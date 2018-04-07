package ru.mirea.thud.client.controller

import ru.mirea.thud.client.creator.StageFactory.createStage
import ru.mirea.thud.client.loader.ViewLoader.loadHelpViewForm
import scalafx.stage.Stage

object HelpViewController {
  lazy val stage: Stage = createStage("Help", loadHelpViewForm())

  def showHelpView(): Unit = if (!stage.showing()) stage.showAndWait()

  def closeExitView(processExit: Boolean): Unit = if (stage.showing()) stage.close()
}
