package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame._
import ru.mirea.thud.client.controller.FieldViewController._
import ru.mirea.thud.client.controller.StartViewController.closeStartView
import ru.mirea.thud.client.model.messages._
import ru.mirea.thud.client.service.CommonUnitActions.setHighlightedCellsToDefault
import ru.mirea.thud.client.state.GameState
import ru.mirea.thud.common.constants.FieldCellType._
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
      case DWARF => DwarfService.processAttack(attackedUnit)
      case TROLL => TrollService.processTrollAttack(attackedUnit)
      case EMPTY =>
    }
  }

  private def performMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.cellType match {
      case DWARF => setHighlightedCellsToDefault(DwarfService.getCellsToHighlightAttack, DwarfService.getCellsToHighlightMove)
      case TROLL => setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove)
      case EMPTY =>
    }
    gameService ! MoveFiguresMessage(PlayerIdentifiers(GameState.playerState.sessionId, GameState.playerState.id), unit, newCell)
  }

  private def calculateMovement(unit: FieldUnit): Unit = unit.cellType match {
    case DWARF => DwarfService.calculateMovement(unit)
    case TROLL => TrollService.calculateTrollMovement(unit)
    case EMPTY =>
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

