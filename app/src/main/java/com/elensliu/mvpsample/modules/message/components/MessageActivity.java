package com.elensliu.mvpsample.modules.message.components;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.elensliu.mvpsample.R;
import com.elensliu.mvpsample.modules.message.model.MessageResp;
import com.elensliu.mvpsample.modules.message.presenters.IMessagePresenter;
import com.elensliu.mvpsample.modules.message.presenters.impl.MessagePresenter;
import com.elensliu.mvpsample.modules.presentation.AbstractBaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import crm.wangjin.main.domain.utils.ToastUtil;

public class MessageActivity
        extends AbstractBaseActivity
        implements IMessagePresenter.View {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    protected void initInjector() {
        getActivityComponent().inject(this);
    }

    @Inject
    MessagePresenter messagePresenter;

    @BindView(R.id.phoneEdt)
    EditText phoneEdt;

    @BindView(R.id.typeEdt)
    EditText typeEdt;

    @BindView(R.id.consoleTx)
    TextView consoleTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSendMessageSuccess(MessageResp data) {
        consoleTx.setText(data.toString());
    }


    @Override
    public void onSendMessageError(String errorMsg) {
        consoleTx.setText(errorMsg);
    }

    @Override
    public void onVerifyTip(String tipMsg) {

    }
}
