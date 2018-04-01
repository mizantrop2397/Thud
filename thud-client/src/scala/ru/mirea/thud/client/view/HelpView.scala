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

class HelpView extends jfxf.Initializable{

  def showHelpDialog(): Unit = {
    val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
    val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\HelpViewForm.fxml").toURI.toURL)
    var dialogStage = new Stage(){
      title = "Help"
      scene = new Scene(loader)
      resizable = false
    }
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit ={}
}


