package ru.mirea.thud.client.app


import akka.actor.ActorSystem.create
import akka.actor.{ActorRef, ActorRefProvider, ActorSelection, ExtendedActorSystem, Props}
import com.typesafe.config.Config
import ru.mirea.thud.client.constants.ClientConstants._
import ru.mirea.thud.client.controller.StartViewController.showStartView
import ru.mirea.thud.client.service.{FieldViewService, PlayerService}
import ru.mirea.thud.common.constants.CommonConstants._
import ru.mirea.thud.common.path.Paths.serverActorPath
import scalafx.application.JFXApp

object ThudGame extends JFXApp {
  lazy val provider: ActorRefProvider = actorSystem.provider
  lazy val config: Config = actorSystem.settings.config
  lazy val playerService: ActorRef = actorSystem actorOf(Props[PlayerService], PLAYER_SERVICE_ACTOR)
  lazy val fieldService: ActorRef = actorSystem actorOf(Props[FieldViewService], FIELD_SERVICE_ACTOR)
  private val actorSystem = create(CLIENT_ACTOR_SYSTEM).asInstanceOf[ExtendedActorSystem]

  def gameService: ActorSelection = actorSystem actorSelection serverActorPath(GAME_SERVICE_ACTOR)

  showStartView(this)

  println(s"Created player service actor: $playerService")
  println(s"Created field service actor: $fieldService")
  println("ThudGame app started")
}