package ru.mirea.thud.client.app

import akka.actor.ActorSystem.create
import ru.mirea.thud.client.constants.ClientConstants.CLIENT_ACTOR_SYSTEM

object ThudGame extends App {
  private val actorSystem = create(CLIENT_ACTOR_SYSTEM)
  println("ThudGame app started")
}
