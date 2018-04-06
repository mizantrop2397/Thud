package ru.mirea.thud.client.service

import ru.mirea.thud.client.app.ThudGame.config
import ru.mirea.thud.client.constants.ConfigKeys._

object ConfigService {
  private def remoteConfig = config getConfig REMOTE_CONFIG

  def hostName: String = remoteConfig getString HOST_NAME

  def port: Int = remoteConfig getInt PORT
}
