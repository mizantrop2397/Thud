package ru.mirea.thud.client.state

import ru.mirea.thud.common.model.{GameField, PlayerState}

case class GameScore(var playerVictories: Int = 0, var enemyPlayerVictories: Int = 0)

case class GameState(var playerState: PlayerState = PlayerState(),
                     var enemyPlayerState: PlayerState = PlayerState(),
                     var gameScore: GameScore = GameScore(),
                     var field: GameField = GameField())

object GameState extends GameState(playerState = PlayerState(), enemyPlayerState = PlayerState(), gameScore = GameScore(), field = GameField())