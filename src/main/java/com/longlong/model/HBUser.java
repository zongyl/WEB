package com.longlong.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 环保部网站上抓取的数据
 * @author long
 *
 */
@Entity
@Table(name = "HB_USER")
public class HBUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    /** 姓名 */
	private String name;
	/** 工作单位 */
	private String gzdw;
	/** 联系地址 */
	private String address;
	/** 民族 */
	private String mz;
	/** 电话 */
	private String phone;
	/** 传真 */
	private String fax;
	/** 性别 */
	private String sex;
	/** 职称 */
	private String zc;
	/** 省份 */
	private String sf;
	/** 邮编 */
	private String post;
	/** 手机 */
	private String mobile;
	/** 电子邮箱 */
	private String email;
	/** 住宿开始时间 */
	private String zsstartdate;
	/** 住宿结束时间 */
	private String zsenddate;
	/** 环保部网站上的序列 */
	private Long updno;
	
	public Long getUpdno() {
		return updno;
	}
	public void setUpdno(Long updno) {
		this.updno = updno;
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
	public String getGzdw() {
		return gzdw;
	}
	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getSf() {
		return sf;
	}
	public void setSf(String sf) {
		this.sf = sf;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZsstartdate() {
		return zsstartdate;
	}
	public void setZsstartdate(String zsstartdate) {
		this.zsstartdate = zsstartdate;
	}
	public String getZsenddate() {
		return zsenddate;
	}
	public void setZsenddate(String zsenddate) {
		this.zsenddate = zsenddate;
	}
	
}
