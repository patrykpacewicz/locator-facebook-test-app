package pl.ppac.test

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import pl.ppac.Application
import test.module.SpringSpec

@SpringBootTest(classes = Array(classOf[Application]))
@ActiveProfiles(Array("integration"))
abstract class IntegrationFlatSpec
  extends FlatSpec
    with BeforeAndAfter
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Matchers
    with SpringSpec
    with ScalaFutures {

  override protected def beforeEach(): Unit = {
    super.beforeEach()
  }

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    beforeAllSpring()
  }

  override protected def afterAll(): Unit = {
    super.afterAll()
    afterAllSpring()
  }
}
