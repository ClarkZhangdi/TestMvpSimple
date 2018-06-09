package crm.wangjin.main.domain.repository.network.http;


import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import crm.wangjin.main.domain.executor.Executor;
import crm.wangjin.main.domain.executor.IExecutorCallback;
import crm.wangjin.main.domain.repository.network.IBaseRequest;


/**
 * Created by elensliu on 16/10/24.
 */

public class RequestManager {

    private final static BlockingQueue<RequestObject> mUnFinishQueue = new LinkedBlockingDeque<>();

    private final static BlockingQueue<RequestObject> mRequestQueue = new LinkedBlockingDeque<>();

    private final static Map<String, String> executorMap = new ConcurrentHashMap<>();

    private static class RequestObject {

        String tag;
        IBaseRequest request;
        IExecutorCallback callback;

        public RequestObject(String tag,
                             IBaseRequest request,
                             IExecutorCallback callback) {
            this.tag = tag;
            this.request = request;
            this.callback = callback;
        }
    }


    private static boolean isRunning = false;
    private static Thread mRequestTHread = new Thread() {

        @Override
        public void run() {
            super.run();
            while (isRunning) {

                if (mRequestQueue.isEmpty()) {
                    try {
                        sleep(100);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                RequestObject requestObject = mRequestQueue.peek();
                if (requestObject != null) {
                    boolean enqueue = true;
                    for (RequestObject queueObj : mUnFinishQueue) {
                        if (requestObject.tag.equals(queueObj.tag)) {
                            enqueue = false;
                            mRequestQueue.remove(requestObject);
                            break;
                        }
                    }

                    if (enqueue) {
                        IExecutorCallback callback = requestObject.callback;
                        synchronized (executorMap) {
                            String exeTag = Executor.postTask(callback);
                            executorMap.put(requestObject.tag, exeTag);
                            mUnFinishQueue.add(requestObject);
                            mRequestQueue.remove(requestObject);
                        }
                    }
                }
            }
        }
    };

    public static void start() {

        isRunning = true;
        mRequestTHread.start();
    }

    public static void stop() {

        isRunning = false;
        clearAllRequests();
    }

    public static String addRequest(IBaseRequest request,
                                    IExecutorCallback callback) {

        request.setRequestTime(System.currentTimeMillis());
        String reqTag = request.getTag();
        RequestObject object = new RequestObject(reqTag, request, callback);
        mRequestQueue.add(object);
        return reqTag;
    }

    public static boolean removeRequest(Object tag) {

        synchronized (mRequestQueue) {
            for (RequestObject requestObject : mRequestQueue) {
                if (requestObject.tag.equals(tag)) {
                    return mRequestQueue.remove(requestObject);
                }
            }
        }
        return false;
    }

    public static boolean removeUnFinish(Object tag) {

        synchronized (mUnFinishQueue) {
            for (RequestObject requestObject : mUnFinishQueue) {
                if (requestObject.tag.equals(tag)) {

                    boolean flag = mUnFinishQueue.remove(requestObject);
                    return flag;
                }
            }
        }
        return false;
    }


    public static boolean cancelByTag(Object tag) {

        boolean removeUnFinish = removeUnFinish(tag);
        boolean removeRequest = removeRequest(tag);
        boolean removeExecutor = false;
        synchronized (executorMap) {

            if (executorMap.containsKey(tag)) {

                String eTag = executorMap.get(tag);
                removeExecutor = Executor.cancelByTag(eTag);
                executorMap.remove(tag);
            }
        }

        return removeUnFinish && removeRequest && removeExecutor;
    }

    public static void cancelAll() {

        synchronized (mUnFinishQueue) {
            mUnFinishQueue.clear();
        }
        synchronized (mRequestQueue) {
            mRequestQueue.clear();
        }

        synchronized (executorMap) {

            for (String tag : executorMap.values()) {
                Executor.cancelByTag(tag);
            }
            executorMap.clear();
        }

    }


    public static void clearAllRequests() {

        synchronized (mUnFinishQueue) {

            mUnFinishQueue.clear();
        }
        synchronized (mRequestQueue) {
            mRequestQueue.clear();
        }
        synchronized (executorMap) {

            for (String eTag : executorMap.values()) {
                Executor.cancelByTag(eTag);
            }
            executorMap.clear();
        }
    }


}
