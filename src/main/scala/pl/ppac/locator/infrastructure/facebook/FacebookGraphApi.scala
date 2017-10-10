package pl.ppac.locator.infrastructure.facebook

import com.restfb.exception.FacebookOAuthException
import com.restfb.types.Place
import com.restfb.{FacebookClient, Parameter}
import com.typesafe.scalalogging.LazyLogging
import pl.ppac.locator.FbConnectionError
import pl.ppac.locator.api.FacebookApi

import scala.collection.JavaConverters._
import scala.util._

private[locator] class FacebookGraphApi(apiSupplier: FacebookGraphApiSupplier) extends FacebookApi with LazyLogging {
  private var api: Option[FacebookClient] = None

  override def findPlaces(placeName: String, centerLatitude: String, centerLongitude: String): Stream[Place] = Try {
    val parameters = placeParameters(placeName, centerLatitude, centerLongitude)
    val places = getApi().fetchConnection("search", classOf[Place], parameters: _*)
    places.asScala.toStream.flatMap(_.asScala)
  } match {
    case Success(res) => res
    case Failure(ex: FacebookOAuthException) =>
      logger.error(ex.getMessage)
      api = None
      throw FbConnectionError(ex.getMessage)
    case Failure(ex) => throw ex
  }

  private def getApi() = api.orElse(updateApi()).get

  private def updateApi() = synchronized {
    api = Some(apiSupplier.get); api
  }

  private def placeParameters(placeName: String, centerLatitude: String, centerLongitude: String): Seq[Parameter] = Seq(
    Parameter.`with`("type", "place"),
    Parameter.`with`("fields", "name,location"),
    Parameter.`with`("distance", "65000"),
    Parameter.`with`("center", s"$centerLatitude,$centerLongitude"),
    Parameter.`with`("q", placeName),
  )
}
