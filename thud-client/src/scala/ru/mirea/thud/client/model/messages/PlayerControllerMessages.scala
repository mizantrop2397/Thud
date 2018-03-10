package ru.mirea.thud.client.model.messages

import ru.mirea.thud.client.model.GameUnit

case class CalculateMovementSchemeMessage(controllingUnit: GameUnit)

case class MovementMessage(controllingUnit: GameUnit)

case class AttackMessage(controllingUnit: GameUnit, attackedUnit: GameUnit)