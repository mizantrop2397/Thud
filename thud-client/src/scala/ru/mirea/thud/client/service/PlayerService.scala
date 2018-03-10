package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.constants.PlayerRole.{DWARF, TROLL}
import ru.mirea.thud.client.model.{GameUnit, Location}
import ru.mirea.thud.client.model.messages.{AttackMessage, CalculateMovementSchemeMessage, MovementMessage}

class PlayerService extends Actor {
  override def receive: Receive = {
    case CalculateMovementSchemeMessage(unit) => unit.unitType match {
      case DWARF => calculateDwarfMovement(unit)
      case TROLL => calculateTrollMovement(unit)
    }
    case MovementMessage(unit) => unit.unitType match {
      case DWARF => processDwarfMovement(unit)
      case TROLL => processTrollMovement(unit)
    }
    case AttackMessage(controllingUnit, attackedUnit) => controllingUnit.unitType match {
      case DWARF => processDwarfAttack(controllingUnit, attackedUnit)
      case TROLL => processTrollAttack(controllingUnit, attackedUnit)
    }
  }

  private def processDwarfMovement(unit: GameUnit): Unit = ???

  private def processTrollMovement(unit: GameUnit): Unit = ???

  private def processDwarfAttack(controllingUnit: GameUnit, attackedUnit: GameUnit): Unit = ???

  private def processTrollAttack(controllingUnit: GameUnit, attackedUnit: GameUnit): Unit = ???

  private def calculateDwarfMovement(unit: GameUnit): Unit = ???

  private def calculateTrollMovement(unit: GameUnit): Unit = ???
}

