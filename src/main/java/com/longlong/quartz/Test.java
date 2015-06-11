/**
 * 
 */
package com.longlong.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;

/**
 * @author zongyl
 *
 */
public class Test {
	
	public static void main(String[] args){
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			
			scheduler.start();
			
//			JobDetail jobDetail = new JobDetail();
//			
//			Trigger trigger = TriggerUtils.computeFireTimes(arg0, arg1, arg2)
//			scheduler.shutdown();
			
			JobDetailImpl job = getJob(PrintJob.class, "job", "18606531369", "我的联通号码！");
			
			JobDetailImpl cronJob = getJob(PrintJob.class, "cronJob", "15257138461", "我的移动号码！");
			
			/*
			 * 测试结果  simpleTrigger 光设置重复间隔  执行不了   必须得设置重复次数   endTime优先结束
			 */
			SimpleTriggerImpl trigger = new SimpleTriggerImpl();
			trigger.setName(job.getName());
			trigger.setStartTime(new Date(System.currentTimeMillis()+1000));
			trigger.setRepeatCount(7);//重复次数
			trigger.setRepeatInterval(1000*10);//重复间隔10秒
			trigger.setEndTime(new Date(System.currentTimeMillis()+60*1000));//1分钟后结束
			
			
			CronTriggerImpl cronTrigger = new CronTriggerImpl();
			cronTrigger.setName(cronJob.getName());
			cronTrigger.setCronExpression("8 * * * * ?");//每逢8秒触发
			cronTrigger.setEndTime(new Date(System.currentTimeMillis()+5*60*1000));//5分钟后停止
			
			scheduler.scheduleJob(job, trigger);
			scheduler.scheduleJob(cronJob, cronTrigger);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 封装Job
	 */
	static JobDetailImpl getJob(Class<? extends Job> clz, String jobName, String phones, String msg){
		//实例化job
		JobDetailImpl job = new JobDetailImpl();
		job.setJobClass(clz);
		job.setName(jobName);
		job.getJobDataMap().put("phones", phones);
		job.getJobDataMap().put("msg", msg);
		return job;
	}
}





