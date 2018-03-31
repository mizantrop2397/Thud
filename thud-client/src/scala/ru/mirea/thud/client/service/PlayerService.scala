package ru.mirea.thud.client.service

import java.util
import java.util.stream.Collectors

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame
import ru.mirea.thud.client.constants.FieldCellType._
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.constants.CellTargetMode.DEFAULT
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.client.model.messages._

/**
  * Review:
  * 1. Почему все protected? Можно ж private
  * 2. А почему нельзя сделать для fieldController-а сообщение ResetHighlightedCells вместо вызова  HighlightCellsMessage с дефолтными параметарми
  */
class PlayerService extends Actor {
  def action : CommonUnitActions = new CommonUnitActions()

  override def receive: Receive = {
    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateMovement(unit)
      case TROLL => TrollService.calculateTrollMovement(unit)
    }

    case PerformMovementMessage(unit, newCell) =>
      processMovement(unit, newCell)
      unit.cellType match {
        case DWARF => //TODO: Миша, это твое! Пиши давай, ленивая задница, а то сожгу!
        case TROLL => setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove) // Review. Предлагаю переименовать в 'resetHighlightedCells'
      }

    case PerformAttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => DwarfService.processAttack(attackedUnit)
      case TROLL => TrollService.processTrollAttack(attackedUnit)
    }
  }

  /*
    Move a figure
  */
  private def processMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.location.copy(newCell.location.x, newCell.location.y)
  }

}

