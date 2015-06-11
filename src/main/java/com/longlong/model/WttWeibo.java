package com.longlong.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WTT_WEIBO")
public class WttWeibo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Long userId;
	
	private String weibo;
	
	private Long rzfs;
	
	private Long gz;
	
	private Long fs;
	
	private Long wb;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public Long getRzfs() {
		return rzfs;
	}

	public void setRzfs(Long rzfs) {
		this.rzfs = rzfs;
	}

	public Long getGz() {
		return gz;
	}

	public void setGz(Long gz) {
		this.gz = gz;
	}

	public Long getFs() {
		return fs;
	}

	public void setFs(Long fs) {
		this.fs = fs;
	}

	public Long getWb() {
		return wb;
	}

	public void setWb(Long wb) {
		this.wb = wb;
	}

}
