package ru.mirea.thud.server.controller

import java.util.UUID.randomUUID

import akka.actor.Actor
import ru.mirea.thud.common.messages.{DrawOfferingMessage, PlayerActionNotificationMessage, PlayerConnectionMessage, PlayerDisconnectionMessage}
import ru.mirea.thud.common.model.GameField
import ru.mirea.thud.server.session.PlayerSession

import scala.collection.mutable

class GameService extends Actor {
  val connectedPlayers: mutable.HashMap[String, PlayerSession] = new mutable.HashMap[String, PlayerSession]

  val playersWaitingQueue: mutable.Queue[String] = new mutable.Queue[String]

  val gameField = new GameField

  override def receive: Receive = {
    case PlayerConnectionMessage(playerName) => handleNewPlayer(playerName)
    case PlayerDisconnectionMessage(playerName) => disconnectPlayer(playerName)
    case PlayerActionNotificationMessage() => notifyEnemyAboutPlayerAction()
    case DrawOfferingMessage() => drawOfferToEnemyPlayer()
  }

  def drawOfferToEnemyPlayer(): Unit = ???

  def notifyEnemyAboutPlayerAction(): Unit = ???

  def disconnectPlayer(playerName: String): Unit = {
    connectedPlayers -= playerName
  }

  def handleNewPlayer(playerName: String): Unit = {
    if (playersWaitingQueue.nonEmpty) {
      val sessionId = randomUUID().toString
      connectedPlayers += sessionId -> PlayerSession(sessionId, playersWaitingQueue.front, playerName)
      return
    }
    playersWaitingQueue += playerName
  }
}
