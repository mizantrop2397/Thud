package ru.mirea.thud.client.controller

import ru.mirea.thud.client.creator.StageFactory.createStage
import ru.mirea.thud.client.loader.ViewLoader.loadExitDialog
import ru.mirea.thud.client.view.ExitView
import scalafx.stage.Stage

object ExitViewController {
  lazy val stage: Stage = createStage("Exit from game", loadExitDialog())
  lazy val view: ExitView = new ExitView()
  var processExit = false

  def showExitView(): Unit = if (!stage.showing()) stage.showAndWait()

  def closeExitView(processExit: Boolean): Unit = {
    if (stage.showing()) stage.close()
    this.processExit = processExit
  }
}
