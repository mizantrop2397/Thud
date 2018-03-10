package ru.mirea.thud.server.server

import akka.actor.ActorSystem.create
import akka.actor.Props
import ru.mirea.thud.server.constants.ServerConstants.SERVER_ACTOR_SYSTEM
import ru.mirea.thud.server.controller.GameController

object Server extends App {
  private val actorSystem = create(SERVER_ACTOR_SYSTEM)
  actorSystem actorOf Props[GameController]
  println("Server started")
}
