package pl.ppac.locator.infrastructure.facebook

import com.restfb.types.Place
import com.restfb.{Connection, Parameter}
import org.springframework.stereotype.Component
import pl.ppac.locator.api.FacebookApi

import scala.collection.JavaConverters._

private[locator] class FacebookGraphApi(apiSupplier: FacebookGraphApiSupplier) extends FacebookApi {
  override def findPlaces(placeName: String, centerLatitude: String, centerLongitude: String): Stream[Place] = {
    val search: Connection[Place] = apiSupplier.get.fetchConnection("search", classOf[Place],
      Parameter.`with`("type", "place"),
      Parameter.`with`("fields", "name,location"),
      Parameter.`with`("distance", "65000"),
      Parameter.`with`("center", s"$centerLatitude,$centerLongitude"),
      Parameter.`with`("q", placeName),
    )

    search.asScala.toStream.flatMap(_.asScala)
  }
}
