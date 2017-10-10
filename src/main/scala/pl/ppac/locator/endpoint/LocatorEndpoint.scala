package pl.ppac.locator.endpoint

import org.springframework.http.HttpStatus.{INTERNAL_SERVER_ERROR, NOT_FOUND}
import org.springframework.web.bind.annotation._
import pl.ppac.cityfinder.{CityNotFound, CountryNotFound}
import pl.ppac.locator.{FbConnectionError, LocatorFacade}
import pl.ppac.support.Errors

@RestController
@RequestMapping(Array("/locator"))
private[locator] class LocatorEndpoint(locatorFacade: LocatorFacade) {
  @GetMapping(Array("/{country}/{city}/{description}"))
  def getLocationInfo(@PathVariable country: String, @PathVariable city: String, @PathVariable description: String) = {
    locatorFacade.findPlaces(country, city, description)
  }

  @ExceptionHandler(Array(classOf[CountryNotFound], classOf[CityNotFound]))
  @ResponseStatus(value = NOT_FOUND)
  def notFoundExceptionHandler(ex: Exception) = Errors.from(ex)


  @ExceptionHandler(Array(classOf[FbConnectionError]))
  @ResponseStatus(value = INTERNAL_SERVER_ERROR)
  def internalServerErrorExceptionHandler(ex: Exception) = Errors.from(ex)

}
