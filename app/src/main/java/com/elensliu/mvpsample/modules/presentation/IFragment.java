package com.elensliu.mvpsample.modules.presentation;

/**
 * Created by elensliu on 2016/11/15.
 */

public interface IFragment {

    void onEnter(Object data);

    void onLeave();

    void onBack();

    void onBackWithData(Object data);
}
