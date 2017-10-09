package pl.ppac.locator.infrastructure.facebook

import com.restfb._

private[locator] class FacebookGraphApiSupplier(
  private val appId: String,
  private val appSecret: String,
  private val webRequestor: WebRequestor
) {
  def get: FacebookClient = {
    val version = Version.VERSION_2_10
    val token = new DefaultFacebookClient("", webRequestor, new DefaultJsonMapper(), version).obtainAppAccessToken(appId, appSecret)
    new DefaultFacebookClient(token.getAccessToken, webRequestor, new DefaultJsonMapper(), version)
  }
}