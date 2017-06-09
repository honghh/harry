package com.qd.modules.ws.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmsBaseUtils {

	public static String post(String path, String params) throws Exception {
		HttpURLConnection httpConn = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL url = new URL(path);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestMethod("POST");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);

			// 发送post请求参数
			out = new PrintWriter(httpConn.getOutputStream());
			out.println(params);
			out.flush();

			// 读取响应
			if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				StringBuffer content = new StringBuffer();
				String tempStr = "";
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				while ((tempStr = in.readLine()) != null) {
					content.append(tempStr);
				}
				return content.toString();
			} else {
				throw new Exception("请求出现了问题!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			in.close();
			out.close();
			httpConn.disconnect();
		}
		return null;
	}
	
	public static String sendSMS(String id,String pwd,String to,String content,String url){
		try {
			String param="id="+id+"&pwd="+pwd+"&to="+to+"&content="+URLEncoder.encode(content, "gb2312")+ "&time=";
			String resMessage = SmsBaseUtils.post(url,param);
			return resMessage;
		} catch (Exception e) {
			return e.getMessage().substring(0,500);
		}
	}


}
