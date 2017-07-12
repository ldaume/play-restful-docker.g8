package utils;

import com.google.inject.Inject;

/**
 * Created by lenny on 11.11.16.
 */
public class StartInterceptor {
    @Inject
    public StartInterceptor(PlayUtils playUtils) {
        playUtils.logConfig();
    }
}
