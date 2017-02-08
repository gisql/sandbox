package devmo.sandbox

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.stream.Materializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor

trait Sys {
  lazy val config = new SandboxConfig {}
  implicit def system: ActorSystem
  implicit lazy val dispatcher: ExecutionContextExecutor = system.dispatcher
  implicit def materializer: Materializer
  def createLogger: LoggingAdapter = Logging.getLogger(system, this)

  sealed trait SandboxConfig {
    private val config = ConfigFactory.load()
    val httpInterface: String = config.getString("http.interface")
    val httpPort: Int = config.getInt("http.port")
    val version: String = config.getString("ping.version")
  }
}

class SysProvider(implicit val system: ActorSystem, val materializer: Materializer) extends Sys