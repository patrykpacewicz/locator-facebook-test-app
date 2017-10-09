package pl.ppac.cityfinder

import pl.ppac.cityfinder.infrastructure.repository.InMemoryCitiesRepository
import pl.ppac.cityfinder.repository.CitiesRepository

class CityFinderFacade private (citiesRepository: CitiesRepository) {
  def findCity(country: String, city: String): Cities = {
    val cities = citiesRepository.findCities(country, city)
    Cities(cities.map(toCity))
  }

  private def toCity(city: CitiesRepository.City): City = City(
    name = city.name,
    alternativeNames = city.alternateNames.toList,
    latitude = city.latitude, longitude = city.longitude
  )
}

object CityFinderFacade {

  def builder(countriesPath: String, citiesPath: String) = {
    val citiesRepository = InMemoryCitiesRepository.loadFromResourceFiles(countriesPath, citiesPath)
    new CityFinderFacade(citiesRepository)
  }

}