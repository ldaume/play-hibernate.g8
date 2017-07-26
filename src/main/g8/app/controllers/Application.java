package controllers;

import com.google.inject.Inject;

import play.*;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import play.libs.concurrent.HttpExecutionContext;

import views.html.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class Application extends Controller {

      private final HttpExecutionContext ec;

      @Inject
      public Application(HttpExecutionContext ec) {
          this.ec = ec;
      }

  public CompletionStage<Result> index() {
    return supplyAsync(() -> ok(index.render("Your new application is ready.")), ec.current());
  }

}
