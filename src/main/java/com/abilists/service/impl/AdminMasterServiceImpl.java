package com.abilists.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.abilists.bean.model.MIndustryModel;
import com.abilists.bean.model.MLanguageModel;
import com.abilists.bean.model.MRoleModel;
import com.abilists.bean.model.MTechDetailModel;
import com.abilists.bean.model.MTechModel;
import com.abilists.bean.para.admin.DltMIndustryPara;
import com.abilists.bean.para.admin.DltMRolePara;
import com.abilists.bean.para.admin.DltMTechDetailPara;
import com.abilists.bean.para.admin.DltMTechPara;
import com.abilists.bean.para.admin.IstMIndustryPara;
import com.abilists.bean.para.admin.IstMRolePara;
import com.abilists.bean.para.admin.IstMTechDetailPara;
import com.abilists.bean.para.admin.IstMTechPara;
import com.abilists.bean.para.admin.SltMIndustryPara;
import com.abilists.bean.para.admin.SltMRolePara;
import com.abilists.bean.para.admin.SltMTechDetailPara;
import com.abilists.bean.para.admin.SltMTechPara;
import com.abilists.bean.para.admin.UdtMIndustryPara;
import com.abilists.bean.para.admin.UdtMRolePara;
import com.abilists.bean.para.admin.UdtMTechDetailPara;
import com.abilists.dao.MMasterDao;
import com.abilists.dao.SMasterDao;
import com.abilists.service.AbstractService;
import com.abilists.service.AdminMasterService;

import io.utility.security.CipherUtility;

@Service
public class AdminMasterServiceImpl extends AbstractService implements AdminMasterService {

	final Logger logger = LoggerFactory.getLogger(AdminMasterServiceImpl.class);

	@Autowired
	private SqlSession sAbilistsDao;
	@Autowired
    private Configuration configuration;	
	@Autowired
	private CipherUtility cipherUtility;

	@Override
	public MTechModel sltMTech(SltMTechPara sltMTechPara) throws Exception {
		MTechModel mTech = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mtNo", sltMTechPara.getMtNo());

			mTech = sAbilistsDao.getMapper(SMasterDao.class).sltMTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return  mTech;
	}

