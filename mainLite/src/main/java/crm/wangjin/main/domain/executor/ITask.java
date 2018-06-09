package crm.wangjin.main.domain.executor;

/**
 * Created by elensliu on 16/10/26.
 */

public interface ITask extends Comparable<ITask> {

    void setTag(String tag);

    String getTag();

    void post(IExecutorCallback callback);

    void post(IExecutorCallback callback, long delay);

    boolean cancel();
}
