package modules;

import com.google.inject.AbstractModule;

import org.apache.commons.lang3.StringUtils;

import play.Configuration;
import play.Environment;
import utils.PlayUtils;

/**
 * Created by leonard on 09.03.16.
 */
public class ApplicationModule extends AbstractModule {

    private final Environment environment;
    private final Configuration configuration;

    public ApplicationModule(Environment environment, Configuration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(PlayUtils.class).asEagerSingleton();
    }
}
