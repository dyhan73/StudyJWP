package spms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 1001235 on 15. 5. 29..
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default "";
}
