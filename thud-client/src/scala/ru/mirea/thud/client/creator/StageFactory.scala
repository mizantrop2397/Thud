package ru.mirea.thud.client.creator

import javafx.{scene => jfxs}
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.stage.Stage

object StageFactory {
  def createStage(name: String, form: jfxs.Parent): Stage = new Stage() {
    title = name
    scene = new Scene(form)
    resizable = false
  }
}
