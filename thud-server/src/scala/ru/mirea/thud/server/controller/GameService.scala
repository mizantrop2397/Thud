package ru.mirea.thud.server.controller

import java.util
import java.util.UUID.randomUUID
import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}

import akka.actor.Actor
import ru.mirea.thud.common.constants.PlayerRole._
import ru.mirea.thud.common.model.messages.ToClientMessages.{DrawOfferingClientMessage, EnemyPlayerDisconnectionMessage, SessionCreatedMessage}
import ru.mirea.thud.common.model.messages.ToServerMessages.{DrawOfferingServerMessage, PlayerActionNotificationMessage, PlayerConnectionServerMessage, PlayerDisconnectionServerMessage}
import ru.mirea.thud.common.model.{GameField, PlayerConnectionInfo, PlayerState}
import ru.mirea.thud.server.app.Server.clientPlayerService
import ru.mirea.thud.server.session.{PlayerInfo, PlayerSession}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Random

class GameService extends Actor {
  val sessions: mutable.Map[String, PlayerSession] = new ConcurrentHashMap[String, PlayerSession].asScala

  val playersWaitingQueue: util.Queue[PlayerConnectionInfo] = new ConcurrentLinkedQueue[PlayerConnectionInfo]

  val gameField = new GameField

  override def receive: Receive = {
    case PlayerConnectionServerMessage(playerInfo) => handleNewPlayer(playerInfo)
    case PlayerDisconnectionServerMessage(sessionId, playerId) => disconnectPlayer(sessionId, playerId)
    case PlayerActionNotificationMessage() => notifyEnemyAboutPlayerAction()
    case DrawOfferingServerMessage(sessionId, playerId) => offerDrawToEnemyPlayer(sessionId, playerId)
  }

  def handleNewPlayer(newPlayerInfo: PlayerConnectionInfo): Unit = {
    if (!playersWaitingQueue.isEmpty) {
      val sessionId = randomUUID().toString
      val waitingPlayerId = randomUUID().toString
      val waitingPlayerInfo = playersWaitingQueue.poll
      val waitingPlayerRole = if (Random nextBoolean()) TROLL else DWARF
      val waitingPlayerState = PlayerState(sessionId, waitingPlayerId, waitingPlayerInfo.name, 0, waitingPlayerRole)
      val newPlayerId = randomUUID().toString
      val newPlayerRole = if (waitingPlayerRole == TROLL) DWARF else TROLL
      val newPlayerState = PlayerState(sessionId, newPlayerId, newPlayerInfo.name, 0, newPlayerRole)
      sessions += sessionId -> PlayerSession(sessionId, PlayerInfo(waitingPlayerInfo, waitingPlayerState), PlayerInfo(newPlayerInfo, newPlayerState))
      val sessionCreatedMessage = SessionCreatedMessage(waitingPlayerState, newPlayerState)
      clientPlayerService(waitingPlayerInfo.port, waitingPlayerInfo.host) ! sessionCreatedMessage
      return
    }
    playersWaitingQueue add newPlayerInfo
  }

  def offerDrawToEnemyPlayer(sessionId: String, playerId: String): Unit = {
    val playerInfo = sessions(sessionId) getPlayer playerId
    val enemyPlayerInfo = sessions(sessionId) getEnemyPlayer playerId
    clientPlayerService(enemyPlayerInfo.connectionInfo.port, enemyPlayerInfo.connectionInfo.host) ! DrawOfferingClientMessage(enemyPlayerInfo.state.score, playerInfo.state.score)
  }

  def notifyEnemyAboutPlayerAction(): Unit = {

  }

  def disconnectPlayer(sessionId: String, playerId: String): Unit = {
    val enemyPlayer = sessions(sessionId) getEnemyPlayer playerId
    sessions -= sessionId
    clientPlayerService(enemyPlayer.connectionInfo.port, enemyPlayer.connectionInfo.host) ! EnemyPlayerDisconnectionMessage()
  }
}