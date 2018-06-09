package crm.wangjin.main.domain.dagger.module;

import crm.wangjin.main.domain.dagger.scope.ScopeApp;
import crm.wangjin.main.domain.dagger.scope.ScopeLife;
import crm.wangjin.main.presentation.components.BaseApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by elensliu on 16/10/18.
 */
@Module
public class ApplicationModule {

    private BaseApplication myApplication;

    public ApplicationModule(BaseApplication application) {
        myApplication = application;
    }


    @Provides
    @ScopeApp
    @ScopeLife()
    public BaseApplication provideApplication() {
        return myApplication;
    }
}
