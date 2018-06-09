package crm.wangjin.main.domain.dagger.inject;

import javax.inject.Inject;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.presentation.components.BaseApplication;


/**
 * Created by elensliu on 16/10/21.
 */

public class AppInject {

    @Inject
    @ScopeLife()
    BaseApplication application;

    private AppInject () {

        ApplicationComponent.getInstance ()
                            .inject (this);
    }

    private static AppInject appInject;

    public synchronized static AppInject getInstance () {

        if (appInject == null) {
            appInject = new AppInject ();
        }

        return appInject;
    }

    public BaseApplication getApplication () {

        return application;
    }


}
