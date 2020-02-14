package cn.harry.common.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * ClassName: OkHttpUtil
 * Description:
 *
 * @author honghh
 * Date 2019/10/21 10:27
 * Copyright (C) www.tech-harry.cn
 */
public class OkHttpUtil {

    /**
     * 发起get请求
     *
     * @param url
     * @param queries
     * @return
     */
    public static String get(String url, Map<String, String> queries) {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }

        Request request = new Request.Builder().url("http://ip.taobao.com/service/getIpInfo.php?ip=1.193.163.159").build();
        try {

            Response response = client.newCall(request).execute();
            result = response.body().string();
            System.out.println(result);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送httppost请求
     *
     * @param url
     * @param data 提交的参数为key=value&key1=value1的形式
     * @return
     */
    public static String httpPost(String url, String data) {
        String result = null;
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/html;charset=utf-8"), data);

        Request request = new Request.Builder().url(url).post(requestBody).build();
        try {
            Response response = httpClient.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
