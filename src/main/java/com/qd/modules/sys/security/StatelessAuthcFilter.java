package com.qd.modules.sys.security;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.qd.common.mapper.JsonMapper;
import com.qd.common.utils.StringUtils;
import com.qd.modules.sys.security.UsernamePasswordToken;
/**
 * 无状态Web应用集成
 * @author Harry
 *
 */
public class StatelessAuthcFilter extends AccessControlFilter {
	
	public static final String DEFAULT_MESSAGE_PARAM = "message";

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest users=(HttpServletRequest)request;
		
		String username=users.getParameter("username");
		String password=users.getParameter("password");
		boolean rememberMe=false;//记住我
		String host=StringUtils.getRemoteAddr((HttpServletRequest)request);
		String captcha="";
		boolean moblie = true;
		//生成无状态 token
		UsernamePasswordToken token=new UsernamePasswordToken(username,password.toCharArray(),rememberMe,host,captcha,moblie);
		try {
			//5、委托给 Realm 进行登录
			getSubject(request, response).login(token);
			} catch (Exception e) {
//			e.printStackTrace();
			onLoginFail(response); //6、登录失败
			return false;
			}
			return true;
	}
	//登录失败时默认返回 401 状态码
	private void onLoginFail(ServletResponse response) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpResponse.getWriter().write("login error");
		String  message = "false";
		renderString(httpResponse, message,"UTF-8");//返回ID
	}
	/**
	 * 客户端返回字符串
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string,
			String type) {
		try {
			response.reset();
			response.setContentType(type);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * 客户端返回JSON字符串
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object),
				"application/json");
	}
}
