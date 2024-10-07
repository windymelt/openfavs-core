package dev.capslock.openfavs
package web

import Endpoints.*

class Routing(helloWorldApp: application.HelloWorld) extends RoutingSyntax {
  helloWorldEndpoint ==> helloWorldApp.helloWorld
  goodByeEndpoint ==> helloWorldApp.goodBye
  randomUuidEndpoint ==> helloWorldApp.randomUuid
}
