package ru.mirea.thud.client.view

import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf}
import ru.mirea.thud.client.controller.HelpViewController.showHelpView
import ru.mirea.thud.client.controller.StartViewController._


class StartView extends jfxf.Initializable {
  @jfxf.FXML private var playerNameField: jfxsc.TextField = _
  @jfxf.FXML private var startGameButton: jfxsc.Button = _
  @jfxf.FXML private var lookingOpponentLabel: jfxsc.Label = _
  @jfxf.FXML private var errorText: jfxsc.Label = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    playerNameField.setText("")
    viewLoaded(this)
  }

  @jfxf.FXML private def startGameAction(event: jfxe.ActionEvent) {
    if (playerNameField.getText().equals("")) {
      errorText.setVisible(true)
      return
    }
    errorText.setVisible(false)
    startGameButton.setVisible(false)
    lookingOpponentLabel.setVisible(true)
    startWaitingEnemy(playerNameField.getText)
  }

  @jfxf.FXML private def showHelpButton(event: jfxe.ActionEvent): Unit = showHelpView()
}
