package ru.mirea.thud.client.controller

import akka.actor.Actor
import ru.mirea.thud.client.constants.GameUnitType.{DWARF, TROLL}
import ru.mirea.thud.client.model.GameUnit
import ru.mirea.thud.client.model.messages.{AttackMessage, CalculateMovementSchemeMessage, MovementMessage}

class PlayerController extends Actor {
  def processDwarfMovement(unit: GameUnit): Unit = ???

  def processTrollMovement(unit: GameUnit): Unit = ???

  def processDwarfAttack(controllingUnit: GameUnit, attackedUnit: GameUnit): Unit = ???

  def processTrollAttack(controllingUnit: GameUnit, attackedUnit: GameUnit): Unit = ???

  def calculateDwarfMovement(unit: GameUnit): Unit = ???

  def calculateTrollMovement(unit: GameUnit): Unit = ???

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
}
