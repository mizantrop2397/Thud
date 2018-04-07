package ru.mirea.thud.client.controller

import ru.mirea.thud.client.app.ThudGame.playerService
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.controller.ExitViewController.{processExit, showExitView}
import ru.mirea.thud.client.creator.StageFactory._
import ru.mirea.thud.client.loader.ViewLoader.loadGameScreenViewForm
import ru.mirea.thud.client.mapping.UnitToCellMapping.getUnitIdByRectangle
import ru.mirea.thud.client.model.messages.CalculateMovementSchemeMessage
import ru.mirea.thud.client.state.GameState
import ru.mirea.thud.client.view.FieldView
import ru.mirea.thud.common.model.Location

object FieldViewController {
  var view: Option[FieldView] = None

  def showFieldView(): Unit = {
    val stage = createStage(GameState.playerState.name, loadGameScreenViewForm())
    stage.setOnCloseRequest { event =>
      showExitView()
      if (!processExit) event.consume()
    }
    stage.showAndWait()
  }

  def viewLoaded(fieldView: FieldView): Unit = {
    view = Some(fieldView)
    fieldView.updatePlayersDataFirstGame(GameState.playerState, GameState.enemyPlayerState)
  }

  def loadSecondGamePlayersData(): Unit = view match {
    case Some(v) => v.updatePlayersDataSecondGame(GameState.playerState, GameState.enemyPlayerState)
  }

  def loadMovementScheme(clickedRectangleId: String): Unit = getUnitIdByRectangle(clickedRectangleId) match {
    case Some(id) => playerService ! CalculateMovementSchemeMessage(GameState.field units id)
  }

  def highlighCells(cells: Map[Location, CellTargetMode.Value]): Unit = {

  }
}
