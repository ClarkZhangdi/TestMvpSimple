package crm.wangjin.main.domain.executor;


import crm.wangjin.main.domain.executor.impl.lite.LiteExecutor;

/**
 * Created by elensliu on 16/10/25.
 * 异步处理框架
 */


public class Executor {

    public static final int MODE_LITE_ASYNC = 2;

    private static IExecutor executor;

    public static void initMode(int mode) {

        switch (mode) {
            case MODE_LITE_ASYNC:
                executor = new LiteExecutor();
                break;
        }

    }

    public static String postTask(IExecutorCallback callback) {

        return postTask(0,
                callback);
    }

    public static String postTask(long delay,
                                  IExecutorCallback callback) {

        if (executor == null) {

            throw new RuntimeException("executor 必须先初始化");
        }

        return executor.postTask(delay,
                callback);
    }

    public static boolean cancelByTag(String tag) {

        if (tag != null) {
            return executor.cancelByTag(tag);
        }
        return false;
    }

    public static void cancelAll() {

        executor.cancelAll();
    }


}
