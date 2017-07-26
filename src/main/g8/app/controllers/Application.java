package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

  public CompletionStage<Result> index() {
    return supplyAsync(() -> ok(index.render("Your new application is ready.")), ec.current());
  }

}
