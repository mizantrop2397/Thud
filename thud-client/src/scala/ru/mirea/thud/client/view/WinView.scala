package ru.mirea.thud.client.view

import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf}
import ru.mirea.thud.client.loader.ViewLoader.loadWinNotificationDialog
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.stage.Stage


class WinView extends jfxf.Initializable {
  var dialogStage: Stage = _
  @jfxf.FXML private var okButton: jfxsc.Button = _
  @jfxf.FXML private var totalScore: jfxsc.Label = _

  def showWinnerDialog(): Unit = {
    dialogStage = new Stage() {
      title = "Winner dialog"
      scene = new Scene(loadWinNotificationDialog())
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
