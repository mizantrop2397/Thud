package ru.mirea.thud.client.app

import akka.actor.ActorSystem.create
import akka.actor.{ActorSelection, Props}
import ru.mirea.thud.client.constants.ClientConstants._
import ru.mirea.thud.client.service.PlayerService
import ru.mirea.thud.common.constants.CommonConstants._
import ru.mirea.thud.common.path.Paths.serverActorPath

object ThudGame extends App {
  private val actorSystem = create(CLIENT_ACTOR_SYSTEM)

  def config = actorSystem.settings.config

  def gameService: ActorSelection = actorSystem actorSelection serverActorPath(GAME_SERVICE_ACTOR)

  def playerService = actorSystem actorOf(Props[PlayerService], PLAYER_SERVICE_ACTOR)

  def fieldController = actorSystem actorOf(Props[PlayerService], FIELD_CONTROLLER_ACTOR)

  println(s"Created player service actor: $playerService")
  println(s"Created field controller actor: $fieldController")
  println("ThudGame app started")
}