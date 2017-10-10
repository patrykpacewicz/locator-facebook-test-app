package pl.ppac.cityfinder.endpoint

import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation._
import pl.ppac.cityfinder._
import pl.ppac.support.Errors

@RestController
@RequestMapping(Array("/cityfinder"))
private[cityfinder] class CityFinderEndpoint(cityFinderFacade: CityFinderFacade) {
  @GetMapping(Array("/{country}/{city}"))
  def findCity(@PathVariable country: String, @PathVariable city: String): Cities = {
    cityFinderFacade.findCity(country, city)
  }

  @ExceptionHandler(Array(classOf[CountryNotFound], classOf[CityNotFound]))
  @ResponseStatus(value = NOT_FOUND)
  def exceptionHandler(ex: Exception) = Errors.from(ex)
}
