package ru.mirea.thud.common.model.messages

import ru.mirea.thud.common.model.{PlayerConnectionInfo, PlayerState}

object ToClientMessages {

  case class SessionCreatedMessage(playerState: PlayerState, enemyPlayerState: PlayerState)

  case class EnemyPlayerDisconnectionMessage()

  case class DrawOfferingClientMessage(playerScore: Int, enemyPlayerScore: Int)

}


object ToServerMessages {

  case class PlayerConnectionServerMessage(info: PlayerConnectionInfo)

  case class PlayerDisconnectionServerMessage(sessionId: String, playerId: String)

  case class PlayerActionNotificationMessage()

  case class DrawOfferingServerMessage(sessionId: String, playerId: String)

}
