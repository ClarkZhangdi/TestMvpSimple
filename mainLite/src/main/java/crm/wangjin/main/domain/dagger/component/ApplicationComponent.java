package crm.wangjin.main.domain.dagger.component;

import crm.wangjin.main.domain.dagger.inject.AppInject;
import crm.wangjin.main.domain.dagger.module.ApplicationModule;
import crm.wangjin.main.domain.dagger.scope.ScopeApp;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.presentation.components.BaseApplication;
import dagger.Component;

/**
 * Created by elensliu on 16/10/18.
 */
@ScopeApp
@Component(modules = ApplicationModule.class)
public abstract class ApplicationComponent {


    private static ApplicationComponent component;

    /**
     * 必须先初始化
     *
     * @param application
     */
    public synchronized static void init(BaseApplication application) {

        if (component == null) {

            component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }

    }

    /**
     * 获取实例
     *
     * @return
     */
    public synchronized static ApplicationComponent getInstance() {
        if (component == null) {
            throw new RuntimeException("必须先调用 ApplicationComponent 的init方法");
        }
        return component;
    }


    public abstract void inject(AppInject inject);


    @ScopeLife()
    public abstract BaseApplication getApplication();
}
