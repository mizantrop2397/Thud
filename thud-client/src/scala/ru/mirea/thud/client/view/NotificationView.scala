package ru.mirea.thud.client.view

import java.io.File
import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf, scene => jfxs}
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.stage.Stage


class NotificationView extends jfxf.Initializable {

  var dialogStage: Stage = _
  @jfxf.FXML private var okButton: jfxsc.Button = _
  @jfxf.FXML private var winnerName: jfxsc.Label = _
  @jfxf.FXML private var totalScore: jfxsc.Label = _

  def showNotification(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\NotificationDialogView.fxml").toURI.toURL)
    dialogStage = new Stage() {
      title = "Result"
      scene = new Scene(loader)
      resizable = false
    }
    View.view = this
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    winnerName.setText(View.name)
    totalScore.setText(View.score)
  }

  @jfxf.FXML private def okButtonPressed(event: jfxe.ActionEvent) {
    View.view.dialogStage.close()
    System.exit(0)
  }
}

object View {
  var view: NotificationView = _
  var score: String = _
  var name: String = _
}
