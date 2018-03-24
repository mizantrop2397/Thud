package ru.mirea.thud.client.state

import ru.mirea.thud.common.model.{GameField, PlayerState}


case class GameScore(var playerVictories: Int = 0, var enemyPlayerVictories: Int = 0)

case class GameState(var playerState: PlayerState = null, var enemyPlayerState: PlayerState = null, var gameScore: GameScore = GameScore(), var field: GameField = GameField())

object GameState extends GameState(playerState = null, enemyPlayerState = null, gameScore = GameScore(), field = GameField())