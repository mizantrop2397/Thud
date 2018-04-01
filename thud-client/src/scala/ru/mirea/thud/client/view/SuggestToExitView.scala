package ru.mirea.thud.client.view
import java.io.File
import java.net.URL
import java.util.ResourceBundle
import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}

import scalafx.stage.Stage
import javafx.{scene => jfxs}

import scalafx.Includes._
import scalafx.scene.Scene

class SuggestToExitView extends jfxf.Initializable{
  @jfxf.FXML private var yesButton: jfxsc.Button = _
  @jfxf.FXML private var noButton: jfxsc.Button = _

  @jfxf.FXML private def yesButtonPressed(event: jfxe.ActionEvent) {
    System.out.println("Закрыть текущее окно. Открыть окно результата")
  }

  @jfxf.FXML private def noButtonPressed(event: jfxe.ActionEvent): Unit = {
    System.out.println("Закрыть текущее окно")
    /*Как получить текущую сцену*/

  }

  def showDialogSuggestToOffer(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\DialogSuggestToOffer.fxml").toURI.toURL)
    var dialogStage = new Stage(){
      title = "Suggest to finish the game"
      scene = new Scene(loader)
      resizable = false
    }
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit ={}
}
