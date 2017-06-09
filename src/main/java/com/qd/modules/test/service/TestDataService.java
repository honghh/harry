package com.qd.modules.test.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qd.common.persistence.Page;
import com.qd.common.service.CrudService;
import com.qd.modules.test.entity.TestData;
import com.qd.modules.test.dao.TestDataDao;

/**
 * 单表生成Service
 * @author Harry
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class TestDataService extends CrudService<TestDataDao, TestData> {

	public TestData get(String id) {
		return super.get(id);
	}
	
	public List<TestData> findList(TestData testData) {
		return super.findList(testData);
	}
	
	public Page<TestData> findPage(Page<TestData> page, TestData testData) {
		return super.findPage(page, testData);
	}
	
	@Transactional(readOnly = false)
	public void save(TestData testData) {
		super.save(testData);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestData testData) {
		super.delete(testData);
	}
	@Transactional(readOnly = false)
	public boolean deleteAll(String ids) {
		boolean flag = true;
		try {
			List<String> idList = Arrays.asList(ids.split(","));
			super.dao.deleteAll(idList);
		} catch (Exception e) {
			flag = false;
		}
		return flag;		
	}
}