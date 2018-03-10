package ru.mirea.thud.client.app

import akka.actor.ActorSystem.create
import akka.actor.Props
import ru.mirea.thud.client.constants.ClientConstants.{CLIENT_ACTOR_SYSTEM, PLAYER_CONTROLLER_ACTOR}
import ru.mirea.thud.client.controller.PlayerController

object ThudGame extends App {
  private val actorSystem = create(CLIENT_ACTOR_SYSTEM)
  val playerController = actorSystem actorOf(Props[PlayerController], PLAYER_CONTROLLER_ACTOR)
  println(s"Created player controller actor: $playerController")
  println("ThudGame app started")
}
