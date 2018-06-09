package crm.wangjin.main.domain.ioc.inject;

import java.lang.annotation.RetentionPolicy;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(java.lang.annotation.ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {


}