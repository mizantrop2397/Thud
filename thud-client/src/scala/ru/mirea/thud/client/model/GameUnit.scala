package ru.mirea.thud.client.model

import ru.mirea.thud.client.constants.PlayerRole

case class GameUnit(location: Location, unitType: PlayerRole.Value, neighbors: List[GameUnit])
