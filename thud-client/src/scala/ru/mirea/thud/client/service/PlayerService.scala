package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame._
import ru.mirea.thud.client.model.messages._
import ru.mirea.thud.client.service.CommonUnitActions.setHighlightedCellsToDefault
import ru.mirea.thud.client.state.GameState
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.model.messages.PlayerIdentifiers
import ru.mirea.thud.common.model.messages.ToServerMessages.MoveFiguresMessage

class PlayerService extends Actor {

  override def receive: Receive = {

    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateMovement(unit)
      case TROLL => TrollService.calculateTrollMovement(unit)
    }

    case PerformMovementMessage(unit, newCell) =>
      unit.cellType match {
        case DWARF =>
          setHighlightedCellsToDefault(DwarfService.getCellsToHighlightAttack, DwarfService.getCellsToHighlightMove)
        case TROLL =>
          setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove)
      }
      gameService ! MoveFiguresMessage(PlayerIdentifiers(GameState.playerState.sessionId, GameState.playerState.id), unit, newCell)

    case PerformAttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => DwarfService.processAttack(attackedUnit)
      case TROLL => TrollService.processTrollAttack(attackedUnit)
    }
  }
}

