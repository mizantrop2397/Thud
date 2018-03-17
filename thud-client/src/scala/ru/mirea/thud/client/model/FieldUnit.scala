package ru.mirea.thud.client.model

import ru.mirea.thud.client.constants.FieldCellType

case class FieldUnit(location: Location, cellType: FieldCellType.Value, neighbors: List[FieldUnit])
