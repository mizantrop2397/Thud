package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.app.ThudGame
import ru.mirea.thud.client.constants.FieldCellType._
import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.client.model.messages._
import ru.mirea.thud.client.view._
//import ru.mirea.thud.client.view.ru.mirea.thud.client.constants.FieldView
import ru.mirea.thud.common.model.messages.ToClientMessages

class PlayerService extends Actor {
  def action : CommonUnitActions = new CommonUnitActions()

  override def receive: Receive = {

    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => DwarfService.calculateDwarfMovement(unit)
      case TROLL => TrollService.calculateTrollMovement(unit)
    }

    case PerformMovementMessage(unit, newCell) =>
      processMovement(unit, newCell)
      unit.cellType match {
        case DWARF => //TODO: Миша, это твое! Пиши давай, ленивая задница, а то сожгу!
        case TROLL =>
          ThudGame.gameController ! MoveFiguresMessage(unit, newCell)
          action.setHighlightedCellsToDefault(TrollService.getCellsToHighlightAttack, TrollService.getCellsToHighlightMove)
      }

    case PerformAttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => DwarfService.processDwarfAttack(attackedUnit)
      case TROLL => TrollService.processTrollAttack(attackedUnit)
    }

      /*добавить параметр enemyPlayer*/
      /*
    case ToClientMessages.EnemyPlayerDisconnectionMessage (enemyPlayerScore: Int)=> {
      val winnerAfterDisconnect = new WinView
      WinStage.score=enemyPlayerScore.toString
      winnerAfterDisconnect.showWinnerDialog()
    }*/

    case ToClientMessages.SessionCreatedMessage(playerState,enemyPlayerState, field) => {

      val units = field.units.values
      for(unit <- units) {
        unit.id
      }
      Main.stage.close()
      val fieldView = new FieldView
      fieldView.showStartField()
      fieldView.setData(playerState.name,playerState.role, playerState.score, enemyPlayerState.name,enemyPlayerState.role, enemyPlayerState.score)
    }

      /*Нужно имя выигрывшего или двух*/
    case ToClientMessages.DrawOfferingClientMessage(playerScore: Int, enemyPlayerScore: Int)=>{
      if (playerScore>enemyPlayerScore){
        ViewHolder.name
        ViewHolder.score=playerScore.toString
      }
      else{
        ViewHolder.name
        ViewHolder.score=enemyPlayerScore.toString
      }
      val offering = new SuggestToExitView
      offering.showDialogSuggestToOffer()
  }

  }

  /*
    Move a figure
  */
  private def processMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.location.copy(newCell.location.x, newCell.location.y)
  }

}

