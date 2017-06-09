package com.qd.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qd.common.service.TreeService;
import com.qd.common.utils.StringUtils;
import com.qd.modules.test.entity.TestTree;
import com.qd.modules.test.dao.TestTreeDao;

/**
 * 树结构生成Service
 * @author Harry
 * @version 2017-06-01
 */
@Service
@Transactional(readOnly = true)
public class TestTreeService extends TreeService<TestTreeDao, TestTree> {

	public TestTree get(String id) {
		return super.get(id);
	}
	
	public List<TestTree> findList(TestTree testTree) {
		if (StringUtils.isNotBlank(testTree.getParentIds())){
			testTree.setParentIds(","+testTree.getParentIds()+",");
		}
		return super.findList(testTree);
	}
	
	@Transactional(readOnly = false)
	public void save(TestTree testTree) {
		super.save(testTree);
	}
	
	@Transactional(readOnly = false)
	public void delete(TestTree testTree) {
		super.delete(testTree);
	}
	
}