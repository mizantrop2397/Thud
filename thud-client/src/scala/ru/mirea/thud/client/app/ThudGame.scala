package ru.mirea.thud.client.app

import akka.actor.ActorSystem.create
import akka.actor.{ActorSelection, Props}
import com.typesafe.config.Config
import ru.mirea.thud.client.constants.ClientConstants._
import ru.mirea.thud.client.service.PlayerService
import ru.mirea.thud.common.constants.CommonConstants._
import ru.mirea.thud.common.model.PlayerConnectionInfo
import ru.mirea.thud.common.model.messages.ToServerMessages.PlayerConnectionServerMessage
import ru.mirea.thud.common.path.Paths.serverActorPath

object ThudGame extends App {
  private val actorSystem = create(CLIENT_ACTOR_SYSTEM)
  val playerController = actorSystem actorOf(Props[PlayerService], PLAYER_SERVICE_ACTOR)
  val fieldController = actorSystem actorOf(Props[PlayerService], FIELD_CONTROLLER_ACTOR)

  println(s"Created player controller actor: $playerController")
  println(s"Created field controller actor: $fieldController")
  println("ThudGame app started")

  def gameController: ActorSelection = actorSystem actorSelection serverActorPath(GAME_SERVICE_ACTOR)

  def config = actorSystem.settings.config
}