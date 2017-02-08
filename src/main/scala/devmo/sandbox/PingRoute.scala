package devmo.sandbox

import akka.http.scaladsl.server.Route
import devmo.sandbox.services.PingSlice

trait PingRoute {
  this: PingSlice with Sys =>
  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import akka.http.scaladsl.server.Directives._

  val pingRoute: Route = get {
    import devmo.sandbox.services.PingProtocol._
    path("ping") {
      pathEndOrSingleSlash {
        complete(pingService.ping)
      }
    }
  }
}
