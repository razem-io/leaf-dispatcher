package controllers

import javax.inject._
import play.api.Logger
import play.api.mvc._
import services.EventNotificationService
import utils.LeafSpyProUtil


@Singleton
class HomeController @Inject()(cc: ControllerComponents, eventNotificationService: EventNotificationService) extends AbstractController(cc) {
  val logger = Logger(this.getClass)

  def index() = Action { implicit request: Request[AnyContent] =>
    val leafSpyData = LeafSpyProUtil.fromMap(request.queryString)

    if(request.queryString.isEmpty) {
      Ok(views.html.index())
    } else if(leafSpyData.isDefined) {
      leafSpyData.foreach(eventNotificationService.triggerLeafSpyProEvent)
      Ok("status:0").withHeaders(("status", "0"))
    } else {
      logger.error("Received invalid data: " + request.queryString)
      BadRequest("Invalid data send to Server.")
    }
  }
}
