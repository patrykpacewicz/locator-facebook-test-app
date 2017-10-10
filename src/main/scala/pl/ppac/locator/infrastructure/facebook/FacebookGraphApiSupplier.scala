package pl.ppac.locator.infrastructure.facebook

import com.restfb._
import com.typesafe.scalalogging.LazyLogging

private[locator] class FacebookGraphApiSupplier(
  private val appId: String,
  private val appSecret: String,
  private val webRequestor: WebRequestor
) extends LazyLogging {
  def get: FacebookClient = {
    logger.info("Create new facebook client with oAuth2 token")

    val version = Version.VERSION_2_10
    val token = new DefaultFacebookClient("", webRequestor, new DefaultJsonMapper(), version).obtainAppAccessToken(appId, appSecret)
    new DefaultFacebookClient(token.getAccessToken, webRequestor, new DefaultJsonMapper(), version)
  }
}