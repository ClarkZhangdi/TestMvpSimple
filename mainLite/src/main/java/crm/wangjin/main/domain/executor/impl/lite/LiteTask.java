package crm.wangjin.main.domain.executor.impl.lite;

import android.os.Handler;
import android.os.Looper;

import com.litesuits.android.async.SimpleTask;
import com.litesuits.android.async.TaskExecutor;

import java.util.concurrent.TimeUnit;

import crm.wangjin.main.domain.executor.IExecutorCallback;
import crm.wangjin.main.domain.executor.ITask;
import crm.wangjin.main.domain.utils.LogUtil;


/**
 * Created by elensliu on 2016/11/1.
 */

public class LiteTask implements ITask {

    private SimpleTask<Void> task;
    private String tag;
    private IExecutorCallback callback;
    private boolean isFinish = false;

    @Override
    public void setTag(String tag) {

        this.tag = tag;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void post(IExecutorCallback callback) {

        post(callback,
                0);
    }

    @Override
    public void post(IExecutorCallback cb,
                     long delay) {

        if (task != null) {
            task.cancel(true);
        }

        this.callback = cb;
        task = new SimpleTask<Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground() {
                callback.runOnBackGround();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);
                callback.runOnMainFinsh();
                isFinish = true;
            }
        };


        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
            public void run() {

                if (!task.isCancelled()) {
                    task.execute(new Object[0]);
                }
            }
        }, delay);

    }


    @Override
    public boolean cancel() {

        boolean flag = false;
        if (task != null
                && !task.isCancelled()
                && !isFinish) {
            flag = task.cancel(true);
            callback.cancel();
        }
        return flag;
    }

    @Override
    public int compareTo(ITask another) {
        return 0;
    }
}
