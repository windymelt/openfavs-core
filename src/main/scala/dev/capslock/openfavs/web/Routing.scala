package dev.capslock.openfavs
package web

import sttp.tapir.server.http4s.Http4sServerInterpreter
import cats.effect.IO
import Endpoints.*
import cats.syntax.all.{*, given}

class Routing(helloWorldApp: application.HelloWorld) {
  val helloWorldRoute = Http4sServerInterpreter[IO]().toRoutes(
    List(
      helloWorldEndpoint.serverLogic(helloWorldApp.helloWorld),
      goodByeEndpoint.serverLogic(helloWorldApp.goodBye),
    ),
  )

  val randomRoute = Http4sServerInterpreter[IO]().toRoutes(
    List(
      randomUuidEndpoint.serverLogic(helloWorldApp.randomUuid),
    ),
  )

  val allRoutes = List(helloWorldRoute, randomRoute).foldK
}
