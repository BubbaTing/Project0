package com.borderlands.test.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.borderlands.dao.BackpackDao;

public class MockitoTestUsage {
	
	public void test1() {
		BackpackDao mock = mock(BackpackDao.class);
		
		when(mock.searchBackpackNumber(978));
		//assert(mock.searchBackpackNumber(978),true);
	}
	
	
}
