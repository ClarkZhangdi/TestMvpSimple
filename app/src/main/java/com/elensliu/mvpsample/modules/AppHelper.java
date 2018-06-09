package com.elensliu.mvpsample.modules;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.elensliu.mvpsample.modules.message.components.MessageActivity;


/**
 * Created by wowling on 17/2/21.
 */

public class AppHelper {


    /**
     * 短信
     *
     * @param context
     */
    public static void showMessage(Context context) {
        showActivity(context, MessageActivity.class);
    }


    /**
     * 跳转Acitivty通用方法
     * curActivity 当前Activity
     * targetActivity 目标Activity
     */
    private static void showActivity(Context context, Class targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        context.startActivity(intent);
    }

    public static void showClass(Context context, Class type, Bundle bundle) {
        Intent intent = new Intent(context, type);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


}
