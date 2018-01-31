package com.lingzg.job;

import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob job = (ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
		System.out.println("----------任务ID："+job.getJobId()+"--------");
		System.out.println("----------任务执行时间"+new Date().toString()+"--------");
		System.out.println("----------任务描述："+job.getDesc()+"--------");
		String className=job.getClassName();;
		String methodName=job.getMethodName();
		Map<String,Object> params = job.getParams();
		if("com.cecsys.secmax.checkroute.service.PxcheckPlanService".equals(className)
				&&"genTask(PxcheckPlan, String)".equals(methodName)){
			/*Long planId=(Long) params.get("planId");
			PxcheckPlanService service = SpringUtils.getBean("pxcheckPlanService", PxcheckPlanService.class);
			PxcheckPlan plan = service.getById(planId);
			try {
				service.genTask(plan, "system");
			} catch (Exception e) {
				System.out.println("----------任务ID："+job.getJobId()+"执行失败--------");
				e.printStackTrace();
			}*/
		}
	}

}
