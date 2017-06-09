package com.qd.modules.ws.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.qd.modules.ws.common.WsConfig;
import com.qd.modules.ws.entity.Oauth2Token;
import com.qd.common.utils.StringUtils;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxbase.wxtoken.JwTokenAPI;

import com.qd.common.utils.CacheUtils;
import com.qd.modules.sys.utils.UserUtils;

import net.sf.json.JSONObject;

public class WsUtils {
	/**
	 * 请求参数：
	 *
	 *	参数	  	        必填	    说明
	 *	appid		是	小程序唯一标识
	 *	secret		是	小程序的 app secret
	 *	js_code		是	登录时获取的 code
	 *	grant_type	是	填写为 authorization_code
	 *
	 *返回参数：
	 *	
	 *	参数		说明
	 *	openid	用户唯一标识
	 *	session_key	会话密钥
	 */
	public final static String oauth_token_url = 
			"https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public final static String code_token_url = 
			"https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=URL&response_type=code&scope=snsapi_base&state=jeetc#wechat_redirect";

	public static String getOpenId(HttpServletRequest request) {
		String openId = "";
		try {
			String url = request.getRequestURL().toString();
			String requestUrl = code_token_url.replace("APPID", WsConfig.accountappid);
			String code = request.getParameter("code");
			requestUrl = requestUrl.replace("URL", url);
			openId = (String) UserUtils.getSession().getAttribute("openid");
			if (StringUtils.isEmpty(openId) && StringUtils.isEmpty(code)) {
				HttpClientUtil.httpRequest(requestUrl, "GET", null);
			}
			if (StringUtils.isEmpty(openId) && StringUtils.isNotEmpty(code)) {
				Oauth2Token ot=getOauth2Token(WsConfig.accountappid, WsConfig.accountappsecret, code);
				if(ot!=null){
					openId = ot.getOpenId();
					UserUtils.getSession().setAttribute("openId", openId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openId;
	}

	public static Oauth2Token getOauth2Token(String appId, String appScecret, String code) throws Exception {
		Oauth2Token oauth2Token = null;
		JSONObject jsonObject = null;
		String requestUrl = oauth_token_url.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appScecret);
		requestUrl = requestUrl.replace("CODE", code);
		try {
			jsonObject = HttpClientUtil.httpRequest(requestUrl, "POST", null);
			oauth2Token = new Oauth2Token();
			if(jsonObject.containsKey("openid")){
				oauth2Token.setOpenId(jsonObject.getString("openid"));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// TODO 记录接口消息
		}
		return oauth2Token;

	}
	
	public static String getAccessToken(){
		try {
			String token=(String) CacheUtils.get("access_token");
			Date nowDate=new Date();
			if(StringUtils.isEmpty(token)){
				token=JwTokenAPI.getAccessToken(WsConfig.accountappid, WsConfig.accountappsecret);
				CacheUtils.put("access_token", token);
				CacheUtils.put("access_time", nowDate.getTime());
			}else{
				long lastTime=(Long) CacheUtils.get("access_time");
				if((nowDate.getTime()-lastTime)/1000>7000){
					token=JwTokenAPI.getAccessToken(WsConfig.accountappid, WsConfig.accountappsecret);
					CacheUtils.put("access_token", token);
					CacheUtils.put("access_time", nowDate.getTime());
				}
			}
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		WsUtils.getAccessToken();
	}



}
