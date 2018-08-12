package models.leafspy

/**
  * user -> Vector(testuser) o user=xxx User ID
  * pass -> Vector(testpassword) o pass=xxx Password
  * PwrSw -> Vector(1) o ????
  * DevBat -> Vector(68) o DevBat=xx Device Battery %
  * Gids -> Vector(328) o Gids=xxx
  * Lat -> Vector(51.22642517089844) o Lat=xxx.xxxxx
  * Long -> Vector(6.922040939331055) o Long=xxx.xxxx
  * AHr -> Vector(114.2047) o AHr=xx.xxxx Current AHr capacity
  * ChrgPwr -> Vector(0) o ChrgPwr=xxxx Charge Power in watts
  * VIN -> Vector(SJNFAAZE1U0020184) o VIN=xxxxxxxxxxxxxxxx
  * PlugState -> Vector(0) o PlugState=x Plug State (EL)
  * SOH -> Vector(98.93) o Battery Health
  * BatTemp -> Vector(33.4) o BatTemp=xx.x Average Battery Temperature
  * Wpr -> Vector(15) o Wpr=xx Front Wiper Status
  * Trip -> Vector(1) o Trip=xx Trip number
  * Odo -> Vector(461) o odo=xxx Odometer in km
  * Amb -> Vector(73.4) o Amb=xx.x Ambient Temperature
  * Seq -> Vector(89) o Seq=xx Sequence number of transfer
  * SOC -> Vector(64.9077) o SOC=xx.xx State of Charge
  * Elv -> Vector(107.652587890625) o Elv=xxx Elevation in meters
  * RPM -> Vector(0),
  * Tunits -> Vector(C),
  * ChrgMode -> Vector(3) o ChrgMode=x Charge Mode (EM)
  */
case class LeafSpyPayloadData(
                               user: String,

                               /**
     * Device Battery in %
     */
                               deviceBattery: Double,

                               /**
     * GIDs This field indicates the remaining energy in the battery in terms of Gids. Gids is the name given to a value found
     * on the Leaf CAN bus that tracked closely with the amount of energy remaining in the Leaf Battery. It was first
     * discovered by Gary Giddings. Later it was determined that Nissan uses this field to indicate the amount of usable
     * energy in the battery by multiplying it by 77.5. On a new 2011 Leafs the maximum Gids is typically 281 or (281 x
     * 77.5) 21.78 kWh of usable energy from the 24 kWh battery. Tapping the label to the right of the value will toggle the
     * value between Gids and percent Gids.
     */
                               gids: Long,

                               /**
     * "Lat" "123 12.12345" Latitude in ddd mm.mmmmm format. Note that minutes has 5 digits of
     * precision. Warning Be sure to check that all significant digits of minutes are displayed before saving the file
     * otherwise you will lose location accuracy
     */
                               lat: Double,

                               /**
     * "Long" "-123 12.12345" Longitude in ddd mm.mmmmm format. Note that minutes has 5 digits of
     * precision. Warning Be sure to check that all significant digits of minutes are displayed before saving the file
     * otherwise you will lose location accuracy.
     */
                               long: Double,

                               /**
     * "Elv" "308" Elevation in the currently selected units (feet or meters). Elevation is received from the GPS
     * hardware in the Android device. Not from the Leaf
     */
                               elevation: Double,

                               /**
     * Sequence number of transfer
     */
                               seq: Int,

                               /**
     * Trip number
     */
                               trip: Int,

                               /**
     * Odometer reading in either miles or km depending on units selected in Settings/Units.
     */
                               odometer: Long,

                               /**
     * SOC State of Charge indicates the amount of charge currently in the battery. The amount of energy this represents is not
     * fixed but decreases over time as the battery ages. Since capacity decreases with age a new battery at 50% SOC holds more
     * energy than a 5 year old battery at 50% SOC.
     * Note: The SOC displayed here is not the same as the one displayed on the dash of later model Leafs. What is displayed
     * by LeafSpy Pro is the true SOC as reported by the battery management computer. The one on the dash is adjusted to
     * respond like a traditional gas gauge. The dash gauge will show 100% and 0% whereas the real SOC never reaches those
     * values in order to protect the battery and extend its life
     */
                               stateOfCharge: Double,

                               /**
     * State of Health is another indication of the battery's ability to hold and release energy and is reported as a
     * percentage. When the battery is new SOH=100%. When SOH drops to 85% for a month or so the Leaf loses the first
     * capacity level segment displayed on the right side of the Leaf's central dash display. The capacity gauge is the rightmost
     * outer curved column of segments next to "1" at the top and "0" at the bottom with the last two segments being colored red
     */
                               stateOfHealth: Double,

                               /**
     * Ampere/Hour is a rating of the present battery capacity. This is determined by the Battery Management Controller in
     * the Leaf and decreases as the battery ages. It gives an estimate of the battery's capacity to hold energy when fully charged,
     * not how much energy is currently in the battery. Think of it as the size of the tank not how much is currently in the tank.
     * As the battery ages this number will decrease indicating that less and less energy can be stored in the battery when fully
     * charged. If you have a laptop you have seen this happen. The amount of time the laptop can run on a fully charged battery
     * decreases as the batteries get older.
     */
                               ampereHourRating: Double,

                               /**
     * Average Battery Temperature
     */
                               batteryTemp: Double,

                               /**
     * Ambient Temperature
     */
                               ambientTemp: Double,

                               /**
     * "Front Wiper Status" "08" This field is the status of the front wipers. The value is saved in hexadecimal
     * format for easier reading.
     * o 80 = Front Wiper High
     * o 40 = Front Wiper Low
     * o 20 = Front Wiper Switch
     * o 10 = Front Wiper Intermittent
     * o 08 = Front Wiper Stopped
     */
                               frontWiperStatus: Int,

                               /**
     * "Plug State" "2" Plug state of J1772 (L1/L2) charge port.
     * o 0= Not plugged
     * o 1= Partial Plugged
     * o 2= Plugged
     */
                               plugState: Int,

                               /**
     * "Charge Mode" "2" Charging mode.
     * o 0= Not charging
     * o 1= Level 1 charging (100-120 volts)
     * o 2= Level 2 charging (200-240 volts)
     * o 3= Level 3 Quick Charging
     */
                               chargeMode: Int,

                               /**
     * "Chrg Pwr" "3300" Charging power coming into the Leaf in watts.
     */
                               chargePower: Double,

                               /**
     *  Car Identifier
     */
                               vin: String,

                               /**
     * Tunits -> Vector(C) ??? Maybe C / F
     */
                               tUnits: String,

                               /**
     * "Power SW" "1" This field will be "1" if Leaf Spy has read the Power Switch and found it active otherwise it
     * will be "0".
     */
                               powerSW: Int,

                               /**
     * Motor RPM ???
     */
                               rpm: Double
)
