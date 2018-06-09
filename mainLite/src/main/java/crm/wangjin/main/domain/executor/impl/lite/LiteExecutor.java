package crm.wangjin.main.domain.executor.impl.lite;


import crm.wangjin.main.domain.executor.IExecutor;
import crm.wangjin.main.domain.executor.IExecutorCallback;
import crm.wangjin.main.domain.executor.ITask;
import crm.wangjin.main.domain.executor.ITaskQueue;
import crm.wangjin.main.domain.executor.impl.TaskQueue;

/**
 * Created by elensliu on 2016/11/1.
 */

public class LiteExecutor implements IExecutor {

    private ITaskQueue queue = new TaskQueue();

    @Override
    public String postTask(long delay, IExecutorCallback callback) {
        ITask task = new LiteTask();
        task.setTag("IExecutor:" + System.currentTimeMillis());
        task.post(callback,
                delay);
        queue.addTask(task);
        return task.getTag();
    }

    @Override
    public boolean cancelByTag(String tag) {

        boolean flag = false;
        ITask task = queue.findByTag(tag);
        if (task != null) {
            flag = task.cancel();
            queue.removeTask(task);

        }
        return flag;
    }

    @Override
    public void cancelAll() {

        for (ITask task : queue.getQueue()) {

            task.cancel();
        }

        queue.clear();

    }
}
