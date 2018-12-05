package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import play.Application;
import play.ApplicationLoader.Context;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import software.reinvent.commons.config.ConfigLoader;

public class AppTestBase {

  // #test-injection
  @Inject protected Application app;
  Config config = ConfigLoader.load();

  @BeforeEach
  public void setup() {
    Module testModule =
        new AbstractModule() {
          @Override
          public void configure() {
            Config testConfig = ConfigFactory.parseResources("conf/application.conf");
            bind(Config.class).toInstance(testConfig.withFallback(ConfigLoader.load()));
            configureMore(this.binder());
          }
        };

    GuiceApplicationBuilder builder =
        new GuiceApplicationLoader()
            .builder(new Context(Environment.simple()))
            .loadConfig(config)
            .overrides(testModule);
    Guice.createInjector(builder.applicationModule()).injectMembers(this);

    Helpers.start(app);
  }

  protected void configureMore(Binder abstractModule) {}

  @AfterEach
  public void teardown() {
    Helpers.stop(app);
  }
  // #test-injection

  protected Result getRequest(String uri) {
    RequestBuilder request = Helpers.fakeRequest().method(Helpers.GET).uri(uri);

    return Helpers.route(app, request);
  }

  protected Result postRequest(String uri, JsonNode json) {
    RequestBuilder request = Helpers.fakeRequest().method(Helpers.POST).uri(uri).bodyJson(json);

    return Helpers.route(app, request);
  }

  protected String resultAsString(Result result) {
    return Helpers.contentAsString(result);
  }
}
