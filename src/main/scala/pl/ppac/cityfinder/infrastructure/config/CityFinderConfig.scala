package pl.ppac.cityfinder.infrastructure.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import pl.ppac.cityfinder.CityFinderFacade

@Configuration
private[cityfinder] class CityFinderConfig {
  @Bean
  def cityFinderFacade(
    @Value("${locator.geonames.countries}") countriesPath: String,
    @Value("${locator.geonames.cities}") citiesPath: String
  ): CityFinderFacade = {
    CityFinderFacade.builder(countriesPath, citiesPath)
  }
}
