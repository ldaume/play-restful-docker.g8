package controllers;

import com.typesafe.config.Config;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Result;
import play.mvc.Results;

public class Application extends GenericController {

  @Inject
  public Application(HttpExecutionContext ec, Config config) {
    super(ec, config);
  }

  public CompletionStage<Result> index() {
    return result(Results::ok);
  }

  public CompletionStage<Result> heartbeat() {
    return result(() -> ok("OK"));
  }
}
