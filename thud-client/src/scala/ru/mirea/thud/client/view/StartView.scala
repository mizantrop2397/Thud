package ru.mirea.thud.client.view
import ru.mirea.thud.client.app.ThudGame
import java.net.URL
import java.util.ResourceBundle
import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe}
import javafx.{fxml => jfxf}

import ru.mirea.thud.common.model.PlayerConnectionInfo
import ru.mirea.thud.common.model.messages.ToServerMessages.PlayerConnectionServerMessage


class StartView extends jfxf.Initializable{
  @jfxf.FXML private var playerNameField: jfxsc.TextField = _
  @jfxf.FXML private var startGameButton: jfxsc.Button = _
  @jfxf.FXML private var lookingOpponentLabel: jfxsc.Label = _
  @jfxf.FXML private var errorText: jfxsc.Label = _


  @jfxf.FXML private def startGameAction(event: jfxe.ActionEvent) {
    System.out.println("Looking for an opponent")
    if (playerNameField.getText().equals("")){
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

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    playerNameField.setText("")
  }
}
