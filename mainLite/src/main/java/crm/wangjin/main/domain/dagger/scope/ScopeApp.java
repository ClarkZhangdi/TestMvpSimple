package crm.wangjin.main.domain.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by elensliu on 16/10/18.
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeApp {
}
