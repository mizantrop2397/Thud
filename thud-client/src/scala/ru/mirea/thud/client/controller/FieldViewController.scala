package ru.mirea.thud.client.controller

import ru.mirea.thud.client.app.ThudGame.playerService
import ru.mirea.thud.client.constants.CellTargetMode
import ru.mirea.thud.client.controller.ExitViewController.{processExit, showExitView}
import ru.mirea.thud.client.factory.StageFactory._
import ru.mirea.thud.client.loader.ViewLoader.loadGameScreenViewForm
import ru.mirea.thud.client.mapping.UnitToCellMapping.{getRectangleId, getUnitIdByImageView, getUnitIdByRectangle}
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

  def loadMovementScheme(id: String): Unit = getUnitIdByRectangle(id) match {
    case Some(foundId) => playerService ! CalculateMovementSchemeMessage(GameState.field units foundId)
    case None => getUnitIdByImageView(id) match {
      case Some(foundId) => playerService ! CalculateMovementSchemeMessage(GameState.field units foundId)
      case None =>
    }
  }

  def highlightCells(cells: Map[Location, CellTargetMode.Value]): Unit = view match {
    case Some(v) => cells foreach { case (location, mode) =>
      GameState.field.units find { case (_, unit) => unit.location == location } match {
        case Some(unit) => v highlighCell(getRectangleId(unit._1), mode)
        case None =>
      }
    }
    case None =>
  }
}
