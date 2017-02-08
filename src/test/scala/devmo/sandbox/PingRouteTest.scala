package devmo.sandbox

import devmo.RouteTestBase
import devmo.sandbox.services.PingSlice

class PingRouteTest extends RouteTestBase {
  import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
  import devmo.sandbox.services.PingProtocol._

  private val pingRoute = (new SysProvider with PingRoute with PingSlice).pingRoute

  "GET /ping" should {
    "return status for DB and the service version" in {
      Get(s"/ping") ~> pingRoute ~> check {
        val hss = responseAs[List[HealthStatus]]
        hss.map(_.name).head shouldBe "version"
      }
    }
  }
}
