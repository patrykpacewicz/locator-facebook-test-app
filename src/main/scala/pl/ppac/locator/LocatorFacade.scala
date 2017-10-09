package pl.ppac.locator

import com.restfb.WebRequestor
import pl.ppac.cityfinder.CityFinderFacade
import pl.ppac.locator.infrastructure.facebook.{FacebookGraphApi, FacebookGraphApiSupplier}
import pl.ppac.locator.service.LocatorFinderService

class LocatorFacade private(locatorFinderService: LocatorFinderService) {
  def findPlaces(country: String, city: String, placeName: String): Places = {
    locatorFinderService.findPlaces(country, city, placeName)
  }
}

object LocatorFacade {
  def builder(
    cityFinderFacade: CityFinderFacade,
    facebookWebRequestor: WebRequestor, appId: String, appSecret: String,
  ) = {
    val supplier = new FacebookGraphApiSupplier(appId, appSecret, facebookWebRequestor)
    val facebookGraphApi = new FacebookGraphApi(supplier)
    val locatorFinderService = new LocatorFinderService(cityFinderFacade, facebookGraphApi)
    new LocatorFacade(locatorFinderService)
  }
}