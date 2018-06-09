package crm.wangjin.main.domain.executor;

import java.util.Collection;
import java.util.List;

/**
 * Created by elensliu on 2016/11/23.
 */

public interface ITaskQueue {


    void addTask(ITask task);

    ITask findByTag(String tag);

    boolean removeTask(ITask task);

    Collection<ITask> getQueue();

    void clear();

}
