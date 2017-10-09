package pl.ppac.cityfinder.infrastructure.repository

import pl.ppac.cityfinder.infrastructure.geonames.FileLoader
import pl.ppac.cityfinder.infrastructure.repository.InMemoryCitiesRepository._
import pl.ppac.cityfinder.repository.CitiesRepository
import pl.ppac.cityfinder.repository.CitiesRepository.City
import pl.ppac.cityfinder.{CityNotFound, CountryNotFound}

private[cityfinder] class InMemoryCitiesRepository(
  cities: Map[CountryName, Map[CityName, List[City]]]
) extends CitiesRepository {
  override def findCities(country: String, city: String): List[City] = {
    cities.get(normalize(country)) match {
      case None => throw CountryNotFound(country)
      case Some(citiesMap) => citiesMap.get(normalize(city)) match {
        case None => throw CityNotFound(country, city)
        case Some(result) => result
      }
    }
  }
}

private[cityfinder] object InMemoryCitiesRepository {
  type CountryName = String
  type CityName = String
  type IsoCode = String

  def loadFromResourceFiles(countriesPath: String, citiesPath: String) = {
    val countries = FileLoader.loadFromResourceFile(countriesPath).map(countryToTuple).toMap
    val cities = FileLoader.loadFromResourceFile(citiesPath).map(cityToCity).toList
    val citiesByIso = cities.groupBy(c => normalize(c.countryCode))

    val data = countries.map { case (countryName, iso) =>
      countryName -> citiesByIso.getOrElse(iso, List())
        .flatMap(c => c.alternateNames.map(_ -> c))
        .groupBy(c => normalize(c._1))
        .map(c => c._1 -> c._2.map(x => x._2))
    }

    new InMemoryCitiesRepository(data)
  }

  private def countryToTuple(data: Array[String]): (CountryName, IsoCode) = normalize(data(4)) -> normalize(data(0))

  private def normalize(in: String): String = in.trim.toLowerCase

  private def cityToCity(data: Array[String]): City = City(
    geonameId = data(0),
    name = data(1),
    asciiname = data(2),
    alternateNames = (data(3).split(',') :+ data(2) :+ data(1)).distinct.filter(_.nonEmpty),
    latitude = data(4),
    longitude = data(5),
    countryCode = data(8)
  )
}