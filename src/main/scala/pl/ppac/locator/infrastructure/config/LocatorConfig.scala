package pl.ppac.locator.infrastructure.config

import com.restfb.DefaultWebRequestor
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.{Bean, Configuration}
import pl.ppac.cityfinder.CityFinderFacade
import pl.ppac.locator.LocatorFacade

@Configuration
private[locator] class LocatorConfig {
  @Bean
  def locatorFacade(
    cityFinderFacade: CityFinderFacade,
    @Value("${locator.facebook.appId}") appId: String,
    @Value("${locator.facebook.appSecret}") appSecret: String
  ) = {
    LocatorFacade.builder(cityFinderFacade, new DefaultWebRequestor ,appId, appSecret)
  }
}
