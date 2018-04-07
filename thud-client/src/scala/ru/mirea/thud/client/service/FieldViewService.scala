package ru.mirea.thud.client.service

import akka.actor.Actor
import ru.mirea.thud.client.controller.FieldViewController.highlightCells
import ru.mirea.thud.client.model.messages.HighlightCellsMessage
import scalafx.application.Platform.runLater

class FieldViewService extends Actor {
  override def receive: Receive = {
    case HighlightCellsMessage(cells) => runLater {
      highlightCells(cells)
    }
  }
}
