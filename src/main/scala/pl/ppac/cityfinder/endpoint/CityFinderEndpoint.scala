package pl.ppac.cityfinder.endpoint

import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}
import pl.ppac.cityfinder.{Cities, CityFinderFacade}

@RestController
@RequestMapping(Array("/cityfinder"))
private[cityfinder] class CityFinderEndpoint(cityFinderFacade: CityFinderFacade) {
  @GetMapping(Array("/{country}/{city}"))
  def findCity(@PathVariable country: String, @PathVariable city: String): Cities = {
    cityFinderFacade.findCity(country, city)
  }
}
