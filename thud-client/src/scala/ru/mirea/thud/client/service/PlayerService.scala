package ru.mirea.thud.client.service

import java.util
import java.util.stream.Collectors

import akka.actor.Actor
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.CellTargetMode.DEFAULT
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.client.model.messages.{AttackMessage, CalculateMovementSchemeMessage, HighlightCellsMessage, MovementMessage}
import ru.mirea.thud.common.model.Location

/**
  * Review:
  * 1. Почему все protected? Можно ж private
  * 2. А почему нельзя сделать для fieldController-а сообщение ResetHighlightedCells вместо вызова  HighlightCellsMessage с дефолтными параметарми
  */
class PlayerService extends Actor {
  override def receive: Receive = {
    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateDwarfMovement(unit)
      case TROLL => TrollService.calculateTrollMovement(unit)
    }

    case MovementMessage(unit, newCell) =>
      processMovement(unit, newCell)
      unit.cellType match {
        case DWARF => //TODO: Миша, это твое! Пиши давай, ленивая задница, а то сожгу!
        case TROLL => setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove) // Review. Предлагаю переименовать в 'resetHighlightedCells'
      }

    case AttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => DwarfService.processDwarfAttack(attackedUnit)
      case TROLL => TrollService.processTrollAttack(attackedUnit)
    }
  }

  /*
    Move a figure
  */
  protected def processMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.location.copy(newCell.location.x, newCell.location.y) // Здесь будет вызов сервака (впилит Антон)
  }

  /*
  Convert ArrayList to Map [Location, CellTargetMode]
*/
  protected def addToMap(fieldUnit: util.ArrayList[FieldUnit], cellTargetMode: CellTargetMode.Value): Unit = {
    var map: Map[Location, CellTargetMode.Value] = Map()
    for (i <- 0 to fieldUnit.size()) {
      map += (fieldUnit.get(i).location -> cellTargetMode)
    }
    HighlightCellsMessage(map) //Review. Не хватает актера, которому сообщение отправляется. По идее ThudGame.fieldController ! HighlightCellsMessage(map)
  }

  protected def setHighlightedCellsToDefault(arrayToAttack: util.ArrayList[FieldUnit], arrayToMove: util.ArrayList[FieldUnit]): Unit = {
    addToMap(arrayToAttack, DEFAULT)
    addToMap(arrayToMove, DEFAULT)
  }
}

