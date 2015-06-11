package com.qianbao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QB_TASK")
public class Qbtask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	/** task name */
	private String name;
	/** 赏金 */	
	private String sj;
	/** 天数 需要几天完成 */
	private String days;
	/** 保证金 */
	private String bzj;
	/** 任务领取次数  */
	private String count;
	/**  */
	private float remake;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getBzj() {
		return bzj;
	}

	public void setBzj(String bzj) {
		this.bzj = bzj;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public float getRemake() {
		return remake;
	}

	public void setRemake(float remake) {
		this.remake = remake;
	}
	
}
