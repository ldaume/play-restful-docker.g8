package controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import play.mvc.Http.RequestBuilder;
import play.test.Helpers;
import utils.AppTestBase;

public class ApplicationTest extends AppTestBase {

  @Test
  public void heartbeat() {
    RequestBuilder request = Helpers.fakeRequest().method("GET").uri("/heartbeat");

    String actual = Helpers.contentAsString(Helpers.route(app, request));
    assertThat(actual).isEqualTo("OK");
  }
}
