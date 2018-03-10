package ru.mirea.thud.client.state

import ru.mirea.thud.client.constants.PlayerRole
import ru.mirea.thud.client.model.GameUnit

case class PlayerState(id: String, name: String, score: Int, role: PlayerRole.Value)

case class Field(units: List[GameUnit])

case class GameScore(playerVictories: Int, enemyPlayerVictories: Int)

case class GameState(playerState: PlayerState, enemyPlayerState: PlayerState, gameScore: GameState, field: Field)

object GameState