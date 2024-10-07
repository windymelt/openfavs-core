package dev.capslock.openfavs.application

import cats.effect.IO

object HelloWorld {
  def helloWorld(user: String): IO[Either[Unit, String]] =
    IO.pure(Right(s"Hello, $user!"))

  def goodBye(user: String): IO[Either[Unit, String]] =
    IO.pure(Right(s"Goodbye, $user!"))
}
