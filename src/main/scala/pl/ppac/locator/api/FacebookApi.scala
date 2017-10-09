package pl.ppac.locator.api

import com.restfb.types.Place

private[locator] trait FacebookApi {
  def findPlaces(placeName: String, centerLatitude: String, centerLongitude: String): Stream[Place]
}
