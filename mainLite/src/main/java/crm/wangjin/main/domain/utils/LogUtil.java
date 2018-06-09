package crm.wangjin.main.domain.utils;

import android.util.Log;


/**
 * Created by fengyh on 16/5/9.
 */
public class LogUtil {

    private static final String TAG = "网贷记账通";


    public static boolean isShow = true;

    public static void d(String s) {
        if (isShow) {
            Log.d(TAG, s);
        }
    }

    public static void e(String s) {
        if (isShow) {
            Log.e(TAG, s);
        }
    }

    public static void w(String s) {
        if (isShow) {
            Log.w(TAG, s);
        }
    }

    public static void i(String s) {
        if (isShow) {
            Log.i(TAG, s);
        }
    }

}
