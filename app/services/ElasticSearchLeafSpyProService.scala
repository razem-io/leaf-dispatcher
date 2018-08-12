package services

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.http.ElasticDsl._
import com.sksamuel.elastic4s.http.index.{CreateIndexResponse, IndexResponse}
import com.sksamuel.elastic4s.http._
import javax.inject.{Inject, Singleton}
import models.leafspy.LeafSpyPayloadData
import org.apache.http.client.config.RequestConfig
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.joda.time.DateTime
import play.api.inject.ApplicationLifecycle
import play.api.libs.json.{JsNumber, Json, Reads, Writes}
import play.api.{Configuration, Logger}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

@Singleton
class ElasticSearchLeafSpyProService @Inject() (config: Configuration, lifecycle: ApplicationLifecycle, notificationService: EventNotificationService) (implicit ec: ExecutionContext) {
  val logger = Logger(this.getClass)


  implicit val jodaDateReads: Reads[DateTime] = Reads[DateTime](_.validate[Long].map[DateTime](dtLong => new DateTime(dtLong)))
  implicit val jodaDateWrites: Writes[DateTime] = (d: DateTime) => JsNumber(d.getMillis)
  implicit val leafSpyLocationJsonFormat = play.api.libs.json.Json.format[ElasticLocation]
  implicit val leafSpyPayloadDataJsonFormat = play.api.libs.json.Json.format[ElasticLeafSpyPayloadData]

  implicit object LeafSpyPayloadDataIndexable extends Indexable[ElasticLeafSpyPayloadData] with HitReader[ElasticLeafSpyPayloadData]{
    override def json(t: ElasticLeafSpyPayloadData): String = Json.toJson(t).toString

    override def read(hit: Hit): Try[ElasticLeafSpyPayloadData] = {
      Try{
        Json.parse(hit.sourceAsString).as[ElasticLeafSpyPayloadData]
      }
    }
  }

  val indexName = "leaf-dispatcher"
  val mappingName = "leaf-spy-pro-data"

  val host: String = config.get[String]("elastic.host")
  val port: Int = config.get[Int]("elastic.port")

  val client = ElasticClient(
    ElasticProperties(s"http://$host:$port"),
    (requestConfigBuilder: RequestConfig.Builder) => { requestConfigBuilder.setConnectionRequestTimeout(60000)},
    (httpClientBuilder: HttpAsyncClientBuilder) => { httpClientBuilder })

  init()
  def init(): Unit = {
    logger.info(this.getClass.getSimpleName + " is enabled.")

    // TODO: Handle errors
    client.execute {
      indexExists(indexName)
    }.map(r =>
      if(!r.result.exists) {
        createLeafSpyProIndex().await
      }
    ).await

    notificationService.onLeafSpyProEvent(addLeafSpyPayloadData(_))
  }

  def createLeafSpyProIndex(): Future[Response[CreateIndexResponse]] = {
    client.execute {
      createIndex(indexName).mappings(
        mapping(mappingName) as(
          dateField("date"),
          doubleField("deviceBattery"),
          longField("gids"),
          geopointField("location"),
          doubleField("elevation"),
          intField("seq"),
          intField("trip"),
          longField("odometer"),
          doubleField("stateOfCharge"),
          doubleField("stateOfHealth"),
          doubleField("ampereHourRating"),
          doubleField("batteryTemp"),
          doubleField("ambientTemp"),
          keywordField("frontWiperStatus"),
          keywordField("plugState"),
          keywordField("chargeMode"),
          doubleField("chargePower"),
          keywordField("vin"),
          keywordField("tUnits"),
          intField("powerSW"),
          doubleField("rpm"),
        )
      )
    }
  }

  def addLeafSpyPayloadData(data: LeafSpyPayloadData, refreshPolicy: RefreshPolicy = RefreshPolicy.NONE): Future[Response[IndexResponse]] =
    client.execute {
      indexInto(indexName / mappingName) doc ElasticLeafSpyPayloadData(data) refresh refreshPolicy
    }


  lifecycle.addStopHook { () =>
    Future.successful(
      client.close()
    )
  }
}

case class ElasticLocation(lat: Double, lon: Double)
case class ElasticLeafSpyPayloadData(
                                      date: DateTime,
                                      user: String,
                                      deviceBattery: Double,
                                      gids: Long,
                                      location: ElasticLocation,
                                      elevation: Double,
                                      seq: Int,
                                      trip: Int,
                                      odometer: Long,
                                      stateOfCharge: Double,
                                      stateOfHealth: Double,
                                      ampereHourRating: Double,
                                      batteryTemp: Double,
                                      ambientTemp: Double,
                                      frontWiperStatus: Int,
                                      plugState: Int,
                                      chargeMode: Int,
                                      chargePower: Double,
                                      vin: String,
                                      tUnits: String,
                                      powerSW: Int,
                                      rpm: Double
                             )
object ElasticLeafSpyPayloadData {
  def apply(d: LeafSpyPayloadData): ElasticLeafSpyPayloadData = ElasticLeafSpyPayloadData(
    DateTime.now(), d.user, d.deviceBattery, d.gids, ElasticLocation(d.lat, d.long), d.elevation, d.seq, d.trip, d.odometer, d.stateOfCharge, d.stateOfHealth, d.ampereHourRating, d.batteryTemp, d.ambientTemp, d.frontWiperStatus, d.plugState, d.chargeMode, d.chargePower, d.vin, d.tUnits, d.powerSW, d.rpm)
}
