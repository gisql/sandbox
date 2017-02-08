package devmo.sandbox

import devmo.SysTestBase
import devmo.sandbox.services.PingSlice

class PingServiceTest extends SysTestBase with PingSlice {
  "Ping Service" should {
    "return version and status of database" in {
      whenReady(pingService.ping) {
        res =>
          res.map(_.name).head shouldBe "version"
      }
    }
  }
}
