package com.elensliu.mvpsample.common.network.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by elensliu on 16/10/22.
 */

public class ReqHead {


    @SerializedName("appVersion")
    String appVersion;
    @SerializedName("clientModel")
    String clientModel;
    @SerializedName("OSVersion")
    String OSVersion;
    @SerializedName("uuid")
    String uuid;
    @SerializedName("packageName")
    String packageName;
    @SerializedName("channel")
    String channel;

    @SerializedName("device_type")
    String deviceType;


    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getClientModel() {
        return clientModel;
    }

    public void setClientModel(String clientModel) {
        this.clientModel = clientModel;
    }

    public String getOSVersion() {
        return OSVersion;
    }

    public void setOSVersion(String OSVersion) {
        this.OSVersion = OSVersion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}
