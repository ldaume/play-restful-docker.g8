import play.Application;
import play.GlobalSettings;
import utils.PlayUtils;


/**
 * The starting point of the service.
 * <p>
 * Created by Leonard Daume on 18.11.2015.
 */
public class Global extends GlobalSettings {

  @Override public void onStart(final Application app) {
    super.onStart(app);
    PlayUtils.logConfig();
  }
}
