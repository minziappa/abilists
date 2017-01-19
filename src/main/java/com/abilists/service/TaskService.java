package com.abilists.service;

import java.util.List;

import com.abilists.bean.model.UserTaskModel;
import com.abilists.bean.para.task.DltTaskPara;
import com.abilists.bean.para.task.IstTaskPara;
import com.abilists.bean.para.task.SltTaskPara;
import com.abilists.bean.para.task.UdtTaskPara;
import com.abilists.common.bean.CommonPara;

public interface TaskService extends PagingService {

	public boolean istTask(IstTaskPara istTaskPara) throws Exception;
	public boolean udtTask(UdtTaskPara udtTaskPara) throws Exception;
	public boolean dltTask(DltTaskPara dltTaskPara) throws Exception;

	public UserTaskModel sltTodayTask(CommonPara commonPara) throws Exception;
	public UserTaskModel sltTask(SltTaskPara sltTaskPara, boolean forView) throws Exception;

	public int sltTaskSum(SltTaskPara sltTaskPara) throws Exception;
	public List<UserTaskModel> sltTaskList(SltTaskPara sltTaskPara) throws Exception;
	public List<UserTaskModel> sltTaskList(CommonPara commonPara) throws Exception;

	public boolean validateTodayTask(IstTaskPara istTaskPara) throws Exception;
}