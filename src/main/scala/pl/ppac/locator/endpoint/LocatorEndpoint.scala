package pl.ppac.locator.endpoint

import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}
import pl.ppac.locator.LocatorFacade

@RestController
@RequestMapping(Array("/locator"))
private[locator] class LocatorEndpoint(locatorFacade: LocatorFacade) {
  @GetMapping(Array("/{country}/{city}/{description}"))
  def getLocationInfo(@PathVariable country: String, @PathVariable city: String, @PathVariable description: String) = {
    locatorFacade.findPlaces(country, city, description)
  }
}
