/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.common.persistence;

import java.util.HashMap;

/**
 * 查询参数类
 * 
 * @author Harry
 * @version 2013-8-23
 */
public class Parameter extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * 构造类，例：new Parameter(id, parentIds)
	 * 
	 * @param values
	 *            参数值
	 */
	public Parameter(Object... values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				put("p" + (i + 1), values[i]);
			}
		}
	}

	/**
	 * 构造类，例：new Parameter(new Object[][]{{"id", id}, {"parentIds", parentIds}})
	 * 
	 * @param parameters
	 *            参数二维数组
	 */
	public Parameter(Object[][] parameters) {
		if (parameters != null) {
			for (Object[] os : parameters) {
				if (os.length == 2) {
					put((String) os[0], os[1]);
				}
			}
		}
	}

}
