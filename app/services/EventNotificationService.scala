package services

import com.google.inject.Singleton
import models.leafspy.LeafSpyPayloadData
import play.api.Logger

import scala.collection.mutable.ArrayBuffer

@Singleton
class EventNotificationService {
  val logger = Logger(this.getClass)

  logger.info(this.getClass + " is active.")

  private val callbacks_onLeafSpyProEvent: ArrayBuffer[(LeafSpyPayloadData) => Unit] = ArrayBuffer.empty

  def onLeafSpyProEvent(callback: (LeafSpyPayloadData) => Unit): Unit = {
    callbacks_onLeafSpyProEvent += callback
  }

  def triggerLeafSpyProEvent(data: LeafSpyPayloadData): Unit = {
    callbacks_onLeafSpyProEvent.foreach(_ (data))
  }
}
