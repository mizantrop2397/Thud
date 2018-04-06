package ru.mirea.thud.client.view
import java.io.File
import java.net.URL
import java.util.ResourceBundle
import javafx.event.EventHandler
import javafx.scene.{control => jfxsc}
import javafx.stage.WindowEvent
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}

import scalafx.stage.Stage
import javafx.{scene => jfxs}

import scalafx.Includes._
import scalafx.event.EventHandler
import scalafx.scene.Scene

class SuggestToExitView extends jfxf.Initializable{
  @jfxf.FXML private var yesButton: jfxsc.Button = _
  @jfxf.FXML private var noButton: jfxsc.Button = _

  var dialogStage: Stage = _

  @jfxf.FXML private def yesButtonPressed(event: jfxe.ActionEvent) {
    System.out.println("Закрыть текущее окно. Открыть окно результата")
    ViewHolder.view.dialogStage.close()
    View.score=ViewHolder.score
    View.name=ViewHolder.name
    val result = new NotificationView
    result.showNotification()
  }

  @jfxf.FXML private def noButtonPressed(event: jfxe.ActionEvent): Unit = {
    System.out.println("Закрыть текущее окно")
    ViewHolder.view.dialogStage.close()
  }

  def showDialogSuggestToOffer(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\DialogSuggestToOffer.fxml").toURI.toURL)
    dialogStage = new Stage(){
      title = "Suggest to finish the game"
      scene = new Scene(loader)
      resizable = false
    }
    ViewHolder.view = this
    dialogStage.showAndWait()
  }


  override def initialize(location: URL, resources: ResourceBundle): Unit ={}

}



object ViewHolder {
  var view: SuggestToExitView = _
  var score:String=_
  var name: String=_
}