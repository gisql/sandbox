package devmo.sandbox

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import devmo.HttpServiceSlice

object Main extends App with HttpServiceSlice with Sys {
  override implicit lazy val system = ActorSystem()
  override implicit lazy val materializer = ActorMaterializer()
  private lazy val logger = Logging.getLogger(system, this)

  try {
    logger.info(s"Starting Sandbox Service ${config.version}")
    logger.info(s"Starting HTTP service on ${config.httpInterface}: ${config.httpPort}")
    Http().bindAndHandle(httpService.routes, config.httpInterface, config.httpPort) onFailure {
      case e =>
        logger.error(e, "Failed to bind HTTP service.  Shutting down.")
        system.terminate()
    }
  } catch {
    case e: Throwable =>
      logger.error(e, "During initialisation.  Shutting down.")
      system.terminate()
  }
}
