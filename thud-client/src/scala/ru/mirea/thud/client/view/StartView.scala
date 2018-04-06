package ru.mirea.thud.client.view

import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf}


class StartView extends jfxf.Initializable {
  @jfxf.FXML private var playerNameField: jfxsc.TextField = _
  @jfxf.FXML private var startGameButton: jfxsc.Button = _
  @jfxf.FXML private var lookingOpponentLabel: jfxsc.Label = _
  @jfxf.FXML private var errorText: jfxsc.Label = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    playerNameField.setText("")
  }

  @jfxf.FXML private def startGameAction(event: jfxe.ActionEvent) {
    System.out.println("Looking for an opponent")
    if (playerNameField.getText().equals("")) {
      errorText.setVisible(true)
    }
    else {
      errorText.setVisible(false)
      startGameButton.setVisible(false)
      lookingOpponentLabel.setVisible(true)
      /*Отправка сообщения на сервер*/
      // ThudGame.gameController ! PlayerConnectionServerMessage(PlayerConnectionInfo(playerNameField.getText, remoteConfig.getString("hostname"), remoteConfig.getInt("port")))
      Main.stage.close()
      val fieldView = new FieldView
      fieldView.showStartField()

    }
  }

  @jfxf.FXML private def showHelpButton(event: jfxe.ActionEvent) {
    val help = new HelpView
    help.showHelpDialog()
  }
}
