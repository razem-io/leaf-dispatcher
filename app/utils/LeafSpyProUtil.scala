package utils

import models.leafspy.LeafSpyPayloadData

object LeafSpyProUtil {

  def fromMap(queryData: Map[String, Seq[String]]): Option[LeafSpyPayloadData] =  {
    val d = queryData.mapValues(_.head)

    for {
      pwrSw <- d.get("PwrSw").map(_.toInt)
      aHr <- d.get("AHr").map(_.toDouble)
      chrgPwr <- d.get("ChrgPwr").map(_.toDouble)
      vin <- d.get("VIN")
      plugState <- d.get("PlugState").map(_.toInt)
      lat <- d.get("Lat").map(_.toDouble)
      soh <- d.get("SOH").map(_.toDouble)
      batTemp <- d.get("BatTemp").map(_.toDouble)
      wpr <- d.get("Wpr").map(_.toInt)
      trip <- d.get("Trip").map(_.toInt)
      odo <- d.get("Odo").map(_.toLong)
      long <- d.get("Long").map(_.toDouble)
      amb <- d.get("Amb").map(_.toDouble)
      seq <- d.get("Seq").map(_.toInt)
      gids <- d.get("Gids").map(_.toLong)
      soc <- d.get("SOC").map(_.toDouble)
      devBat <- d.get("DevBat").map(_.toDouble)
      elv <- d.get("Elv").map(_.toDouble)
      rpm <- d.get("RPM").map(_.toDouble)
      tunits <- d.get("Tunits")
      chrgMode <- d.get("ChrgMode").map(_.toInt)
      user <- d.get("user")
    } yield LeafSpyPayloadData(
      user = user,
      deviceBattery = devBat,
      gids = gids,
      lat = lat,
      long = long,
      powerSW = pwrSw,
      ampereHourRating = aHr,
      chargePower = chrgPwr,
      vin = vin,
      plugState = plugState,
      stateOfHealth = soh,
      batteryTemp = batTemp,
      frontWiperStatus = wpr,
      trip = trip,
      odometer = odo,
      ambientTemp = amb,
      seq = seq,
      stateOfCharge = soc,
      elevation = elv,
      rpm = rpm,
      tUnits = tunits,
      chargeMode = chrgMode
    )
  }
}
