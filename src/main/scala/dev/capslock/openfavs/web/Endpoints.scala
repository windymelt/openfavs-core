package dev.capslock.openfavs.web

import sttp.tapir.*

object Endpoints {
  val helloWorldEndpoint =
    endpoint.in("hello").in(path[String]("name")).out(stringBody)

  val goodByeEndpoint =
    endpoint.in("goodbye").in(path[String]("name")).out(stringBody)

  val randomUuidEndpoint =
    endpoint.in("random-uuid").out(stringBody)
}
