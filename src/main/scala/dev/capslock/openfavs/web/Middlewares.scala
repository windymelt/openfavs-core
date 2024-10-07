package dev.capslock.openfavs.web

import org.http4s.server.middleware.ErrorAction
import org.http4s.HttpRoutes
import cats.effect.kernel.Async
import cats.effect.IO
import org.http4s.server.middleware.ErrorHandling
import org.http4s.server.middleware.Logger
import org.http4s.Response
import cats.data.OptionT

object Middlewares {
  def withErrorLogging(svc: HttpRoutes[IO]) =
    ErrorHandling.Recover.total(
      ErrorAction.log(
        svc,
        messageFailureLogAction = (t, msg) =>
          OptionT.liftF(
            IO.println(msg) >>
              IO.println(t),
          ),
        serviceErrorLogAction = (t, msg) =>
          OptionT.liftF(
            IO.println(msg) >>
              IO.println(t),
          ),
      ),
    )

  def logging(svc: HttpRoutes[IO]) = Logger.httpRoutes[IO](
    logHeaders = false,
    logBody = true,
    redactHeadersWhen = _ => false,
    logAction = Some((msg: String) => scribe.cats.io.info(msg)),
  )(svc)
}
