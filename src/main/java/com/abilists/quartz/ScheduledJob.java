//package com.abilists.quartz;
//
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import com.abilists.service.IndexService;
//
///**
// * Not used
// * @author njoonk
// *
// */
//public class ScheduledJob extends QuartzJobBean {
//	final Logger logger = LoggerFactory.getLogger(ScheduledJob.class);
//
//	@Autowired
//	IndexService indexService;
//
//	public ScheduledJob() {
//	}
//
//	@Override
//	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
//		try {
//			logger.info("--Start batch--");
//			indexService.makeIndexPage();
//			logger.info("--End batch--");
//		} catch (Exception e) {
//			logger.error("Exception >> ", e);
//		}
//	}
//
//}
