package devmo

import akka.http.scaladsl.server.Route
import devmo.sandbox.{PingRoute, Sys, SysProvider}
import devmo.sandbox.services.PingSlice

trait HttpService {
  def routes: Route
}

trait HttpServiceSlice {
  this: Sys =>
  import akka.http.scaladsl.server.Directives._

  val httpService: HttpService = new SysProvider with HttpService
    with PingRoute with PingSlice {
    override val routes: Route = pathPrefix("v1") {
      pingRoute
    }
  }
}
