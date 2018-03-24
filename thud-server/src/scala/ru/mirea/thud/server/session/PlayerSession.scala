package ru.mirea.thud.server.session

import ru.mirea.thud.common.model.{PlayerConnectionInfo, PlayerState}
import ru.mirea.thud.server.exception.PlayerNotFoundException

case class PlayerSession(sessionId: String, firstPlayerInfo: PlayerInfo, secondPlayerInfo: PlayerInfo) {
  def getPlayer(id: String): PlayerInfo = if (id == firstPlayerInfo.state.id) firstPlayerInfo
  else if (id == secondPlayerInfo.state.id) secondPlayerInfo
  else throw new PlayerNotFoundException(s"Player with id = '$id' was not found")

  def getEnemyPlayer(id: String): PlayerInfo = if (id == firstPlayerInfo.state.id && id != secondPlayerInfo.state.id) secondPlayerInfo
  else if (id == secondPlayerInfo.state.id && id != firstPlayerInfo.state.id) firstPlayerInfo
  else throw new PlayerNotFoundException(s"Player with id = '$id' was not found")
}

case class PlayerInfo(connectionInfo: PlayerConnectionInfo, state: PlayerState)