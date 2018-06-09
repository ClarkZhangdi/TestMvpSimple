package com.elensliu.mvpsample.common.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import crm.wangjin.main.domain.dagger.inject.AppInject;

/**
 * Created by liuchengen on 2017/5/3.
 */

public class PackageInfoUtils {

    public static String getVersion() {

        Application application = AppInject.getInstance().getApplication();
        try {
            PackageManager manager = application.getPackageManager();
            PackageInfo info = manager.getPackageInfo(application.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "1.0.0";
    }

    public static String getChannel() {

        Application application = AppInject.getInstance().getApplication();

        try {
            ApplicationInfo appInfo = application.getPackageManager()
                    .getApplicationInfo(application.getPackageName(),
                            PackageManager.GET_META_DATA);
            return appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "publish";
    }


}
