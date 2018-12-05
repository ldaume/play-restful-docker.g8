package controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import utils.AppTestBase;

public class ApplicationTest extends AppTestBase {

  @Test
  public void heartbeat() {
    assertThat(resultAsString(getRequest("/heartbeat"))).isEqualTo("OK");
  }
}
