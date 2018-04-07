package ru.mirea.thud.common.model.messages

import ru.mirea.thud.common.model.{FieldUnit, GameField, PlayerConnectionInfo, PlayerState}

object ToClientMessages {

  case class SessionCreatedMessage(gameField: GameField, playerState: PlayerState, enemyPlayerState: PlayerState)

  case class EnemyPlayerDisconnectionMessage()

  case class DrawOfferingClientMessage(playerScore: Int, enemyPlayerScore: Int)

  case class UpdateGameField(gameField: GameField)

}


object ToServerMessages {

  case class MoveFiguresMessage(identifiers: PlayerIdentifiers, oldCell: FieldUnit, newCell: FieldUnit)

  case class DeleteFiguresMessage(identifiers: PlayerIdentifiers, cells: List[FieldUnit])

  case class PlayerConnectionServerMessage(info: PlayerConnectionInfo)

  case class PlayerDisconnectionServerMessage(identifiers: PlayerIdentifiers)

  case class DrawOfferingServerMessage(identifiers: PlayerIdentifiers)

}

case class PlayerIdentifiers(sessionId: String, playerId: String)