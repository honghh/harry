package com.qd.modules.ws.entity;

public class Oauth2Token {

	// 网页授权接口调用凭证
	private String accessToken;
	// 凭证有效时长
	private int expiresIn;
	// 用于刷新凭证
	private String refreshToken;
	// 用户标识
	private String OpenId;
	// 用户授权作用域
	private String scope;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

}
