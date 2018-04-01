package ru.mirea.thud.common.model

import ru.mirea.thud.client.model.GameUnit

case class GameField(units: Map[Location, GameUnit] = Map())
