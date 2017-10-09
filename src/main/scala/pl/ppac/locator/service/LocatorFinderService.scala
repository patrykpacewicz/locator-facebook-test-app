package pl.ppac.locator.service

import com.restfb.types.{Place => FbPlace}
import pl.ppac.cityfinder.{City, CityFinderFacade}
import pl.ppac.locator.api.FacebookApi
import pl.ppac.locator.{Place, Places}

private[locator] class LocatorFinderService(cityFinderFacade: CityFinderFacade, facebookApi: FacebookApi) {
  def findPlaces(country: String, city: String, placeName: String): Places = {
    val cities = cityFinderFacade.findCity(country, city).cities

    val places = cities
      .flatMap { city => findPlace(placeName, city) }
      .filter { place => inCities(place, cities) }

    Places(places.map(toPlace))
  }

  private def findPlace(placeName: String, city: City): Seq[FbPlace] = {
    facebookApi.findPlaces(placeName, city.latitude, city.longitude)
  }

  private def inCities(place: FbPlace, cities: List[City]): Boolean = {
    val placeCity = place.getLocation.getCity
    cities.exists(_.alternativeNames.contains(placeCity))
  }

  private def toPlace(place: FbPlace): Place = Place(
    place.getName, place.getLocation.getLatitude, place.getLocation.getLongitude
  )
}