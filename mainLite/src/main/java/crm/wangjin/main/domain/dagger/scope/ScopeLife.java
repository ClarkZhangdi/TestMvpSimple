package crm.wangjin.main.domain.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by elensliu on 16/10/18.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeLife {

    String value() default "Application";
}
