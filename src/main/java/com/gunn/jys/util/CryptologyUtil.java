package com.gunn.jys.util;

import com.gunn.jys.constant.common.EncryptConst;
import org.apache.shiro.crypto.hash.SimpleHash;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 密码工具类
 */
public class CryptologyUtil {

    /** */
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /** */
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** */
    /**
     * 获取公钥的key
     */
    public static final String PUBLIC_KEY = "RSAPublicKey";

    /** */
    /**
     * 获取私钥的key
     */
    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /** */
    /**
     * RSA最大加密明文大小
     */
    public static final int MAX_ENCRYPT_BLOCK = 117;

    /** */
    /**
     * RSA最大解密密文大小
     */
    public static final int MAX_DECRYPT_BLOCK = 128;

    public final static String md5(String encryptString, char[] hexDigits) {
        try {
            byte[] btInput = encryptString.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 方法名: md5UpperCase</br>
     * 详述: 大写的MD5</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2016-3-31</br>
     *
     * @param encryptString
     * @return
     */
    public static String md5UpperCase(String encryptString) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        return md5(encryptString, hexDigits);
    }

    /**
     * 方法名: md5LowerCase</br>
     * 详述: 小写的MD5</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2016-3-31</br>
     *
     * @param encryptString
     * @return
     */
    public static String md5LowerCase(String encryptString) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        return md5(encryptString, hexDigits);
    }

    /** */
    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genRsaKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     *
     * 方法名: md5WithSalt</br>
     * 详述: md5加上盐值</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2017年2月23日</br>
     *
     * @param password
     * @param salt
     * @return
     */
    public static String md5WithSalt(String password, String salt) {
        SimpleHash simpleHash = new SimpleHash(EncryptConst.ALGORITHM_MD5, password, salt,
                EncryptConst.ITERATION_TIMES);
        return simpleHash.toString();
    }

    /**
     *
     * 方法名: getSalt</br>
     * 详述: 获取随机的盐值</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2017年2月23日</br>
     *
     * @return
     */
    public static String getSalt() {
        return RandomUtil.randomString(10);
    }

    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 生成签名数据
     *
     * @param data
     *            待加密的数据
     * @param key
     *            加密使用的key
     * @throws InvalidKeyException
     */
    public static String getSignature(String data, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : rawHmac) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte ib) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }




    public static String shiroMd5(String password, String salt) {
        SimpleHash simpleHash = new SimpleHash(EncryptConst.ALGORITHM_MD5, password, salt, EncryptConst.ITERATION_TIMES);
        return simpleHash.toString();
    }


    public static void main(String[] args) {
        String salt = getSalt();
        System.out.println(salt);
        System.out.println(shiroMd5("123456", salt));
        System.out.println("72830176d4a1c5461d2adc0d44194460".length());
    }














}
