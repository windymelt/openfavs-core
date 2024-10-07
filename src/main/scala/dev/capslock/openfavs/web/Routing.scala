package dev.capslock.openfavs
package web

import sttp.tapir.server.http4s.Http4sServerInterpreter
import cats.effect.IO
import Endpoints.*

object Routing {
  val helloWorldRoute = Http4sServerInterpreter[IO]().toRoutes(
    List(
      helloWorldEndpoint.serverLogic(application.HelloWorld.helloWorld),
      goodByeEndpoint.serverLogic(application.HelloWorld.goodBye),
    ),
  )
}
