package com.longlong.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * weituitui用户表
 * @author long
 *
 */
@Entity
@Table(name = "WTT_USER")
public class WttUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long userId;
	private String name;
	private String renzheng;
	private String vip;
	private Double money1;
	private Double money2;
	private Double money3;
	private Double money4;
	private Double money5;
	/** 注册时间 */
	private String zcsj;
	/** 发布任务 */
	private Long fbrw;
	/** 提交稿件 */
	private Long tjgj;
	/** 发表帖子 */
	private Long fbtz;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRenzheng() {
		return renzheng;
	}
	public void setRenzheng(String renzheng) {
		this.renzheng = renzheng;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public Double getMoney1() {
		return money1;
	}
	public void setMoney1(Double money1) {
		this.money1 = money1;
	}
	public Double getMoney2() {
		return money2;
	}
	public void setMoney2(Double money2) {
		this.money2 = money2;
	}
	public Double getMoney3() {
		return money3;
	}
	public void setMoney3(Double money3) {
		this.money3 = money3;
	}
	public Double getMoney4() {
		return money4;
	}
	public void setMoney4(Double money4) {
		this.money4 = money4;
	}
	public Double getMoney5() {
		return money5;
	}
	public void setMoney5(Double money5) {
		this.money5 = money5;
	}
	public String getZcsj() {
		return zcsj;
	}
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}
	public Long getFbrw() {
		return fbrw;
	}
	public void setFbrw(Long fbrw) {
		this.fbrw = fbrw;
	}
	public Long getTjgj() {
		return tjgj;
	}
	public void setTjgj(Long tjgj) {
		this.tjgj = tjgj;
	}
	public Long getFbtz() {
		return fbtz;
	}
	public void setFbtz(Long fbtz) {
		this.fbtz = fbtz;
	}
	
}
