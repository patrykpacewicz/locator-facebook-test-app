package pl.ppac.test

import org.scalatest._
import pl.ppac.test.module.FacadesInMemoryBuilderSpec

abstract class UnitFlatSpec
  extends FlatSpec
    with BeforeAndAfter
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Matchers
    with FacadesInMemoryBuilderSpec