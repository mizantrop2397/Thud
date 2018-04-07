package ru.mirea.thud.client.loader

import java.lang.Thread._

import javafx.{fxml => jfxf, scene => jfxs}

object ViewLoader {
  def loadDialogSuggestToOffer(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/DialogSuggestToOffer.fxml"))

  def loadExitDialog(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/ExitDialogView.fxml"))

  def loadGameScreenViewForm(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/GameScreenViewForm.fxml"))

  def loadHelpViewForm(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/HelpViewForm.fxml"))

  def loadNotificationDialogView(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/NotificationDialogView.fxml"))

  def loadStartViewForm(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/StartViewForm.fxml"))

  def loadWinNotificationDialog(): jfxs.Parent = jfxf.FXMLLoader.load(currentThread.getContextClassLoader.getResource("fxml/WinNotificationDialog.fxml"))
}
