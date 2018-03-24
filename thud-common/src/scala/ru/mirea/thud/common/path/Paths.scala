package ru.mirea.thud.common.path

import ru.mirea.thud.common.constants.CommonConstants.{HOST, SERVER_ACTOR_SYSTEM, SERVER_PORT}

object Paths {
  def serverActorPath(actorName: String) = s"akka.tcp://$SERVER_ACTOR_SYSTEM@$HOST:$SERVER_PORT/user/$actorName"

  def clientActorPath(actorName: String, clientPort: Int, clientHost: String) = s"akka.tcp://$SERVER_ACTOR_SYSTEM@$clientHost:$clientPort/user/$actorName"
}
