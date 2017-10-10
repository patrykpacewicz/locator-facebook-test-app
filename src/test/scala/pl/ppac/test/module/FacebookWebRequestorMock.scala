package pl.ppac.test.module

import java.net.HttpURLConnection.{HTTP_BAD_REQUEST, HTTP_OK}

import com.restfb.DefaultWebRequestor
import com.restfb.WebRequestor.Response

trait FacebookWebRequestorMock {
  protected val facebookWebRequestor = new DefaultWebRequestor {
    override def executeGet(url: String) = url match {
      case x if x.contains("fb-oauth-err") => new Response(HTTP_BAD_REQUEST, errorResp)
      case x if x.contains("one-resp") => new Response(HTTP_OK, oneResp)
      case x if x.contains("two-resp") => new Response(HTTP_OK, twoResp)
      case _ => new Response(HTTP_OK, zeroResp)
    }
  }
  private val errorResp =
    """{ "error": {
      |    "message": "Error validating access token: 190",
      |    "type": "OAuthException",
      |    "code": 190
      |} }""".stripMargin
  private val zeroResp = """{ "data": [ ]}""".stripMargin
  private val oneResp =
    """ { "data": [
      |    { "name": "some Poz", "location": { "city": "Poznan", "country": "Poland", "latitude": 52.0, "longitude": 16.0 } }
      | ]}""".stripMargin
  private val twoResp =
    """ { "data": [
      |    { "name": "some Waw", "location": { "city": "Warszawa", "country": "Poland", "latitude": 51.0, "longitude": 15.0 } },
      |    { "name": "some Poz", "location": { "city": "Poznan", "country": "Poland", "latitude": 52.0, "longitude": 16.0 } }
      | ]}""".stripMargin

}
