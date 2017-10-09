package pl.ppac.locator

import pl.ppac.test.UnitFlatSpec

class LocatorFacadeSpec extends UnitFlatSpec {
  it should "find allegro in Poznań" in {
    // given
    val country = "Poland"
    val city = "Poznań"
    val query = "one-resp"
    val expected = Places(List(Place("some Poz", 52.0, 16.0)))

    // when
    val results = locatorFacade.findPlaces(country, city, query)

    // then
    results should equal(expected)
  }

  it should "find nothing when query is incorrect" in {
    // given
    val country = "Poland"
    val city = "Poznań"
    val query = "bleblebleblebleblebleblebleble"
    val expected = Places(List())

    // when
    val results = locatorFacade.findPlaces(country, city, query)

    // then
    results should equal(expected)
  }

  it should "filter out results from different cities" in {
    // given
    val country = "Poland"
    val city1 = "Poznań"
    val city2 = "Warszawa"
    val query = "two-resp"
    val expected1 = Places(List(Place("some Poz", 52.0, 16.0)))
    val expected2 = Places(List(Place("some Waw", 51.0, 15.0)))

    // when
    val results1 = locatorFacade.findPlaces(country, city1, query)
    val results2 = locatorFacade.findPlaces(country, city2, query)

    // then
    results1 should equal(expected1)
    results2 should equal(expected2)
  }
}
