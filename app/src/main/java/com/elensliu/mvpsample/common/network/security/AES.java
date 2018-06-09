package com.elensliu.mvpsample.common.network.security;

import android.text.TextUtils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import crm.wangjin.main.domain.utils.Base64;


/**
 * Created by liuchengen on 2017/2/24.
 */

public class AES {

    private final static String HEX = "0123456789ABCDEF";
    private static final String CBC_PKCS5_PADDING = "AES/CBC/NoPadding";//AES是加密方式 CBC是工作模式 PKCS5Padding是填充模式
    private static final String AES = "AES";//AES 加密
    private static final String SHA1PRNG = "SHA1PRNG";//// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法


    /*
     * 生成随机数，可以当做动态的密钥 加密和解密的密钥必须一致，不然将不能解密
     */
    public static String generateKey() {
        try {
            SecureRandom localSecureRandom = SecureRandom.getInstance(SHA1PRNG);
            byte[] bytes_key = new byte[8];
            localSecureRandom.nextBytes(bytes_key);
            String str_key = toHex(bytes_key);
            return str_key;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
     * 加密
     */
    public static String encrypt(String key, String cleartext) {
        if (TextUtils.isEmpty(cleartext)) {
            return cleartext;
        }
        try {
            byte[] result = encrypt(key, cleartext.getBytes());
            return Base64.encode(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] encrypt(String key, byte[] clear) throws Exception {
        try {
            String iv = "1234567812345678";
            Cipher cipher = Cipher.getInstance(CBC_PKCS5_PADDING);
            int blockSize = cipher.getBlockSize();
            int plaintextLength = clear.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(clear, 0, plaintext, 0, clear.length);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
            byte[] ivb = iv.getBytes();
            IvParameterSpec ivspec = new IvParameterSpec(ivb);
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return encrypted;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
     * 解密
     */
    public static String decrypt(String key, String encrypted) {
        if (TextUtils.isEmpty(encrypted)) {
            return encrypted;
        }
        try {
            byte[] enc = Base64.decode(encrypted);
            byte[] result = decrypt(key, enc);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(String key, byte[] encrypted) throws Exception {
        try {
            String iv = "1234567812345678";
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted);
            return original;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //二进制转字符
    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
