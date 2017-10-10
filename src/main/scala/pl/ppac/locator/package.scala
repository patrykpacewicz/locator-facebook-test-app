package pl.ppac

package object locator {

  case class Places(places: List[Place])

  case class Place(name: String, latitude: Double, longitude: Double)

  case class FbConnectionError(message: String) extends Exception(message)
}
