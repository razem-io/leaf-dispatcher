package modules

import com.google.inject.AbstractModule
import services.EventNotificationService


class LeafDispatcherModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[EventNotificationService]).asEagerSingleton()
  }
}
