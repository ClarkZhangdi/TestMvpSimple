package crm.wangjin.main.domain.executor;

/**
 * Created by elensliu on 16/10/26.
 */

public interface IExecutor {

    String postTask(long delay, IExecutorCallback callback);

    boolean cancelByTag(String tag);

    void cancelAll();

}
