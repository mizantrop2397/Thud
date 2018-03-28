package ru.mirea.thud.client.view

import java.io.IOException
import javafx.scene.Scene
import javafx.{fxml => jfxf}
import javafx.{scene => jfxs}

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType

object Main extends JFXApp{
  val loader: jfxs.Parent = jfxf.FXMLLoader.load(getClass.getResource("gameScreenViewForm.fxml"))
  stage = new JFXApp.PrimaryStage {
    title.value = "Thud application"
    scene = new Scene(loader)
    resizable = false;
  }

}
