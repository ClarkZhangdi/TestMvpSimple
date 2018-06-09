package crm.wangjin.main.event;

/**
 * Created by liuchengen on 2017/1/17.
 */

public class NetworkChangeEvent {

    private boolean isConnect = true;

    public NetworkChangeEvent(boolean isConnect) {
        this.isConnect = isConnect;
    }

    public boolean isConnect() {
        return isConnect;
    }
}
