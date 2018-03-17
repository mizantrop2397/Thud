package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.constants.FieldCellType._
import ru.mirea.thud.client.model.FieldUnit
import ru.mirea.thud.client.model.messages.{AttackMessage, CalculateMovementSchemeMessage, MovementMessage}

class PlayerService extends Actor {
  var troll : TrollService = new TrollService
  var dwarf : DwarfService = new DwarfService
  override def receive: Receive = {

    case CalculateMovementSchemeMessage(unit) => unit.cellType match {
      case DWARF => dwarf.calculateDwarfMovement(unit)
      case TROLL => troll.calculateTrollMovement(unit)
    }
    case MovementMessage(unit, newCell) =>
      processMovement(unit, newCell)

    case AttackMessage(attackedUnit) => attackedUnit.cellType match {
      case DWARF => dwarf.processDwarfAttack(attackedUnit)
      case TROLL => troll.processTrollAttack(attackedUnit)
    }
  }

  /*
    Move a figure
  */
  protected def processMovement(unit: FieldUnit, newCell: FieldUnit): Unit = {
    unit.location.copy(newCell.location.x, newCell.location.y)
  }


  protected def isCellEmpty(cell: FieldUnit): Boolean ={
    cell.cellType.equals(EMPTY)
  }

  protected def isCellOut(cell: FieldUnit): Boolean ={
    cell.cellType.equals(OUT)
  }

  protected def isCellDwarf(cell: FieldUnit): Boolean ={
    cell.cellType.equals(DWARF)
  }

  protected def isCellTroll(cell: FieldUnit): Boolean ={
    cell.cellType.equals(TROLL)
  }

  protected def isCellRock(cell: FieldUnit): Boolean ={
    cell.cellType.equals(ROCK)
  }
}

