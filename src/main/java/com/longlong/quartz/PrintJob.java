/**
 * 
 */
package com.longlong.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zongyl
 *
 */
public class PrintJob implements Job {

	private String phones;
	
	private String msg;
	
	private String interval;
	
	private int count = 0;
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("count:"+count);
		if(count>0&&count/Integer.parseInt(interval)==0){
			System.out.println(".......job execute ..........."+format.format(new Date()));
			//System.out.println("第"+this.count+"次,略过！");
		}
		System.out.println(".......job execute ..........."+format.format(new Date()));
		System.out.println("phones:"+getPhones()+"=======msg:"+getMsg());
		this.count += 1;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
