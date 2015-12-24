package controllers.auth;

import play.mvc.With;

import java.lang.annotation.*;

/**
 * Created by Leonard Daume on 12.11.2015.
 */

@With(BasicAuthAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Inherited
@Documented
public @interface BasicAuth { }
