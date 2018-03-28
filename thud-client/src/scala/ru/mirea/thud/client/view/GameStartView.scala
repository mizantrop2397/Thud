package ru.mirea.thud.client.view

import java.net.URL
import java.util.ResourceBundle
import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}

import scalafx.controls.LoginDialogDemo.stage
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType


class GameStartView extends jfxf.Initializable{
  @jfxf.FXML private var playerNameField: jfxsc.TextField = _
  @jfxf.FXML private var startGameButton: jfxsc.Button = _
  @jfxf.FXML private var lookingOpponentLabel: jfxsc.Label = _
  @jfxf.FXML private def startGameAction(event: jfxe.ActionEvent) {
    System.out.println("Looking for an opponent")
    startGameButton.setVisible(false);
    lookingOpponentLabel.setVisible(true);
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {}
}
