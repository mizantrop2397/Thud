package ru.mirea.thud.client.model.messages

import ru.mirea.thud.client.model.FieldUnit

case class CalculateMovementSchemeMessage(controllingUnit: FieldUnit)

case class MovementMessage(controllingUnit: FieldUnit, newCell: FieldUnit)

case class AttackMessage(attackedUnit: FieldUnit)