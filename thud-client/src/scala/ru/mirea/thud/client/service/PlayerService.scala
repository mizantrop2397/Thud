package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame
import ru.mirea.thud.client.constants.FieldCellType._
import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.client.model.messages._

class PlayerService extends Actor {
  def action : CommonUnitActions = new CommonUnitActions()

  override def receive: Receive = {

    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateDwarfMovement(unit)
      case TROLL => TrollService.calculateTrollMovement(unit)
    }

    case PerformMovementMessage(unit, newCell) =>
      processMovement(unit, newCell)
      unit.cellType match {
        case DWARF => //TODO: Миша, это твое! Пиши давай, ленивая задница, а то сожгу!
        case TROLL =>
          ThudGame.gameController ! MoveFiguresMessage(unit, newCell)
          action.setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove)
      }

    case PerformAttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => DwarfService.processDwarfAttack(attackedUnit)
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

