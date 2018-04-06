package ru.mirea.thud.client.service

import ru.mirea.thud.client.app.ThudGame.config
import ru.mirea.thud.client.constants.ConfigKeys._

object ConfigService {
  def hostName: String = remoteConfig getString HOST_NAME

  private def remoteConfig = config getConfig REMOTE_CONFIG

  def port: Int = remoteConfig getInt PORT
}
