package ru.mirea.thud.client.controller

import javafx.scene._
import ru.mirea.thud.client.app.ThudGame.gameService
import ru.mirea.thud.client.loader.ViewLoader.loadStartViewForm
import ru.mirea.thud.client.service.ConfigService.{hostName, port}
import ru.mirea.thud.client.view.StartView
import ru.mirea.thud.common.model.PlayerConnectionInfo
import ru.mirea.thud.common.model.messages.ToServerMessages.PlayerConnectionServerMessage
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage


object StartViewController {
  var application: Option[JFXApp] = None
  var view: Option[StartView] = None

  def showStartView(app: JFXApp): Unit = {
    this.application = Some(app)
    app.stage = new PrimaryStage {
      title = "Thud application"
      resizable = false
      val root: Parent = loadStartViewForm()
      scene = new Scene(root)
    }
  }

  def startWaitingEnemy(playerName: String): Unit = {
    System.out.println("Looking for an opponent...")
    gameService ! PlayerConnectionServerMessage(PlayerConnectionInfo(playerName, hostName, port))
  }

  def viewLoaded(view :StartView): Unit = {
    this.view = Some(view)
  }

  def closeStartView(): Unit = application match {
    case Some(app) => if (app.stage != null && app.stage.showing()) app.stage.close()
  }
}
