package controllers.auth;

import com.google.common.io.BaseEncoding;
import org.apache.commons.lang3.StringUtils;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import static play.Play.application;

/**
 * Created by Leonard Daume on 12.11.2015.
 */

public class BasicAuthAction extends Action.Simple {

  private static final String AUTHORIZATION = "authorization";
  private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
  private static final String REALM = "Basic realm=\"Play RESTful Docker\"";

  public F.Promise<Result> call(Http.Context context) throws Throwable {
    final String authHeader = context.request().getHeader(AUTHORIZATION);
    if ( authHeader == null ) {
      context.response().setHeader(WWW_AUTHENTICATE, REALM);
      return F.Promise.promise(() -> unauthorized());
    }

    final String auth = authHeader.substring(6);
    byte[] decodedAuth = BaseEncoding.base64().decode(auth);
    final String[] credString = new String(decodedAuth, "UTF-8").split(":");

    if ( credString == null || credString.length != 2 ) {
      return F.Promise.promise(() -> unauthorized());
    }

    final String username = credString[0];
    final String password = credString[1];

    boolean isAllowed = StringUtils.equals(application().configuration()
                                                        .getString("basic.auth.username", "user"),
                                           username)
                        && StringUtils.equals(application().configuration()
                                                           .getString("basic.auth.password",
                                                                      "password"), password);
    return isAllowed ? delegate.call(context) : F.Promise.promise(() -> unauthorized());
  }
}
