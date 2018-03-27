package ru.mirea.thud.common.model

import ru.mirea.thud.common.constants.PlayerRole

case class GameUnit(location: Location, unitType: PlayerRole.Value, neighbors: List[GameUnit])
