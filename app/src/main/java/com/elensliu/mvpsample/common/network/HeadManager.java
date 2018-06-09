package com.elensliu.mvpsample.common.network;

import android.content.Context;
import android.text.TextUtils;


import com.elensliu.mvpsample.common.network.model.request.ReqHead;
import com.elensliu.mvpsample.common.utils.PackageInfoUtils;

import java.util.UUID;

import crm.wangjin.main.domain.dagger.component.ApplicationComponent;


/**
 * Created by elensliu on 16/10/20.
 */

public class HeadManager {


    public static void defaultParams(ReqHead reqHead) {

        reqHead.setAppVersion(PackageInfoUtils.getVersion());
        reqHead.setClientModel(android.os.Build.MODEL);
        reqHead.setOSVersion("A" + android.os.Build.VERSION.RELEASE);
        reqHead.setUuid(UUID.randomUUID().toString());
        reqHead.setPackageName(getPackageName());
        reqHead.setChannel(PackageInfoUtils.getChannel());
        reqHead.setDeviceType("Android");
    }
    

    private static String packageName = "";

    private static String getPackageName() {

        if (TextUtils.isEmpty(HeadManager.packageName)) {

            Context context = ApplicationComponent.getInstance().getApplication();
            packageName = context.getPackageName();
        }
        return packageName;
    }
}
