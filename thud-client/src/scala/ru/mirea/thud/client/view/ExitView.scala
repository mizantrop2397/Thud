package ru.mirea.thud.client.view

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf}
import ru.mirea.thud.client.controller.ExitViewController.closeExitView

class ExitView() {
  @jfxf.FXML private var yesButton: jfxsc.Button = _
  @jfxf.FXML private var noButton: jfxsc.Button = _

  @jfxf.FXML private def yesButtonPressed(event: jfxe.ActionEvent) {
    closeExitView(true)
    System.exit(0)
  }

  @jfxf.FXML private def noButtonPressed(event: jfxe.ActionEvent): Unit = {
    closeExitView(false)
  }
}