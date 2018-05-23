package lifecycle;

import static java.lang.Character.isLetter;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;
import static play.Logger.info;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import java.io.IOException;
import java.util.Properties;
import play.Application;

/**
 * Print Play config.
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
    printConfig();
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
    var configPrinter = startConfigPrinter("Environment variables");
    getenv()
        .keySet()
        .stream()
        .sorted()
        .forEach(key -> addKeyValue(configPrinter, key, getenv(key)));
    info(withFooter(configPrinter).toString());
  }

  private void printConfig() {
    var configFile = getProperty("config.resource", "application.conf");
    var configPrinter = startConfigPrinter(join("Config file: (", configFile, ")"));

    var loadedConfigFile = new Properties();
    try {
      loadedConfigFile.load(application.classloader().getResourceAsStream(configFile));
    } catch (IOException e) {
      //
    }

    loadedConfigFile
        .keySet()
        .stream()
        .filter(key -> key instanceof String)
        .map(key -> trimToEmpty((String) key))
        .filter(key -> isNotBlank(key) && isLetter(key.charAt(0)))
        .sorted()
        .forEach(
            key -> {
              try {
                addKeyValue(configPrinter, key, this.config.getValue(key).unwrapped());
              } catch (Exception e) {
                addKeyValue(configPrinter, key, " = n/a");
              }
            });
    info(withFooter(configPrinter).toString());
  }

  private StringBuilder startConfigPrinter(final String title) {

    var text = new StringBuilder(join("\n\t╭", repeat("─", 68), "╮\n"));
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
