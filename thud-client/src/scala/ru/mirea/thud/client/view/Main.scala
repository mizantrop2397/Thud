package ru.mirea.thud.client.view

import java.io.{File, IOException}
import java.lang.Thread._
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

import scala.io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

object Main extends JFXApp{
  val resourcePath = "C:\\Users\\Анастасия\\Downloads\\Thud-master\\thud-client\\src\\resources"
  val loader: jfxs.Parent = jfxf.FXMLLoader.load(new File(s"$resourcePath\\fxml\\StartViewForm.fxml").toURI.toURL)
  stage = new JFXApp.PrimaryStage {
    title.value = "Thud application"
    scene = new Scene(loader)
    resizable = false;
  }

}
