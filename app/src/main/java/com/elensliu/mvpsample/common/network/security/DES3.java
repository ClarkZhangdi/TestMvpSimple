package com.elensliu.mvpsample.common.network.security;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;


/**
 * Created by elensliu on 16/10/21.
 */

public class DES3 {

    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 3DES加密
     *
     * @param src      需要加密的内容
     * @param password 加密密码
     * @return byte[] 加密后的数据
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] encrypt(byte[] src, String password)
            throws UnsupportedEncodingException,
            InvalidKeyException,
            InvalidKeySpecException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            IllegalBlockSizeException,
            BadPaddingException {

        try {
            DESedeKeySpec dks = new DESedeKeySpec(password.getBytes(DEFAULT_CHARSET));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,
                    securekey);
            return cipher.doFinal(src);

        } catch (UnsupportedEncodingException
                | InvalidKeySpecException |
                InvalidKeyException |
                NoSuchAlgorithmException |
                NoSuchPaddingException |
                BadPaddingException |
                IllegalBlockSizeException e) {

            throw e;
        }
    }


    /**
     * 3DES解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeySpecException
     */
    public static byte[] decrypt(byte[] content, String password)
            throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException,
            UnsupportedEncodingException,
            InvalidKeySpecException {

        try {
            DESedeKeySpec dks = new DESedeKeySpec(password.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE,
                    securekey);
            return cipher.doFinal(content);

        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                BadPaddingException |
                IllegalBlockSizeException |
                InvalidKeySpecException |
                UnsupportedEncodingException e) {

            throw e;
        }
    }


    /**
     * RSA加密时生成 3DES密钥
     *
     * @return
     */
    public static String randomKey() {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        int range = buffer.length();
        for (int i = 0; i < 24; i++) {   //随机生成24位
            sb.append(buffer.charAt(random.nextInt(range)));
        }
        //密钥
        return sb.toString();
    }


    private static String lastKey = "";

    public static void setLastKey(String key) {

        lastKey = key;
    }

    public static String getLastKey() {

        if (TextUtils.isEmpty(lastKey)) {

            lastKey = randomKey();
        }
        return lastKey;
    }
}
