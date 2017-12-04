package utils;

import static com.jayway.jsonpath.JsonPath.parse;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static play.Logger.info;
import static play.libs.Json.toJson;

import com.google.inject.Inject;
import com.jayway.jsonpath.DocumentContext;
import com.typesafe.config.Config;
import java.io.IOException;
import java.util.Properties;
import play.Application;

/**
 * Some helping methods for play.
 *
 * <p>Created by Leonard Daume on 18.11.2015.
 */
public final class PlayUtils {

  private final Application application;
  private final Config config;

  @Inject
  public PlayUtils(Application application, Config config) {
    this.application = application;
    this.config = config;
    logConfig();
  }

  private void logConfig() {
    printEnvs();
    sleepQuietly();
    printUsedConfig();
    sleepQuietly();
  }

  private void sleepQuietly() {
    try {
      sleep(1000);
    } catch (InterruptedException e) {
      //
    }
  }

  private void printEnvs() {
    final StringBuilder configPrinter = startConfigPrinter("Environment variables");
    getenv()
        .keySet()
        .stream()
        .sorted()
        .forEach(key -> addKeyValue(configPrinter, key, getenv(key)));
    info(withFooter(configPrinter).toString());
  }

  private void printUsedConfig() {
    String config = getProperty("config.resource", "application.conf");
    final StringBuilder configPrinter = startConfigPrinter(join("Config file: (", config, ")"));
    Properties loadedConfigFile = new Properties();
    try {
      loadedConfigFile.load(application.classloader().getResourceAsStream(config));
    } catch (IOException e) {
      //
    }
    DocumentContext ctx = parse(toJson(config));
    loadedConfigFile
        .keySet()
        .stream()
        .sorted()
        .forEach(
            key -> {
              try {
                addKeyValue(configPrinter, key, ctx.read(join("$$.", key)));
              } catch (Exception e) {
                addKeyValue(configPrinter, key, " = n/a");
              }
            });
    info(withFooter(configPrinter).toString());
  }

  private StringBuilder startConfigPrinter(final String title) {

    final StringBuilder text = new StringBuilder(join("\n\t╭", repeat("─", 68), "╮\n"));
    text.append(join(rightPad(join("\t│    ", title, ": "), 70, " "), "│\n"));
    text.append(join("\t╞════", repeat("═", 60), "════╡\n"));
    text.append(join("\t│", repeat(" ", 68), "┊\n"));
    return text;
  }

  private void addKeyValue(
      final StringBuilder configPrinter, final Object key, final Object value) {
    configPrinter.append(join("\t│    ", key, " = ", value, "\n"));
  }

  private StringBuilder withFooter(final StringBuilder text) {
    text.append(join("\t│", repeat(" ", 68), "┊\n"));
    text.append(join("\t╰", repeat("─", 68), "╯\n"));
    return text;
  }
}
