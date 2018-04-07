package ru.mirea.thud.client.view

import java.net.URL
import java.util.ResourceBundle

import javafx.scene.{control => jfxsc}
import javafx.{event => jfxe, fxml => jfxf}
import ru.mirea.thud.client.app.ThudGame.gameService
import ru.mirea.thud.client.loader.ViewLoader.loadDialogSuggestToOffer
import ru.mirea.thud.client.state.GameState
import ru.mirea.thud.common.model.messages.PlayerIdentifiers
import ru.mirea.thud.common.model.messages.ToServerMessages.DrawOfferingServerMessage
import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.stage.Stage

class SuggestToExitView extends jfxf.Initializable {
  var dialogStage: Stage = _
  @jfxf.FXML private var yesButton: jfxsc.Button = _
  @jfxf.FXML private var noButton: jfxsc.Button = _

  def showDialogSuggestToOffer(): Unit = {
    dialogStage = new Stage() {
      title = "Suggest to finish the game"
      scene = new Scene(loadDialogSuggestToOffer())
      resizable = false
    }
    ViewHolder.view = this
    dialogStage.showAndWait()
  }

  override def initialize(location: URL, resources: ResourceBundle): Unit = {
    ViewHolder.score = GameState.playerState.score.toString
    ViewHolder.name = GameState.playerState.name
  }

  @jfxf.FXML private def yesButtonPressed(event: jfxe.ActionEvent) {
    gameService ! DrawOfferingServerMessage(PlayerIdentifiers(GameState.playerState.sessionId, GameState.playerState.id))
    System.out.println("Закрыть текущее окно. Открыть окно результата")
    ViewHolder.view.dialogStage.close()
    View.score = ViewHolder.score
    View.name = ViewHolder.name
    val result = new NotificationView
    result.showNotification()
  }

  @jfxf.FXML private def noButtonPressed(event: jfxe.ActionEvent): Unit = {
    System.out.println("Закрыть текущее окно")
    ViewHolder.view.dialogStage.close()
  }

}


object ViewHolder {
  var view: SuggestToExitView = _
  var score: String = _
  var name: String = _
}