package ru.mirea.thud.server.app

import akka.actor.ActorSystem.create
import akka.actor.{ActorRef, ActorSelection, Props}
import ru.mirea.thud.common.constants.CommonConstants.{GAME_SERVICE_ACTOR, PLAYER_SERVICE_ACTOR, SERVER_ACTOR_SYSTEM}
import ru.mirea.thud.common.path.Paths.clientActorPath
import ru.mirea.thud.server.service.GameService

object Server extends App {
  private lazy val gameService: ActorRef = actorSystem actorOf(Props[GameService], GAME_SERVICE_ACTOR)
  private val actorSystem = create(SERVER_ACTOR_SYSTEM)
  println(s"Created actor: $gameService")
  println("Server started")

  def clientPlayerService(clientPort: Int, clientHost: String): ActorSelection = {
    actorSystem actorSelection clientActorPath(PLAYER_SERVICE_ACTOR, clientPort, clientHost)
  }
}
