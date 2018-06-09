package crm.wangjin.main.domain.utils;

import android.text.TextUtils;
import android.widget.Toast;

import crm.wangjin.main.domain.dagger.inject.AppInject;
import crm.wangjin.main.presentation.components.BaseApplication;

/**
 * Created by liuchengen on 2016/12/14.
 */

public class ToastUtil {


    public static void show(String message) {

        show(message, 1000);
    }


    public static void show(String message, int duration) {
        BaseApplication application = AppInject.getInstance().getApplication();
        if (TextUtils.isEmpty(message))
            return;
        final Toast toast = Toast.makeText(
                application,
                message,
                Toast.LENGTH_LONG);

        toast.getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);

        toast.show();
    }
}
