package ru.mirea.thud.server.controller

import java.util.UUID.randomUUID

import akka.actor.Actor
import ru.mirea.thud.common.messages._
import ru.mirea.thud.common.model.{GameField, PlayerConnectionInfo}
import ru.mirea.thud.server.app.Server.clientPlayerService
import ru.mirea.thud.server.session.{PlayerInfo, PlayerSession}

import scala.collection.mutable

class GameService extends Actor {
  val connectedPlayers: mutable.HashMap[String, PlayerSession] = new mutable.HashMap[String, PlayerSession]

  val playersWaitingQueue: mutable.Queue[PlayerConnectionInfo] = new mutable.Queue[PlayerConnectionInfo]

  val gameField = new GameField


  override def receive: Receive = {
    case PlayerConnectionMessage(playerInfo) => handleNewPlayer(playerInfo)
    case PlayerDisconnectionMessage(sessionId) => disconnectPlayer(sessionId)
    case PlayerActionNotificationMessage() => notifyEnemyAboutPlayerAction()
    case DrawOfferingMessageToServer(sessionId, playerId) => offerDrawToEnemyPlayer(sessionId, playerId)
  }

  def handleNewPlayer(info: PlayerConnectionInfo): Unit = {
    if (playersWaitingQueue.nonEmpty) {
      val sessionId = randomUUID().toString
      val firstPlayerId = randomUUID().toString
      val secondPlayerId = randomUUID().toString
      connectedPlayers += sessionId -> PlayerSession(sessionId, PlayerInfo(firstPlayerId, playersWaitingQueue.front), PlayerInfo(secondPlayerId, info))
      return
    }
    playersWaitingQueue += info
  }

  def offerDrawToEnemyPlayer(sessionId: String, playerId: String): Unit = {
    val enemyPlayerInfo = connectedPlayers(sessionId) getPlayer playerId
    clientPlayerService(enemyPlayerInfo.connectionInfo.port, enemyPlayerInfo.connectionInfo.host) ! DrawOfferingMessageToClient()
  }

  def notifyEnemyAboutPlayerAction(): Unit = {

  }

  def disconnectPlayer(sessionId: String): Unit = {
    connectedPlayers -= sessionId
  }
}
