package com.abilists.service;

import java.util.List;

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

public interface AdminMasterService extends PagingService {

	public MTechModel sltMTech(SltMTechPara sltMTechPara) throws Exception;
	public List<MTechModel> sltMTechList(SltMTechPara sltMTechPara) throws Exception;
	public List<MTechModel> sltMTechAll() throws Exception;

	public boolean istMTech(IstMTechPara istMTechPara) throws Exception;
	public boolean udtMTech(SltMTechPara sltMTechPara) throws Exception;
	public List<MRoleModel> sltMRoleAll() throws Exception;
	public int sltMTechSum(SltMTechPara sltMTechPara) throws Exception;
	public boolean dltMTech(DltMTechPara dltMTechPara) throws Exception;

	public boolean istMTechDetail(IstMTechDetailPara istMTechDetailPara) throws Exception;
	public boolean udtMTechDetail(UdtMTechDetailPara udtMTechDetailPara) throws Exception;
	public boolean dltMTechDetail(DltMTechDetailPara dltMTechDetailPara) throws Exception;
	public MTechDetailModel sltMTechDetail(SltMTechDetailPara sltMTechDetailPara) throws Exception;
	public List<MTechDetailModel> sltMTechDetailList(SltMTechDetailPara sltMTechDetailPara) throws Exception;
	public List<MTechDetailModel> sltMTechDetailAll() throws Exception;

	public MRoleModel sltMRole(SltMRolePara sltMRolePara) throws Exception;
	public List<MRoleModel> sltMRoleList(SltMRolePara sltMRolePara) throws Exception;
	public boolean istMRole(IstMRolePara istMRolePara) throws Exception;
	public boolean udtMRole(UdtMRolePara udtMRolePara) throws Exception;
	public int sltMRoleSum(SltMRolePara sltMRolePara) throws Exception;
	public boolean dltMRole(DltMRolePara dltMRolePara) throws Exception;

	public MIndustryModel sltMIndustry(SltMIndustryPara sltMIndustryPara) throws Exception;
	public List<MIndustryModel> sltMIndustryList(SltMIndustryPara sltMIndustryPara) throws Exception;
	public boolean istMIndustry(IstMIndustryPara istMIndustryPara) throws Exception;
	public boolean udtMIndustry(UdtMIndustryPara udtMIndustryPara) throws Exception;
	public int sltMIndustrySum(SltMIndustryPara sltMIndustryPara) throws Exception;
	public boolean dltMIndustry(DltMIndustryPara dltMIndustryPara) throws Exception;

	public List<MIndustryModel> sltMIndustryAll() throws Exception;
	public List<MLanguageModel> sltMLanguageAll() throws Exception;
}