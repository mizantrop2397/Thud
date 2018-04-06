package ru.mirea.thud.client.view

import java.io.File
import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf, scene => jfxs}
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.stage.Stage


class ExitView extends jfxf.Initializable {

  var dialogStage: Stage = _
  @jfxf.FXML private var yesButton: jfxsc.Button = _
  @jfxf.FXML private var noButton: jfxsc.Button = _

  def showExitView(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\ExitDialogView.fxml").toURI.toURL)
    dialogStage = new Stage() {
      title = "Exit dialog"
      scene = new Scene(loader)
      resizable = false
    }
    ExView.exview = this
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {}

  @jfxf.FXML private def yesButtonPressed(event: jfxe.ActionEvent) {
    ExView.exview.dialogStage.close()
    System.exit(0)
  }

  @jfxf.FXML private def noButtonPressed(event: jfxe.ActionEvent): Unit = {
    ExView.exview.dialogStage.close()
  }
}

object ExView {
  var exview: ExitView = _
}

