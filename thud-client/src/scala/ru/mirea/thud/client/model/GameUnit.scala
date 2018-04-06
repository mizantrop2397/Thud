package ru.mirea.thud.client.model

import ru.mirea.thud.common.constants.PlayerRole
import ru.mirea.thud.common.model.Location

//фот тут будет FieldUnit. По сути то же самое, так что ориентирйся на это, когда будет слиять, поправим
case class GameUnit(location: Location, unitType: PlayerRole.Value, neighbors: List[GameUnit])

//соседи - соседние клетки. Посмотри внимательно и х порядок в массиве. Он указан у меня в классах
