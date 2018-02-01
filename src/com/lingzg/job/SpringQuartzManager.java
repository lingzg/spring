package com.lingzg.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz调度管理器
 * 
 * @author lingzg
 * 
 */
public class SpringQuartzManager {

	@Autowired
	private SchedulerFactoryBean schedulerFactory;

	/**
	 * @Description: 添加一个定时任务
	 * 
	 */
	public void addJob(ScheduleJob job) {
		try {
			// schedulerFactoryBean 由spring创建注入
			Scheduler scheduler = schedulerFactory.getScheduler();
			// 不存在，创建一个
			JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
					.withIdentity(job.getJobName(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式构建一个新的trigger
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup())
					.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 */
	public void modifyJobTime(ScheduleJob job) {
		try {
			// schedulerFactoryBean 由spring创建注入
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			// Trigger已存在，那么更新相应的定时设置
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 */
	public void removeJob(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			scheduler.pauseTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(job.getJobName(), job.getJobGroup()));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:启动所有定时任务
	 */
	public void startJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:关闭所有定时任务
	 */
	public void shutdownJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 计划中的任务 指那些已经添加到quartz调度器的任务
	 */
	public void findPlanedJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					ScheduleJob job = new ScheduleJob();
					job.setJobName(jobKey.getName());
					job.setJobGroup(jobKey.getGroup());
					job.setDesc("触发器:" + trigger.getKey());
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					job.setJobStatus(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						job.setCronExpression(cronExpression);
					}
					jobList.add(job);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 运行中的任务
	 */
	public void findRunningJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
			List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
			for (JobExecutionContext executingJob : executingJobs) {
				ScheduleJob job = new ScheduleJob();
				JobDetail jobDetail = executingJob.getJobDetail();
				JobKey jobKey = jobDetail.getKey();
				Trigger trigger = executingJob.getTrigger();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDesc("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 暂停任务
	 */
	public void pauseJob(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			scheduler.pauseJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 恢复任务
	 */
	public void resumeJob(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			scheduler.resumeJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 立即运行任务 这里的立即运行，只会运行一次，方便测试时用。
	 * quartz是通过临时生成一个trigger的方式来实现的，这个trigger将在本次任务运行完成之后自动删除。
	 */
	public void triggerJob(ScheduleJob job) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
			scheduler.triggerJob(jobKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}