package ru.mirea.thud.common.model

import ru.mirea.thud.common.constants.CommonConstants.EMPTY_STRING
import ru.mirea.thud.common.constants.PlayerRole
import ru.mirea.thud.common.constants.PlayerRole.TROLL

case class PlayerConnectionInfo(name: String, host: String, port: Int)

case class PlayerState(sessionId: String = EMPTY_STRING,
                       id: String = EMPTY_STRING,
                       name: String = EMPTY_STRING,
                       score: Int = 0,
                       role: PlayerRole.Value = TROLL)
