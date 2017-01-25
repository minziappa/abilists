package com.abilists.service;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.bean.SlaveDbMappingBean;
import com.abilists.common.bean.CommonPara;
import com.abilists.dao.MSequenceDao;

import io.paging.Paging;
import io.paging.bean.PagingBean;

public abstract class AbstractService {

	@Autowired
    private Configuration configuration;	
	
	final Logger logger = LoggerFactory.getLogger(AbstractService.class);

	protected static String BASE_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

	protected static int INT_DB_NUM = 0;

	@Autowired
	@Qualifier("sqlSessionSlaveFactory")
	public SqlSessionFactoryBean sqlSessionSlaveFactory;
	@Autowired
	public SqlSession mAbilistsDao;

	@Autowired
	private SlaveDbMappingBean slaveDbMappingBean;
	private Map<String, BasicDataSource> mappingDb;

	protected BasicDataSource getDispersionDb() {
		mappingDb = slaveDbMappingBean.getSltedSlaveDb();

		INT_DB_NUM = INT_DB_NUM + 1;
		INT_DB_NUM = INT_DB_NUM % mappingDb.size();

		return (BasicDataSource) mappingDb.get(String.valueOf(INT_DB_NUM));
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	protected long getSequence(String seqName) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seqName", seqName);
		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			mAbilistsDao.getMapper(MSequenceDao.class).updateSequece(map);
		} catch (Exception e) {
			logger.error("Exception error - updateSequece", e);
			throw e;
		}

		return (long) map.get("id");
	}

	public PagingBean makePaging(CommonPara commonPara, int sum) throws Exception {
		PagingBean paging = new PagingBean();
		// Set Paging list
		if(commonPara.getAllCount() <= 0) {
			paging.setAllCount(sum);
		} else {
			paging.setAllCount(commonPara.getAllCount());
		}
		Paging.setTotalLimit(Paging.PER_PAGE, Paging.PAGE_LIMIT, 
				configuration.getInt("paging.limit.total"));
		Paging.linkPaging(paging, commonPara.getNowPage());

		return paging;
	}

}