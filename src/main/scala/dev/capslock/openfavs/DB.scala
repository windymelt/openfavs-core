package dev.capslock.openfavs

import cats.effect._
import skunk._
import skunk.implicits._
import skunk.codec.all._
import natchez.Trace.Implicits.noop

class DB(conf: DatabaseConfig) {
  val session = Session.single[IO](
    host = conf.host,
    port = conf.port,
    user = conf.user,
    database = conf.database,
    password = conf.password,
  )
}
