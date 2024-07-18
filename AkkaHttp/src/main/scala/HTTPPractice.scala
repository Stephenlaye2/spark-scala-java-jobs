import akka.actor.ActorSystem
import akka.http.scaladsl.Http
//import akka.http.scaladsl.model.Uri.Path.~
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.PathMatchers.IntNumber
import akka.http.scaladsl.server.Route

import scala.language.postfixOps
import scala.concurrent.ExecutionContextExecutor

  object HTTPPractice extends App {
    // Create an ActorSystem to host the application in
    implicit val system: ActorSystem = ActorSystem("mySystem")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val r: Route = {
      pathPrefix("hello") {
        path("world") {
          complete("Done1")
        } ~
          path(IntNumber) { x =>
            complete(s"Done2 with number $x")
          }
      }
    }

    // Bind the route to localhost and port 8180
    Http().newServerAt("localhost", 8180).bind(r)
    println("server started at 8180")
  }

//  "What is the output for the URL http://localhost:8180/ ?"

