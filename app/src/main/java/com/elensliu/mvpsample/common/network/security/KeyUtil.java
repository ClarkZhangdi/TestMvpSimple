package com.elensliu.mvpsample.common.network.security;


/**
 * Created by elensliu on 16/10/21.
 */

public class KeyUtil {


    /**
     * 获取RSA公钥
     */
    public static String getRsaPublicKey() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDNTP1MCf1xnMYiDt+sg/+rxBb7lW2MhLoLqDeNB2VzZWsV0CsgmRmpHCALLXMBxlhH9f8SkdKWUfa4UI6ZcuTR6eibW3igbeqIrhjwrB2hc/XBCGHwfurAtAOE669OVokSUEBaqC6nLr0DXGFrlisRwiWpbGxuwg1+Ph/O9Dn7yQIDAQAB";
    }


    /**
     * 获取接口请求3DES加密解密key
     *
     * @return
     */
    public static String getDesKey() {

        return "ucsmy123qwerttuiasdfghjk";
    }

    /**
     * 获取手势3DES key
     */
    public static String getDKey() {

        return "12345678";
    }
}
