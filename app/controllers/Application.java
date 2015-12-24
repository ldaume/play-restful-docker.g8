package controllers;

import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

public class Application extends Controller {

  public F.Promise<Result> index() {
    return F.Promise.promise(Results::ok);
  }
}
