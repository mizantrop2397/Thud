package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame._
import ru.mirea.thud.client.controller.FieldViewController._
import ru.mirea.thud.client.controller.StartViewController.closeStartView
import ru.mirea.thud.client.model.messages._
import ru.mirea.thud.client.service.CommonUnitActions.setHighlightedCellsToDefault
import ru.mirea.thud.client.service.DwarfService.{calculateDwarfMovement, getDwarfCellsToHighlightAttack, getDwarfCellsToHighlightMove, processDwarfAttack}
import ru.mirea.thud.client.service.TrollService.{calculateTrollMovement, getTrollCellsToHighlightAttack, getTrollCellsToHighlightMove, processTrollAttack}
import ru.mirea.thud.client.state.GameState
import ru.mirea.thud.common.constants.FieldCellType._
import ru.mirea.thud.common.constants.PlayerRole
import ru.mirea.thud.common.model.messages.PlayerIdentifiers
import ru.mirea.thud.common.model.messages.ToClientMessages.SessionCreatedMessage
import ru.mirea.thud.common.model.messages.ToServerMessages.MoveFiguresMessage
import ru.mirea.thud.common.model.{FieldUnit, GameField, PlayerState}
import scalafx.application.Platform.runLater

class PlayerService extends Actor {
  override def receive: Receive = {
    case SessionCreatedMessage(field, playerState, enemyPlayerState) => startGame(field, playerState, enemyPlayerState)
    case CalculateMovementSchemeMessage(unit) => calculateMovement(unit)
    case PerformMovementMessage(unit, newCell) => performMovement(unit, newCell)
    case PerformAttackMessage(attackedUnit) => performAttack(attackedUnit)
  }

  private def performAttack(attackedUnit: FieldUnit): Unit = {
    attackedUnit.cellType match {
      case DWARF => processDwarfAttack(attackedUnit)
      case TROLL => processTrollAttack(attackedUnit)
    }
  }

  private def performMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.cellType match {
      case DWARF => setHighlightedCellsToDefault(getDwarfCellsToHighlightAttack, getDwarfCellsToHighlightMove)
      case TROLL => setHighlightedCellsToDefault(getTrollCellsToHighlightAttack, getTrollCellsToHighlightMove)
    }
    gameService ! MoveFiguresMessage(PlayerIdentifiers(GameState.playerState.sessionId, GameState.playerState.id), unit, newCell)
  }

  private def calculateMovement(unit: FieldUnit): Unit = unit.cellType match {
    case DWARF => if(GameState.playerState.role == PlayerRole.DWARF) calculateDwarfMovement(unit)
    case TROLL => if(GameState.playerState.role == PlayerRole.TROLL) calculateTrollMovement(unit)
  }


  private def startGame(field: GameField, playerState: PlayerState, enemyPlayerState: PlayerState): Unit = {
    GameState.field = field
    GameState.playerState = playerState
    GameState.enemyPlayerState = enemyPlayerState
    runLater {
      closeStartView()
      showFieldView()
    }
  }
}

