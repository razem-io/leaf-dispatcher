package modules

import com.google.inject.AbstractModule
import services.ElasticSearchLeafSpyProService

class ElasticSearchLeafSpyProModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[ElasticSearchLeafSpyProService]).asEagerSingleton()
  }
}
