package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.libs.F;
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
}
