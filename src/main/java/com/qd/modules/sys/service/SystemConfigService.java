/**
 * Copyright &copy; 2015-2020 <a href="http://www.qd.org/">qd</a> All rights reserved.
 */
package com.qd.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qd.common.persistence.Page;
import com.qd.common.service.CrudService;
import com.qd.modules.sys.entity.SystemConfig;
import com.qd.modules.sys.dao.SystemConfigDao;

/**
 * 系统配置Service
 * @author liugf
 * @version 2016-02-07
 */
@Service
@Transactional(readOnly = true)
public class SystemConfigService extends CrudService<SystemConfigDao, SystemConfig> {

	public SystemConfig get(String id) {
		return super.get(id);
	}
	
	public List<SystemConfig> findList(SystemConfig systemConfig) {
		return super.findList(systemConfig);
	}
	
	public Page<SystemConfig> findPage(Page<SystemConfig> page, SystemConfig systemConfig) {
		return super.findPage(page, systemConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SystemConfig systemConfig) {
		super.save(systemConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemConfig systemConfig) {
		super.delete(systemConfig);
	}
	
}