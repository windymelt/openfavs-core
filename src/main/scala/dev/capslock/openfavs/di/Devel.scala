package dev.capslock.openfavs
package di

import wvlet.airframe.Design

object Devel extends Environment {
  val design = Design.newDesign
    .bind[Config].toInstance(Config.unsafeLoad())
    .bind[DB].toProvider[Config](conf => DB(conf.database))
    .bind[application.HelloWorld].toSingleton
    .bind[web.Routing].toSingleton
    .bind[web.Web].toSingleton
}
