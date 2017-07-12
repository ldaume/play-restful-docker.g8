package controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class Application extends Controller {

    public CompletionStage<Result> index() {
        return CompletableFuture.supplyAsync(Results::ok);
    }
}
