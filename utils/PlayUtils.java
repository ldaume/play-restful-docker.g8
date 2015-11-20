package utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.Play;
import play.libs.Json;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.repeat;
import static play.Logger.info;

/**
 * Some helping methods and constants for Play!
 * <p>
 * Created by leonard on 16.11.15.
 */
public class PlayUtils {

  public static final void logConfig() {
    printEnvs();
    sleepQuietly();
    try {
      printUsedConfig();
    } catch (Exception e) {
      Logger.error("Could not log current configs.", e);
    }
    sleepQuietly();
  }

  private static void sleepQuietly() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      //
    }
  }

  private static void printEnvs() {
    final StringBuilder configPrinter = startConfigPrinter("Environment variables");
    System.getenv()
          .keySet()
          .stream()
          .sorted()
          .forEach(key -> addKeyValue(configPrinter, key, System.getenv(key)));
    info(withFooter(configPrinter).toString());
  }

  private static void printUsedConfig() {
    String config = System.getProperty("config.resource", "application.conf");
    info("Will use config file {} of {}.",
         config,
         FileUtils.listFiles(Play.application().getFile("/conf"),
                             FileFilterUtils.suffixFileFilter(".conf"),
                             null)
                  .stream()
                  .map(File::getName)
                  .sorted()
                  .collect(Collectors.toSet()));
    final StringBuilder configPrinter = startConfigPrinter("Config file: (" + config + ")");
    Properties loadedConfigFile = new Properties();
    try {
      loadedConfigFile.load(Play.application().classloader().getResourceAsStream(config));
    } catch (IOException e) {
      //
    }
    DocumentContext ctx = JsonPath.parse(Json.toJson(Play.application().configuration().asMap())
                                             .toString());
    loadedConfigFile.keySet().stream().sorted().forEach(key -> {
      try {
        addKeyValue(configPrinter, key, ctx.read("$." + key));
      } catch (Exception e) {
        addKeyValue(configPrinter, key, " = n/a");
      }
    });
    info(withFooter(configPrinter).toString());
  }

  private static final StringBuilder startConfigPrinter(final String title) {
    final StringBuilder text = new StringBuilder("\n\t╭" + repeat("─", 68) + "╮\n");
    text.append(StringUtils.rightPad("\t│    " + title + ": ", 70, " ") + "│\n");
    text.append("\t╞════" + repeat("═", 60) + "════╡\n");
    text.append("\t│" + repeat(" ", 68) + "┊\n");
    return text;
  }

  private static void addKeyValue(final StringBuilder configPrinter,
                                  final Object key,
                                  final Object value) {
    configPrinter.append("\t│    " + key + " = " + value + "\n");
  }

  private static StringBuilder withFooter(final StringBuilder text) {
    text.append("\t│" + repeat(" ", 68) + "┊\n");
    text.append("\t╰" + repeat("─", 68) + "╯\n");
    return text;
  }

  private PlayUtils() {
    throw new UnsupportedOperationException();
  }
}
