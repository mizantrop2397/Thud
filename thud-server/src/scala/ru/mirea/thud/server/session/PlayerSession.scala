package ru.mirea.thud.server.session

import ru.mirea.thud.common.model.PlayerConnectionInfo
import ru.mirea.thud.server.exception.PlayerNotFoundException

case class PlayerSession(sessionId: String, firstPlayerInfo: PlayerInfo, secondPlayerInfo: PlayerInfo) {
  def getPlayer(id: String): PlayerInfo = if (id == firstPlayerInfo.id) firstPlayerInfo else if (id == secondPlayerInfo.id) secondPlayerInfo else throw new PlayerNotFoundException(s"Player with id = '$id' was not found")
}

case class PlayerInfo(id: String, connectionInfo: PlayerConnectionInfo)