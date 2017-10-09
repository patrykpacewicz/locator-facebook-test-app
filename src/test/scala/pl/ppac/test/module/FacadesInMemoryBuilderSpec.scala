package pl.ppac.test.module

import org.scalatest.Suite
import pl.ppac.cityfinder.CityFinderFacade
import pl.ppac.locator.LocatorFacade

trait FacadesInMemoryBuilderSpec extends FacebookWebRequestorMock {
  this: Suite =>

  val cityFinderFacade = CityFinderFacade.builder("geonames/countryInfo.txt", "geonames/pl-cities1000.txt")
  val locatorFacade = LocatorFacade.builder(cityFinderFacade, facebookWebRequestor, "test-app-id", "test-app-secret")
}
