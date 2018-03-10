package ru.mirea.thud.client.app

import akka.actor.ActorSystem.create
import akka.actor.Props
import ru.mirea.thud.client.controller.PlayerController
import ru.mirea.thud.common.constants.CommonConstants._

object ThudGame extends App {
  private val actorSystem = create(CLIENT_ACTOR_SYSTEM)
  val playerController = actorSystem actorOf(Props[PlayerController], PLAYER_CONTROLLER_ACTOR)
  def gameController = actorSystem actorSelection GAME_CONTROLLER_ACTOR
  println(s"Created player controller actor: $playerController")
  println("ThudGame app started")
}
