package com.gunn.jys.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {

    /**
     * 把对象转换成json
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    /**
     * 转换成对象
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    /**
     * 格式化json
     * @param jsonString
     * @return
     */
    public static String toPrettyFormat(String jsonString) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }

    public static void main(String[] args) {
        String jsonString = "{\"status\":\"true\",\"code\":\"200\",\"msg\":null,\"info\":{\"orderPay\":{\"id\":259,\"orderId\":318,\"status\":20,\"orderAmount\":50.00,\"payAmount\":1.01,\"couponAmount\":0.00,\"pointAmount\":0.00,\"totalPoint\":0,\"created\":\"2017-11-23 18:31:43\",\"updated\":\"2017-11-23 18:33:30\",\"useCoupon\":0,\"usePoint\":0,\"pointTransactionNo\":null,\"returnPointTransactionNo\":null,\"payType\":20,\"payDate\":null,\"payEndDate\":\"2017-11-23 19:01:43\",\"transactionId\":null},\"order\":{\"id\":318,\"userId\":2,\"orderNum\":\"011711231831968\",\"checkInNum\":\"965338\",\"status\":20,\"created\":\"2017-11-23 18:31:43\",\"updated\":\"2017-11-25 11:00:00\",\"remark\":null,\"type\":1,\"spaceId\":1001}}}";
        System.out.println(toPrettyFormat(jsonString));
    }




}
