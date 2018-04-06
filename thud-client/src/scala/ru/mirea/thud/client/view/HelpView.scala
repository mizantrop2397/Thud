package ru.mirea.thud.client.view

import java.net.URL
import java.util.ResourceBundle
import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}

import scalafx.stage.Stage
import javafx.{scene => jfxs}

import scalafx.Includes._
import scalafx.scene.Scene

class HelpView extends jfxf.Initializable{

  def showHelpDialog(): Unit = {
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(getClass.getResource("helpViewForm.fxml"))
    var dialogStage = new Stage(){
      title = "Help"
      scene = new Scene(loader)
      resizable = false
    }
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit ={}
}


