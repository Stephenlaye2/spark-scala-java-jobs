import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.Future
import scala.util.{Failure, Success}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

  // Define your case class outside of the object to ensure visibility
  case class JokeResponse(categories: Seq[String], created_at: String, icon_url: String, id: String, updated_at: String, url: String, value: String)

  // Define your JsonFormats object
  object JsonFormats extends DefaultJsonProtocol with SprayJsonSupport {
    implicit val jokeResponseFormat = jsonFormat7(JokeResponse.apply)
  }

  import JsonFormats._ // Import the implicit JsonFormat for JokeResponse

  object AkkaHttpJob extends App {
    implicit val system = ActorSystem()
    import system.dispatcher // ExecutionContext for Futures

    def fetchJoke(url: String): Future[JokeResponse] = {
      Http().singleRequest(HttpRequest(uri = url)).flatMap { response =>
        Unmarshal(response.entity).to[JokeResponse] // Unmarshal JSON to the JokeResponse case class
      }
    }

    val url = "https://api.chucknorris.io/jokes/random" // Use your endpoint URL
    fetchJoke(url).onComplete {
      case Success(jokeResponse) =>
        println(s"${jokeResponse.value}")
        system.terminate() // Correctly terminate the ActorSystem
      case Failure(exception) =>
        println(s"Request failed: ${exception.getMessage}")
        system.terminate() // Correctly terminate the ActorSystem
    }

  }

