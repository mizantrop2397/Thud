package ru.mirea.thud.client.service

import ru.mirea.thud.client.app.ThudGame.{config, provider}
import ru.mirea.thud.client.constants.ConfigKeys._

object ConfigService {
  def hostName: String = remoteConfig getString HOST_NAME

  private def remoteConfig = config getConfig REMOTE_CONFIG

  def port: Int = provider.getDefaultAddress.port.get
}
