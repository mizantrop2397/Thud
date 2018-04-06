package ru.mirea.thud.client.view

import java.io.File
import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf, scene => jfxs}
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.stage.Stage


class WinView extends jfxf.Initializable {
  var dialogStage: Stage = _
  @jfxf.FXML private var okButton: jfxsc.Button = _
  @jfxf.FXML private var totalScore: jfxsc.Label = _

  def showWinnerDialog(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\WinNotificationDialog.fxml").toURI.toURL)
    dialogStage = new Stage() {
      title = "Winner dialog"
      scene = new Scene(loader)
      resizable = false
    }
    WinStage.view = this
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    totalScore.setText(WinStage.score)
  }

  @jfxf.FXML private def okButtonPressed(event: jfxe.ActionEvent) {
    WinStage.view.dialogStage.close()
    System.exit(0)
  }
}

object WinStage {
  var view: WinView = _
  var score: String = _
}
