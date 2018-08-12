package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import test.TestCommons

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "HomeController GET" should {

    "without params it should render a welcome page" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "with params it should return a valid status" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/" + TestCommons.leafSpyProUrlParams))

      println(TestCommons.leafSpyProUrlParams)

      status(home) mustBe OK
      contentType(home) mustBe Some("text/plain")
      contentAsString(home) must include ("status:0")
    }
  }
}
