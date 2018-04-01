package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame._
import ru.mirea.thud.client.model.messages._
import ru.mirea.thud.client.service.CommonUnitActions.setHighlightedCellsToDefault
import ru.mirea.thud.client.service.DwarfService.processDwarfAttack
import ru.mirea.thud.client.service.TrollService.{calculateTrollMovement, getCellsToHighlightAttack, getCellsToHighlightMove, processTrollAttack}
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.model.FieldUnit
import ru.mirea.thud.common.model.messages.ToServerMessages.MoveFiguresMessage

class PlayerService extends Actor {

  override def receive: Receive = {

    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateDwarfMovement(unit)
      case TROLL => calculateTrollMovement(unit)
    }

    case PerformMovementMessage(unit, newCell) =>
      processMovement(unit, newCell)
      unit.cellType match {
        case DWARF => //TODO: Миша, это твое! Пиши давай, ленивая задница, а то сожгу!
        case TROLL =>
          gameService ! MoveFiguresMessage(unit, newCell)
          setHighlightedCellsToDefault(getCellsToHighlightAttack, getCellsToHighlightMove)
      }

    case PerformAttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => processDwarfAttack(attackedUnit)
      case TROLL => processTrollAttack(attackedUnit)
    }
  }

  /*
    Move a figure
  */
  private def processMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.location.copy(newCell.location.x, newCell.location.y)
  }

}

