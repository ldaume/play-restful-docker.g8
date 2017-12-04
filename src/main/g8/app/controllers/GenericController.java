package controllers;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.typesafe.config.Config;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

public abstract class GenericController extends Controller {

  private final HttpExecutionContext ec;
  private final Config config;

  @Inject
  protected GenericController(HttpExecutionContext ec, Config config) {
    this.ec = ec;
    this.config = config;
  }

  protected CompletableFuture<Result> result(Supplier<Result> result) {
    response().setHeader("version", config.getString("app.version"));
    return supplyAsync(() -> result.get(), ec.current());
  }
}
