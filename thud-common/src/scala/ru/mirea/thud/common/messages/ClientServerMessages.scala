package ru.mirea.thud.common.messages

case class ClientServerMessages()

case class PlayerConnectionMessage(playerName: String)

case class PlayerDisconnectionMessage(playerName: String)

case class PlayerActionNotificationMessage()

case class DrawOfferingMessage()
