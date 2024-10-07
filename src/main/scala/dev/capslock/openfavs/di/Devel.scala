package dev.capslock.openfavs
package di

import wvlet.airframe.Design

object Devel extends Environment {
  val design = Design.newDesign
    .bind[web.Web].toSingleton
}
