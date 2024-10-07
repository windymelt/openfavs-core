package dev.capslock.openfavs.web

trait RoutingSyntax {
  import org.http4s.HttpRoutes
  import sttp.tapir.server.http4s.Http4sServerInterpreter
  import sttp.tapir.Endpoint
  import sttp.tapir.server.ServerEndpoint
  import cats.syntax.all.{*, given}
  import cats.effect.IO

  protected type SE[I, EO, O] =
    ServerEndpoint.Full[Unit, Unit, I, EO, O, Any, IO]

  private var routes: List[HttpRoutes[IO]] = List.empty

  extension [I, EO, O](ep: Endpoint[Unit, I, EO, O, Any]) {
    inline def ==>(logic: I => IO[Either[EO, O]]): Unit = {
      val sep: SE[I, EO, O] = ep.serverLogic[IO](logic)
      val r = Http4sServerInterpreter[IO]().toRoutes(
        sep,
      )
      routes = r :: routes
    }
  }

  lazy val allRoutes: HttpRoutes[IO] = routes.foldK
}
