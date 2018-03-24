package ru.mirea.thud.common.messages

import ru.mirea.thud.common.model.PlayerConnectionInfo

case class PlayerConnectionMessage(info : PlayerConnectionInfo)

case class PlayerDisconnectionMessage(sessionId: String)

case class PlayerActionNotificationMessage()

case class DrawOfferingMessageToServer(sessionId: String, playerId: String)

case class DrawOfferingMessageToClient()
