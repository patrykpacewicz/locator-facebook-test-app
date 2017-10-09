package pl.ppac

package object cityfinder {

  case class Cities(cities: List[City])

  case class City(name: String, alternativeNames: List[String], latitude: String, longitude: String)

  case class CountryNotFound(country: String) extends Exception(s"Country $country not found")

  case class CityNotFound(country: String, city: String) extends Exception(s"City $city not found in $country")

}
