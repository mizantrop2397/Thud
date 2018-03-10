package ru.mirea.thud.client.model

import ru.mirea.thud.client.constants.GameUnitType

case class GameUnit(location: Location, unitType: GameUnitType.Value, neighbors: List[GameUnit])
