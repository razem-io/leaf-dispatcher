package test

object TestCommons {
  val leafSpyProDataMap: Map[String, Seq[String]] = Map("PwrSw" -> Vector("1"), "AHr" -> Vector("114.2047"), "ChrgPwr" -> Vector("0"), "VIN" -> Vector("SJNFAAZE1U0020184"), "PlugState" -> Vector("0"), "Lat" -> Vector("51.24433517456055"), "SOH" -> Vector("98.93"), "BatTemp" -> Vector("33.2"), "Wpr" -> Vector("15"), "Trip" -> Vector("3"), "Odo" -> Vector("500"), "Long" -> Vector("6.768471717834473"), "Amb" -> Vector("72.5"), "Seq" -> Vector("543"), "Gids" -> Vector("325"), "SOC" -> Vector("64.4632"), "pass" -> Vector("testpassword"), "DevBat" -> Vector("100"), "Elv" -> Vector("92.4735107421875"), "RPM" -> Vector("0"), "Tunits" -> Vector("C"), "ChrgMode" -> Vector("0"), "user" -> Vector("testuser"))
  val leafSpyProUrlParams: String = "?" + leafSpyProDataMap
      .mapValues(_.head)
      .map(s => s._1 + "=" + s._2)
      .mkString("&")
}
