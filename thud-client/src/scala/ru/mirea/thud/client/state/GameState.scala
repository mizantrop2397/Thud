package ru.mirea.thud.client.state

import ru.mirea.thud.client.constants.PlayerRole
import ru.mirea.thud.client.constants.PlayerRole.TROLL
import ru.mirea.thud.client.model.GameUnit
import ru.mirea.thud.common.constants.CommonConstants.EMPTY_STRING

case class PlayerState(var id: String = EMPTY_STRING, var name: String = EMPTY_STRING, var score: Int = 0, var role: PlayerRole.Value = TROLL)

case class Field(units: List[GameUnit] = List())

case class GameScore(var playerVictories: Int = 0, var enemyPlayerVictories: Int = 0)

case class GameState(var playerState: PlayerState = PlayerState(), var enemyPlayerState: PlayerState = PlayerState(), var gameScore: GameScore = GameScore(), var field: Field = Field())

object GameState extends GameState