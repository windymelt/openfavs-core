package dev.capslock.openfavs

import pureconfig._
import pureconfig.generic.derivation.default._
import cats.effect.IO

case class Config(
    database: DatabaseConfig,
) derives ConfigReader

case class DatabaseConfig(
    host: String,
    port: Int,
    user: String,
    database: String,
    password: Option[String],
)

object Config {
  def unsafeLoad(): Config =
    ConfigSource.default.loadOrThrow[Config]
}
