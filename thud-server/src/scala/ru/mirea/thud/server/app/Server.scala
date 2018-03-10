package ru.mirea.thud.server.app

import akka.actor.ActorSystem.create
import akka.actor.Props
import ru.mirea.thud.common.constants.CommonConstants.{GAME_SERVICE_ACTOR, SERVER_ACTOR_SYSTEM}
import ru.mirea.thud.server.controller.GameService

object Server extends App {
  private val actorSystem = create(SERVER_ACTOR_SYSTEM)
  actorSystem actorOf(Props[GameService], GAME_SERVICE_ACTOR)
  println("Server started")
}
