package com.abilists.service;

import com.abilists.common.bean.CommonPara;

import io.paging.bean.PagingBean;

public interface PagingService {
	public PagingBean makePaging(CommonPara commonPara, int sum) throws Exception;
}
