package crm.wangjin.main.domain.executor;

/**
 * Created by elensliu on 16/10/25.
 */

public interface IExecutorCallback {

    void runOnBackGround();

    void runOnMainFinsh();

    void cancel();
}
