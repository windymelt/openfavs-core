package dev.capslock.openfavs.web

import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
import cats.syntax.all.{*, given}
import org.http4s.HttpRoutes
import org.http4s._, org.http4s.dsl.io._
import org.http4s.ember.server.EmberServerBuilder
import com.comcast.ip4s.port
import com.comcast.ip4s.host
import Middlewares.{withErrorLogging, logging}

class Web {
  val webServer = EmberServerBuilder
    .default[IO]
    .withPort(port"8080")
    .withHost(host"0.0.0.0")
    .withHttpApp(logging(withErrorLogging(Routing.helloWorldRoute)).orNotFound)
    .build

}
