package crm.wangjin.main.presentation.components;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by elensliu on 2017/7/21.
 */

public abstract class BaseApplication extends Application {

    private int appCount = 0;
    private ActivityLifecycleCallbacks callbacks;
    private Map<String, WeakReference<View>> decorViews = new LinkedHashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();


        super.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {


                String key = activity.getClass().getName();
                if (!decorViews.containsKey(key)) {
                    View view = activity.getWindow().getDecorView();
                    decorViews.put(key, new WeakReference<>(view));
                }
                if (callbacks != null) {
                    callbacks.onActivityCreated(activity, savedInstanceState);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

                appCount++;
                if (callbacks != null) {
                    callbacks.onActivityStarted(activity);
                }

            }

            @Override
            public void onActivityResumed(Activity activity) {

                if (callbacks != null) {
                    callbacks.onActivityResumed(activity);
                }

            }

            @Override
            public void onActivityPaused(Activity activity) {

                if (callbacks != null) {
                    callbacks.onActivityPaused(activity);
                }

            }

            @Override
            public void onActivityStopped(Activity activity) {

                appCount--;
                if (callbacks != null) {
                    callbacks.onActivityStopped(activity);
                }

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity,
                                                    Bundle outState) {

                if (callbacks != null) {
                    callbacks.onActivitySaveInstanceState(activity, outState);
                }

            }

            @Override
            public void onActivityDestroyed(Activity activity) {


                String key = activity.getClass().getName();
                decorViews.remove(key);
                if (callbacks != null) {
                    callbacks.onActivityDestroyed(activity);
                }
            }
        });
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {

        this.callbacks = callback;
    }

    protected int getAppCount() {
        return appCount;
    }

    public View getTopView() {
        View view = null;

        WeakReference<View> weakReference = decorViews.entrySet().iterator().next().getValue();
        if (weakReference != null) {
            view = weakReference.get();
        }
        return view;
    }
}
