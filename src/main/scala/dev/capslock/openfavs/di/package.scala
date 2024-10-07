package dev.capslock.openfavs

import wvlet.airframe.Design
import cats.effect.std.Dispatcher
import cats.effect.IO

package object di {
  trait Environment {
    val design: Design
  }
}
