/**
 * Copyright &copy; 2012-2017 <a href="https://www.tech-qd.com">Jeeqd</a> All rights reserved.
 */
package com.qd.common.supcan.freeform;

import com.qd.common.supcan.common.Common;
import com.qd.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 硕正FreeForm
 * 
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}

	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}

}
