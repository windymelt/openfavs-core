package dev.capslock.openfavs

import wvlet.airframe.Design

package object di {
  trait Environment {
    val design: Design
  }
}
