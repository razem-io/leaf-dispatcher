package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    val data = request.queryString

    if(data.isEmpty) Ok(views.html.index())
    else Ok("status:0")
  }
}

/**
  * Map(
  * PwrSw -> Vector(1),
  * AHr -> Vector(114.2047),
  * ChrgPwr -> Vector(0),
  * VIN -> Vector(SJNFAAZE1U0020184),
  * PlugState -> Vector(0),
  * Lat -> Vector(51.22642517089844),
  * SOH -> Vector(98.93),
  * BatTemp -> Vector(33.4),
  * DevBat -> Vector(68),
  * Wpr -> Vector(15), Trip -> Vector(1),
  * Odo -> Vector(461),
  * Long -> Vector(6.922040939331055),
  * Amb -> Vector(73.4),
  * Seq -> Vector(89),
  * Gids -> Vector(328),
  * SOC -> Vector(64.9077),
  * pass -> Vector(testpassword),
  * Elv -> Vector(107.652587890625),
  * RPM -> Vector(0),
  * Tunits -> Vector(C),
  * ChrgMode -> Vector(3),
  * user -> Vector(testuser))
  */
