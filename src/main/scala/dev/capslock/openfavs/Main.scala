package dev.capslock.openfavs

import cats.effect.IOApp
import cats.effect.ExitCode
import cats.effect.IO
import cats.syntax.all.{*, given}
import cats.effect.kernel.Resource
import wvlet.airframe.surface.Surface

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- scribe.cats.io.info("Openfavs")
      _ <- scribe.cats.io.info(
        s"PID ${java.lang.management.ManagementFactory.getRuntimeMXBean.getName}",
      )
      design = di.Devel.design
      _ <- {
        val sessionResource = for {
          session <- Resource.eval(IO(design.newSession))
          safeSession <- Resource.make[IO, web.Web](
            IO(session.start) >> IO(session.get[web.Web](Surface.of[web.Web])),
          )(_ =>
            scribe.cats.io.info("closing session...") >> IO(session.shutdown),
          )
        } yield safeSession

        sessionResource.use { web =>
          scribe.cats.io.info("using webserver...") >> web.webServer.useForever
        }
      }
    } yield ExitCode.Success
}

class Application(w: web.Web) {
  def run: IO[Unit] = w.webServer.useForever
}
