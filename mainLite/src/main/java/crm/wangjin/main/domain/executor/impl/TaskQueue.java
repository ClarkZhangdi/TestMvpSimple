package crm.wangjin.main.domain.executor.impl;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import crm.wangjin.main.domain.executor.ITask;
import crm.wangjin.main.domain.executor.ITaskQueue;


/**
 * Created by elensliu on 2016/11/23.
 */

public class TaskQueue implements ITaskQueue {

    private final BlockingQueue<ITask> mTaskQueue = new PriorityBlockingQueue<> ();


    @Override
    public void addTask (ITask task) {
        mTaskQueue.add (task);
    }

    @Override
    public ITask findByTag (String tag) {

        for (ITask task : mTaskQueue) {

            if (task.getTag ()
                    .equals (tag)) {

                return task;
            }
        }
        return null;
    }

    @Override
    public boolean removeTask (ITask task) {
        return mTaskQueue.remove (task);
    }

    @Override
    public Collection<ITask> getQueue () {
        return mTaskQueue;
    }

    @Override
    public void clear () {

        mTaskQueue.clear ();
    }
}
