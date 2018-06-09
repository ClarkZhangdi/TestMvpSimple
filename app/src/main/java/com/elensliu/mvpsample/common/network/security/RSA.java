package com.elensliu.mvpsample.common.network.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import crm.wangjin.main.domain.utils.Base64;


/**
 * Created by elensliu on 16/10/21.
 */

public class RSA {

    private static final String RSA_ALGORITHM = "RSA/None/PKCS1Padding";


    /**
     * APP端 RSA 加密生成的KEY
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NullPointerException
     * @throws IOException
     */
    public static Key getRSAPublicKey ()
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NullPointerException,
            IOException {

        return loadPublicKey (KeyUtil.getRsaPublicKey ());
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static Key loadPublicKey (String publicKeyStr)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NullPointerException {
        try {
            byte[] buffer = Base64.decode (publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance ("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec (buffer);
            return keyFactory.generatePublic (keySpec);
        } catch (NoSuchAlgorithmException | NullPointerException | InvalidKeySpecException e) {

            throw e;
        }
    }




    /**
     * RSA加密
     *
     * @param key 秘钥
     * @param src 源数据
     * @return byte[] 加密后的数据
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] encrypt (Key key, byte[] src)
            throws InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchPaddingException,
            NoSuchAlgorithmException {

        if (src == null || src.length == 0) {
            throw new IllegalArgumentException ("报文为空");
        }
        try {
            Cipher ciper = Cipher.getInstance (RSA_ALGORITHM);
            ciper.init (Cipher.ENCRYPT_MODE,
                        key);
            return ciper.doFinal (src);
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException |
                BadPaddingException e) {

            throw e;
        }
    }

    /**
     * RSA解密
     *
     * @param privateKey 私钥
     * @param src        加密后的源数据
     * @return byte[] 解密后的数据
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public static byte[] decrypt (Key privateKey, byte[] src)
            throws InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException,
            NoSuchAlgorithmException,
            NoSuchPaddingException {

        try {
            Cipher cipher = Cipher.getInstance (RSA_ALGORITHM);
            cipher.init (Cipher.DECRYPT_MODE,
                         privateKey);
            return cipher.doFinal (src);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException |
                BadPaddingException e) {

            throw e;
        }
    }
}
