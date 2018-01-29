package com.gunn.jys.util;

import com.gunn.jys.constant.common.DatePattern;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 随机工具类
 */
public class RandomUtil {

    // 字符串 大写O除外避免与0相似
    public static final char[] chars = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz123456789".toCharArray();

    public static final String A2Z = "ABCDEFGHIJKLMNPQRSTUVWXYZ";

    public static final String ZERO2NINE = "0123456789";

    /**
     * 方法名: randomString</br>
     * 详述: 生成一组随机字符串 </br>
     * 开发人员：yuanbao</br>
     * 创建时间：2016年8月30日</br>
     * @param length
     * @return
     */
    public static String randomString(int length) {
        return RandomStringUtils.random(length, chars);
    }

    /**
     * 按位移把数字转字符串
     *
     * @param number
     * @return
     */
    public static String intToStr(int number) {
        char[] arry = String.valueOf(number).toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arry.length; i++) {
            char c = (char) arry[i];
            sb.append((char) (c + 17));
        }
        return sb.toString();
    }

    public static String getA2ZCode(int length) {
        return RandomStringUtils.random(length, A2Z.toCharArray());
    }

    public static String getZero2NineCode(int length) {
        return RandomStringUtils.random(length, ZERO2NINE.toCharArray());
    }

    /**
     * 随机生成邀请码 前面2位是字母 后面四位是数字
     *
     * @return
     */
    public static String getInvitationCode() {
        return getA2ZCode(2) + getZero2NineCode(4);
    }


    /**
     * 方法名: createTransaction</br>
     * 详述: 生成交易流水号</br>
     * 开发人员：yuanbao</br>
     * 创建时间：2017年2月23日</br>
     * @return
     */
    public static String createTransaction(String key) {
        StringBuilder sb = new StringBuilder(DateUtils.format(new Date(), DatePattern.LONGEST_DASH));
        sb.append(getA2ZCode(8));
        sb.append(key.length() > 4 ? key.substring(key.length() - 4) : getZero2NineCode(4));
        return sb.toString();
    }


    /**
     *
     * 方法名: getUniqueCodeByDate</br>
     * 详述: 获取唯一的交易流水号</br>
     * 开发人员：ruibiaozhong</br>
     * 创建时间：2017年3月14日</br>
     * @return
     */
    public static String getUniqueCodeByDate() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String uniqueCode = DateUtils.format(new Date(), DatePattern.LONGEST_DASH) + getZero2NineCode(2);
        return uniqueCode;
    }


    /**
     * 根据空间id生成预支付号
     * @param spaceId
     * @return
     */
    public static String getPrepayNoBySpaceId(Integer spaceId) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String uniqueCode = DateUtils.format(new Date(), DatePattern.LONGEST_DASH) + spaceId + getZero2NineCode(2);
        return uniqueCode;
    }

    /**
     * 生成UUID随机字符串
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成充值记录号pre（充值方式）+年月日时分秒
     * @param prefix
     * @return
     */

    /**
     * 生成length长的随机数字
     * @param length
     * @return
     */
    public static String randomNumber(int length){
        String result="";
        for(int i=0;i<length;i++){
            result+= RandomStringUtils.randomNumeric(1,1);

        }
        return result;
    }
    public  static String generateRechargeNo() {
        String prefix = DateUtils.format(new Date(), DatePattern.RECHARGE_NO);
        String suffix = randomNumber(6);
        return prefix+suffix;

    }
}
