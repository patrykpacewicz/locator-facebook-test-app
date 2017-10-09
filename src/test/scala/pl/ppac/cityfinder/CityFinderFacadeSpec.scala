package pl.ppac.cityfinder

import pl.ppac.test.UnitFlatSpec

class CityFinderFacadeSpec extends UnitFlatSpec {
  it should "find Luboń in Poland using name field" in {
    // given
    val country = "Poland"
    val city = "Luboń"
    val altNames = List(
      "Liubonis", "Ljubon", "Ljubon'", "Lubon", "Lubona", "Luboń", "Luboņa",
      "Luboň", "lu bang", "Лубоњ", "Любон", "Любонь", "盧邦"
    )
    val expected = Cities(List(City("Luboń", altNames, "52.34705", "16.89267")))

    // when
    val result = cityFinderFacade.findCity(country, city)

    // then
    result should equal(expected)
  }

  it should "find Luboń in Poland using asciiname field" in {
    // given
    val country = "Poland"
    val city = "Lubon"
    val altNames = List(
      "Liubonis", "Ljubon", "Ljubon'", "Lubon", "Lubona", "Luboń", "Luboņa",
      "Luboň", "lu bang", "Лубоњ", "Любон", "Любонь", "盧邦"
    )
    val expected = Cities(List(City("Luboń", altNames, "52.34705", "16.89267")))

    // when
    val result = cityFinderFacade.findCity(country, city)

    // then
    result should equal(expected)
  }

  it should "find Luboń in Poland using alternatenames field" in {
    // given
    val country = "Poland"
    val city = "Liubonis"
    val altNames = List(
      "Liubonis", "Ljubon", "Ljubon'", "Lubon", "Lubona", "Luboń", "Luboņa",
      "Luboň", "lu bang", "Лубоњ", "Любон", "Любонь", "盧邦"
    )
    val expected = Cities(List(City("Luboń", altNames, "52.34705", "16.89267")))

    // when
    val result = cityFinderFacade.findCity(country, city)

    // then
    result should equal(expected)
  }

  it should "not find non existing country" in {
    // given
    val country = "BluBluBlur"
    val city = ""

    assertThrows[CountryNotFound] {
      // when
      cityFinderFacade.findCity(country, city)
    }
  }

  it should "not find Poznań in Argentina" in {
    // given
    val country = "Argentina"
    val city = "Poznań"

    assertThrows[CityNotFound] {
      // when
      cityFinderFacade.findCity(country, city)
    }
  }

  it should "find two Dąbie in Poland" in {
    // given
    val country = "Poland"
    val city = "Dąbie"

    val altNames1 = List("Dabie", "Dombe", "Dąbie", "Gmina Dabie", "Gmina Dombe", "Gmina Dąbie", "Домбе", "Ґміна Домбе")
    val altNames2 = List("Dabie", "Dąbie")

    val expected = Cities(List(City("Dąbie", altNames1, "52.08668", "18.8225"), City("Dąbie", altNames2, "52.01056", "15.15221")))

    // when
    val result = cityFinderFacade.findCity(country, city)

    // then
    result should equal(expected)
  }
}
