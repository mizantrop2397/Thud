package ru.mirea.thud.common.model

import ru.mirea.thud.common.constants.FieldCellType

case class FieldUnit(location: Location, var cellType: FieldCellType.Value, neighbors: List[FieldUnit])
