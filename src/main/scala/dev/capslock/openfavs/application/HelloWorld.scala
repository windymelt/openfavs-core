package dev.capslock.openfavs
package application

import cats.effect.IO

class HelloWorld(db: DB) {
  def helloWorld(user: String): IO[Either[Unit, String]] =
    IO.pure(Right(s"Hello, $user!"))

  def goodBye(user: String): IO[Either[Unit, String]] =
    IO.pure(Right(s"Goodbye, $user!"))

  def randomUuid(unit: Unit): IO[Either[Unit, String]] = db.session.use {
    session =>
      import skunk._
      import skunk.implicits._
      import skunk.codec.all._

      session
        .unique(sql"SELECT gen_random_uuid()".query(uuid).map(_.toString)).map(
          Right(_),
        )
  }
}
