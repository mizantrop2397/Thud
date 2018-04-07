package ru.mirea.thud.server.service

import java.util
import java.util.UUID.randomUUID
import java.util.concurrent.{ConcurrentHashMap, ConcurrentLinkedQueue}

import akka.actor.Actor
import ru.mirea.thud.common.constants.FieldCellType.EMPTY
import ru.mirea.thud.common.constants.PlayerRole._
import ru.mirea.thud.common.model.messages.PlayerIdentifiers
import ru.mirea.thud.common.model.messages.ToClientMessages.{DrawOfferingClientMessage, EnemyPlayerDisconnectionMessage, SessionCreatedMessage, UpdateGameField}
import ru.mirea.thud.common.model.messages.ToServerMessages._
import ru.mirea.thud.common.model.{FieldUnit, PlayerConnectionInfo, PlayerState}
import ru.mirea.thud.server.app.Server.clientPlayerService
import ru.mirea.thud.server.calculator.NeighborsCalculator.calculateNeighbors
import ru.mirea.thud.server.generator.GameFieldGenerator.generateDefaultField
import ru.mirea.thud.server.session.{PlayerInfo, PlayerSession}

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Random

class GameService extends Actor {
  val sessions: mutable.Map[String, PlayerSession] = new ConcurrentHashMap[String, PlayerSession].asScala

  val playersWaitingQueue: util.Queue[PlayerConnectionInfo] = new ConcurrentLinkedQueue[PlayerConnectionInfo]

  override def receive: Receive = {
    case PlayerConnectionServerMessage(playerInfo) => handleNewPlayer(playerInfo)
    case PlayerDisconnectionServerMessage(identifiers) => disconnectPlayer(identifiers)
    case DrawOfferingServerMessage(identifiers) => offerDrawToEnemyPlayer(identifiers)
    case MoveFiguresMessage(identifiers, fromCell, toCell) => moveFigures(identifiers, fromCell, toCell)
    case DeleteFiguresMessage(identifiers, cells) => deleteFigures(identifiers, cells.asScala toList)
  }

  private def handleNewPlayer(newPlayerInfo: PlayerConnectionInfo): Unit = {
    if (playersWaitingQueue.isEmpty) {
      playersWaitingQueue add newPlayerInfo
      return
    }
    val sessionId = randomUUID().toString
    val waitingPlayerId = randomUUID().toString
    val waitingPlayerInfo = playersWaitingQueue.poll
    val waitingPlayerRole = if (Random nextBoolean()) TROLL else DWARF
    val waitingPlayerState = PlayerState(sessionId, waitingPlayerId, waitingPlayerInfo.name, 0, waitingPlayerRole)
    val newPlayerId = randomUUID().toString
    val newPlayerRole = if (waitingPlayerRole == TROLL) DWARF else TROLL
    val newPlayerState = PlayerState(sessionId, newPlayerId, newPlayerInfo.name, 0, newPlayerRole)
    val field = generateDefaultField()
    sessions += sessionId -> PlayerSession(sessionId, PlayerInfo(waitingPlayerInfo, waitingPlayerState), PlayerInfo(newPlayerInfo, newPlayerState), field)
    clientPlayerService(waitingPlayerInfo.port, waitingPlayerInfo.host) ! SessionCreatedMessage(field, waitingPlayerState, newPlayerState)
    clientPlayerService(newPlayerInfo.port, newPlayerInfo.host) ! SessionCreatedMessage(field, newPlayerState, waitingPlayerState)
  }

  private def offerDrawToEnemyPlayer(identifiers: PlayerIdentifiers): Unit = {
    val player = sessions(identifiers.sessionId) getPlayer identifiers.playerId
    val enemy = sessions(identifiers.sessionId) getEnemyPlayer identifiers.playerId
    clientPlayerService(enemy.connectionInfo.port, enemy.connectionInfo.host) ! DrawOfferingClientMessage(enemy.state.score, player.state.score)
  }

  private def disconnectPlayer(identifiers: PlayerIdentifiers): Unit = {
    val enemy = sessions(identifiers.sessionId) getEnemyPlayer identifiers.playerId connectionInfo

    sessions -= identifiers.sessionId
    clientPlayerService(enemy.port, enemy.host) ! EnemyPlayerDisconnectionMessage()
  }

  private def moveFigures(identifiers: PlayerIdentifiers, fromCell: FieldUnit, toCell: FieldUnit): Unit = {
    val session = sessions(identifiers.sessionId)
    fromCell.neighbors remove fromCell.neighbors.indexWhere(neighbor => neighbor == fromCell)
    calculateNeighbors(toCell, session.gameField) foreach { neighbor =>
      toCell.neighbors += neighbor
      neighbor.neighbors += toCell
    }
    val player = session getPlayer identifiers.playerId connectionInfo
    val enemy = session getEnemyPlayer identifiers.playerId connectionInfo

//    gameField.units(fromCell.location) = fromCell
//    gameField.units(toCell.location) = toCell
//    clientPlayerService(player.port, player.host) ! UpdateGameField(gameField)
    clientPlayerService(enemy.port, enemy.host) ! UpdateGameField(session.gameField)
  }


  private def deleteFigures(identifiers: PlayerIdentifiers, units: List[FieldUnit]): Unit = {
    units foreach { unit =>
      sessions(identifiers.sessionId).gameField.units
        .values
        .filter(gameUnit => gameUnit.location == unit.location)
        .foreach(unit => unit.cellType = EMPTY)
    }
    val player = sessions(identifiers.sessionId) getPlayer identifiers.playerId connectionInfo
    val enemy = sessions(identifiers.sessionId) getEnemyPlayer identifiers.playerId connectionInfo

    clientPlayerService(player.port, player.host) ! UpdateGameField(sessions(identifiers.sessionId).gameField)
    clientPlayerService(enemy.port, enemy.host) ! UpdateGameField(sessions(identifiers.sessionId).gameField)
  }
}
