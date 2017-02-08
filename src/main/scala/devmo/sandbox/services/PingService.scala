package devmo.sandbox.services

import devmo.sandbox.Sys
import devmo.sandbox.services.PingProtocol.HealthStatus
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

import scala.concurrent.Future

object PingProtocol extends DefaultJsonProtocol {
  case class HealthStatus(name: String, status: String, info: String)

  implicit val healthStatusFormat: RootJsonFormat[HealthStatus] = jsonFormat3(HealthStatus)
}

trait PingService {
  def ping: Future[List[HealthStatus]]
}

trait PingSlice {
  this: Sys =>
  val pingService: PingService = new PingService {
    private val serviceCheck = Future(config.version)

    override def ping: Future[List[HealthStatus]] = {
      val fs = Map("version" -> serviceCheck).map({
        case (name, check) =>
          check map {
            info =>
              HealthStatus(name, "UP", info)
          } recover {
            case e =>
              HealthStatus(name, "DOWN", e.getMessage)
          }
      })

      Future.sequence(fs).map(_.toList)
    }
  }
}
