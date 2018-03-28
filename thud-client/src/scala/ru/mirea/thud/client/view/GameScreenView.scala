package ru.mirea.thud.client.view

import javafx.{fxml, scene, event => jfxe, fxml => jfxf}
import java.net.URL
import java.util.ResourceBundle
import javafx.scene.{control => jfxsc}
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

import scalafx.application.JFXApp
import scalafx.controls.LoginDialogDemo.stage
import scalafx.scene.Scene
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

class GameScreenView extends jfxf.Initializable {
  @jfxf.FXML private var suggestToFinish: jfxsc.Button = _

  @jfxf.FXML private def suggestToExitTheGame(event: jfxe.ActionEvent) {
    System.out.println("Looking for an opponent")
    onShowLoginDialog()

  }

  def onShowLoginDialog(): Unit = {/*
    GameStartView.stage.
    val alert = new Alert(AlertType.Information) {
      initOwner(stage)
      title = "Information Dialog"
      contentText = "The second player won the game with"
    }.showAndWait()*/
  val loader: jfxs.Parent = jfxf.FXMLLoader.load(getClass.getResource("gameScreenViewForm.fxml"))
    stage = new JFXApp.PrimaryStage {
      title.value = "Thud application"
      scene = new Scene(loader)
      resizable = false;
    }
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {}
}