	@Override
	public List<MTechModel> sltMTechList(SltMTechPara sltMTechPara) throws Exception {
		List<MTechModel> mtechList = new ArrayList<MTechModel>();

		// Get now page
		int nowPage = sltMTechPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));
		map.put("mtSkill", sltMTechPara.getMtSkill());

		try {
			mtechList = sAbilistsDao.getMapper(SMasterDao.class).sltMTechList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return mtechList;
	}

	@Override
	public MTechDetailModel sltMTechDetail(SltMTechDetailPara sltMTechDetailPara) throws Exception {
		MTechDetailModel mTechDetail = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtdNo", sltMTechDetailPara.getMtdNo());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			mTechDetail = sAbilistsDao.getMapper(SMasterDao.class).sltMTechDetail(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return mTechDetail;
	}

	@Override
	public List<MTechDetailModel> sltMTechDetailList(SltMTechDetailPara sltMTechDetailPara) throws Exception {
		List<MTechDetailModel> mTechDetailList = null;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtNo", sltMTechDetailPara.getMtNo());
		map.put("mlCode", sltMTechDetailPara.getMlCode());

		try {
			sqlSessionSlaveFactory.setDataSource(getDispersionDb());
			mTechDetailList = sAbilistsDao.getMapper(SMasterDao.class).sltMTechDetailList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return mTechDetailList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean istMTechDetail(IstMTechDetailPara istMTechDetailPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtdLevel", istMTechDetailPara.getMtdLevel());
		map.put("mtdLevelExplain", istMTechDetailPara.getMtdLevelExplain());
		map.put("mtNo", istMTechDetailPara.getMtNo());
		map.put("mtKind", istMTechDetailPara.getMtKind());
		map.put("mlCode", istMTechDetailPara.getMlCode());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).insertMTechDetail(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("istMTechDetail error, mtNo={}", istMTechDetailPara.getMtNo());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean udtMTechDetail(UdtMTechDetailPara udtMTechDetailPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtdNo", udtMTechDetailPara.getMtdNo());
		map.put("mtdLevel", udtMTechDetailPara.getMtdLevel());
		map.put("mtdLevelExplain", udtMTechDetailPara.getMtdLevelExplain());
		map.put("mtNo", udtMTechDetailPara.getMtNo());
		map.put("mtKind", udtMTechDetailPara.getMtKind());
		map.put("mlCode", udtMTechDetailPara.getMlCode());
		map.put("mtdStatus", udtMTechDetailPara.getMlCode());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).updateMTechDetail(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}
		if(intResult < 1) {
			logger.error("insertUser error, mtdNo={}", udtMTechDetailPara.getMtdNo());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean dltMTechDetail(DltMTechDetailPara dltMTechDetailPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtdNo", dltMTechDetailPara.getMtdNo());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).deleteMTechDetail(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("dltMTechDetailPara error, mtdNo={}", dltMTechDetailPara.getMtdNo());
			return false;
		}

		return true;
	}

	@Override
	public int sltMTechSum(SltMTechPara sltMTechPara) throws Exception {
		int sum = 0;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mtSkill", sltMTechPara.getMtSkill());

			sum = sAbilistsDao.getMapper(SMasterDao.class).sltMTechSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean udtMTech(SltMTechPara sltMTechPara) throws Exception {

		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtNo", sltMTechPara.getMtNo());
		map.put("mtName", sltMTechPara.getMtName());
		map.put("mtSkill", sltMTechPara.getMtSkill());
		map.put("mtKind", sltMTechPara.getMtKind());
		map.put("mtVersion", sltMTechPara.getMtVersion());
		map.put("mtExplain", sltMTechPara.getMtExplain());
		map.put("mtRelation", sltMTechPara.getMtRelation());
		map.put("mtTrend", sltMTechPara.getMtTrend());
		map.put("mtStatus", "0");
		map.put("mtDelete", "0");

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).updateMTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(intResult < 1) {
			logger.error("updateMTech error, mtNo={}", sltMTechPara.getMtNo());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean dltMTech(DltMTechPara dltMTechPara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtNo", dltMTechPara.getMtNo());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).deleteMTech(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("dltMTechPara error, mtNo={}", dltMTechPara.getMtNo());
			return false;
		}

		return true;
	}

	@Override
	public MRoleModel sltMRole(SltMRolePara sltMRolePara) throws Exception {
		MRoleModel mRole = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mrNo", sltMRolePara.getMrNo());

			mRole = sAbilistsDao.getMapper(SMasterDao.class).sltMRole(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return  mRole;
	}

	@Override
	public List<MRoleModel> sltMRoleList(SltMRolePara sltMRolePara) throws Exception {

		List<MRoleModel> mtechList = null;

		// Get now page
		int nowPage = sltMRolePara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));
		map.put("mlCode", sltMRolePara.getMlCode());

		try {
			mtechList = sAbilistsDao.getMapper(SMasterDao.class).sltMRoleList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return mtechList;
	}

	@Override
	public List<MRoleModel> sltMRoleAll() throws Exception {

		List<MRoleModel> mRoleList = null;

		try {
			mRoleList = sAbilistsDao.getMapper(SMasterDao.class).sltMRoleAll();
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return mRoleList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean udtMRole(UdtMRolePara udtMRolePara) throws Exception {

		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mrNo", udtMRolePara.getMrNo());
		map.put("mrName", udtMRolePara.getMrName());
		map.put("mrCode", udtMRolePara.getMrCode());
		map.put("mrExplain", udtMRolePara.getMrExplain());
		map.put("mrResponsibilities", udtMRolePara.getMrResponsibilities());
		map.put("mrWorks", udtMRolePara.getMrWorks());
		map.put("mrSkills", udtMRolePara.getMrSkills());
		map.put("mrPrefers", udtMRolePara.getMrPrefers());
		map.put("mlCode", udtMRolePara.getMlCode());
		map.put("mrStatus", "0");

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).updateMRole(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(intResult < 1) {
			logger.error("udtMRole error, mtNo={}", udtMRolePara.getMrNo());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean dltMRole(DltMRolePara dltMRolePara) throws Exception {
		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mrNo", dltMRolePara.getMrNo());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).deleteMRole(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("dltMTechPara error, mtNo={}", dltMRolePara.getMrNo());
			return false;
		}

		return true;
	}

	@Override
	public int sltMRoleSum(SltMRolePara sltMRolePara) throws Exception {
		int sum = 0;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mlCode", sltMRolePara.getMlCode());

			sum = sAbilistsDao.getMapper(SMasterDao.class).sltMRoleSum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean istMTech(IstMTechPara istMTechPara) throws Exception {
		int intResult = 0;
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mtSkill", istMTechPara.getMtSkill());
			map.put("mtKind", istMTechPara.getMtKind());
			map.put("mtName", istMTechPara.getMtName());
			map.put("mtVersion", istMTechPara.getMtVersion());
			map.put("mtExplain", istMTechPara.getMtExplain());
			map.put("mtRelation", istMTechPara.getMtRelation());
			map.put("mtTrend", istMTechPara.getMtTrend());

			intResult = mAbilistsDao.getMapper(MMasterDao.class).insertMTech(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
		if(intResult < 1) {
			logger.error("istMTech error, mtKind={}", istMTechPara.getMtKind());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean istMRole(IstMRolePara istMRolePara) throws Exception {
		int intResult = 0;
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mrName", istMRolePara.getMrName());
			map.put("mrCode", istMRolePara.getMrCode());
			map.put("mrExplain", istMRolePara.getMrExplain());
			map.put("mrResponsibilities", istMRolePara.getMrResponsibilities());
			map.put("mrWorks", istMRolePara.getMrWorks());
			map.put("mrSkills", istMRolePara.getMrSkills());
			map.put("mrPrefers", istMRolePara.getMrPrefers());
			map.put("mlCode", istMRolePara.getMlCode());

			intResult = mAbilistsDao.getMapper(MMasterDao.class).insertMRole(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
		if(intResult < 1) {
			logger.error("istMRole error, userName={}", istMRolePara.getUserId());
			return false;
		}

		return true;
	}

	@Override
	public MIndustryModel sltMIndustry(SltMIndustryPara sltMIndustryPara) throws Exception {
		MIndustryModel mIndustry = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("miNo", sltMIndustryPara.getMiNo());
			map.put("mlCode", sltMIndustryPara.getLang());

			mIndustry = sAbilistsDao.getMapper(SMasterDao.class).sltMIndustry(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return  mIndustry;
	}

	@Override
	public List<MIndustryModel> sltMIndustryList(SltMIndustryPara sltMIndustryPara) throws Exception {

		List<MIndustryModel> mIndustryList = null;

		// Get now page
		int nowPage = sltMIndustryPara.getNowPage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowPage", (nowPage - 1) * configuration.getInt("paging.row.cnt"));
		map.put("row", configuration.getInt("paging.row.cnt"));
		map.put("mlCode", sltMIndustryPara.getMlCode());

		try {
			mIndustryList = sAbilistsDao.getMapper(SMasterDao.class).sltMIndustryList(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return mIndustryList;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean istMIndustry(IstMIndustryPara istMIndustryPara) throws Exception {
		int intResult = 0;
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("miLargeCategory", istMIndustryPara.getMiLargeCategory());
			map.put("miMiddleCategory", istMIndustryPara.getMiMiddleCategory());
			map.put("miCode", istMIndustryPara.getMiCode());
			map.put("miExplain", istMIndustryPara.getMiExplain());
			map.put("mlCode", istMIndustryPara.getMlCode());
			map.put("miStatus", istMIndustryPara.getMiStatus());
			map.put("miDelete", istMIndustryPara.getMiDelete());

			intResult = mAbilistsDao.getMapper(MMasterDao.class).insertMIndustry(map);

		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
		if(intResult < 1) {
			logger.error("istMIndustry error, userId={}", istMIndustryPara.getUserId());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean udtMIndustry(UdtMIndustryPara udtMIndustryPara) throws Exception {

		int intResult = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("miNo", udtMIndustryPara.getMiNo());
		map.put("miLargeCategory", udtMIndustryPara.getMiLargeCategory());
		map.put("miMiddleCategory", udtMIndustryPara.getMiMiddleCategory());
		map.put("miCode", udtMIndustryPara.getMiCode());
		map.put("miExplain", udtMIndustryPara.getMiExplain());
		map.put("mlCode", udtMIndustryPara.getMlCode());
		map.put("miStatus", udtMIndustryPara.getMiStatus());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).updateMIndustry(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		if(intResult < 1) {
			logger.error("udtMIndustry error, mtNo={}", udtMIndustryPara.getMiNo());
			return false;
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public boolean dltMIndustry(DltMIndustryPara dltMIndustryPara) throws Exception {
		int intResult = 0;
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("miNo", dltMIndustryPara.getMiNo());

		try {
			intResult = mAbilistsDao.getMapper(MMasterDao.class).deleteMIndustry(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		if(intResult < 1) {
			logger.error("dltMTechPara error, mtNo={}", dltMIndustryPara.getMiNo());
			return false;
		}

		return true;
	}

	@Override
	public int sltMIndustrySum(SltMIndustryPara sltMIndustryPara) throws Exception {
		int sum = 0;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mlCode", sltMIndustryPara.getMlCode());

			sum = sAbilistsDao.getMapper(SMasterDao.class).sltMIndustrySum(map);
		} catch (Exception e) {
			logger.error("Exception error", e);
		}

		return sum;
	}

	@Override
	public List<MLanguageModel> sltMLanguageAll() throws Exception {
		List<MLanguageModel> mLanguageList = null;
	
		try {
			mLanguageList = sAbilistsDao.getMapper(SMasterDao.class).sltMLanguageAll();
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
	
		return mLanguageList;
	}

	@Override
	public List<MIndustryModel> sltMIndustryAll() throws Exception {
		List<MIndustryModel> mIndustryList = null;
	
		try {
			mIndustryList = sAbilistsDao.getMapper(SMasterDao.class).sltMIndustryAll();
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
	
		return mIndustryList;
	}

	@Override
	public List<MTechModel> sltMTechAll() throws Exception {
	
		List<MTechModel> mtechList = null;
	
		try {
			mtechList = sAbilistsDao.getMapper(SMasterDao.class).sltMTechAll();
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}
	
		return mtechList;
	}

	@Override
	public List<MTechDetailModel> sltMTechDetailAll() throws Exception {

		List<MTechDetailModel> mtechDetailList = null;
	
		try {
			mtechDetailList = sAbilistsDao.getMapper(SMasterDao.class).sltMTechDetailAll();
		} catch (Exception e) {
			logger.error("Exception error", e);
			throw e;
		}

		return mtechDetailList;
	}

}