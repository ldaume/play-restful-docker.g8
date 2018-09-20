package modules;

import com.google.inject.AbstractModule;
import javax.inject.Inject;
import com.typesafe.config.Config;
import lifecycle.PlayUtils;
import play.Environment;

/**
 * The default guice module for bindings.
 *
 * <p>Created by leonard on 09.03.16.
 */
public class ApplicationModule extends AbstractModule {

  private final Environment environment;
  private final Config config;

  @Inject
  public ApplicationModule(Environment environment, Config config) {
    this.environment = environment;
    this.config = config;
  }

  @Override
  protected void configure() {
    bind(PlayUtils.class).asEagerSingleton();
  }
}
