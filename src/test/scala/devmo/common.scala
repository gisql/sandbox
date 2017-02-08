package devmo

import akka.actor.ActorSystem
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.stream.{ActorMaterializer, Materializer}
import devmo.sandbox.Sys
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.prop.{Checkers, GeneratorDrivenPropertyChecks}
import org.scalatest.time.{Millis, Span}
import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

abstract class NoDbTestBase extends WordSpec with Matchers with ScalaFutures with Checkers with GeneratorDrivenPropertyChecks {
  implicit val pc = PatienceConfig(timeout = scaled(Span(15000, Millis)), interval = scaled(Span(100, Millis)))
  protected def ras(path: String): String = Source.fromInputStream(getClass.getResourceAsStream(path)).mkString
}

abstract class SysTestBase extends NoDbTestBase with Sys {
  override implicit val system: ActorSystem = ActorSystem("test")
  override implicit val materializer: Materializer = ActorMaterializer()
}

abstract class RouteTestBase extends NoDbTestBase with ScalatestRouteTest
