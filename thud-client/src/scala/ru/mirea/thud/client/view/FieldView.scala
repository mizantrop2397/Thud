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
/**
  * Created by Anastasia on 28.03.2018.
  */
class FieldView extends jfxf.Initializable {
  @jfxf.FXML private var suggestToFinish: jfxsc.Button = _
  @jfxf.FXML private var helpButton: jfxsc.Button = _

  @jfxf.FXML private def suggestToExitTheGame(event: jfxe.ActionEvent) {
    val dialog = new SuggestToExitView;
    dialog.showDialogSuggestToOffer();
  }

  @jfxf.FXML private def showHelpButton(event: jfxe.ActionEvent) {
    System.out.println("Looking for an opponent")
    val help = new HelpView
    help.showHelpDialog()
  }

  @jfxf.FXML private def putTheButton(event: jfxe.ActionEvent) {
    System.out.println("Кнопка нажата")

  }

  def showStartField(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\GameScreenViewForm.fxml").toURI.toURL)
  //  val loader: jfxs.Parent = jfxf.FXMLLoader.load(getClass.getResource("GameScreenViewForm.fxml"))
    var dialogStage = new Stage(){
      scene = new Scene(loader)
      resizable = false
    }
    dialogStage.showAndWait()
  }




  override def initialize(location: URL, resources: ResourceBundle): Unit = {
  }

}
