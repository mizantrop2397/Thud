package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame._
import ru.mirea.thud.client.model.messages._
import ru.mirea.thud.client.service.CommonUnitActions.setHighlightedCellsToDefault
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.model.FieldUnit
import ru.mirea.thud.common.model.messages.ToServerMessages.MoveFiguresMessage

class PlayerService extends Actor {

  override def receive: Receive = {

    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateMovement(unit)
      case TROLL => TrollService.calculateTrollMovement(unit)
    }

    case PerformMovementMessage(unit, newCell) =>
      processMovement(unit, newCell)
      unit.cellType match {
        case DWARF =>
          setHighlightedCellsToDefault(DwarfService.getCellsToHighlightAttack, DwarfService.getCellsToHighlightMove)
        case TROLL =>
          setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove)
      }
      gameService ! MoveFiguresMessage(unit, newCell)

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

