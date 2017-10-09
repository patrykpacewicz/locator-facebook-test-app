package pl.ppac.cityfinder.repository

private[cityfinder] trait CitiesRepository {
  def findCities(country: String, city: String): List[CitiesRepository.City]
}

private[cityfinder] object CitiesRepository {
  case class City(
    geonameId: String, name: String, asciiname: String, alternateNames: Array[String],
    latitude: String, longitude: String, countryCode: String
  )
}