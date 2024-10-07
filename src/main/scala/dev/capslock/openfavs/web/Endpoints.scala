package dev.capslock.openfavs.web

import sttp.tapir.*
import cats.effect.IO
import org.http4s.HttpRoutes

object Endpoints {
  val helloWorldEndpoint =
    endpoint.in("hello").in(path[String]("name")).out(stringBody)

  val goodByeEndpoint =
    endpoint.in("goodbye").in(path[String]("name")).out(stringBody)

  val randomUuidEndpoint: Endpoint[Unit, Unit, Unit, String, Any] =
    endpoint.in("random-uuid").out(stringBody)
}
