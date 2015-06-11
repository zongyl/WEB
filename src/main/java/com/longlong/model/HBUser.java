package com.longlong.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ��������վ��ץȡ������
 * @author long
 *
 */
@Entity
@Table(name = "HB_USER")
public class HBUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    /** ���� */
	private String name;
	/** ������λ */
	private String gzdw;
	/** ��ϵ��ַ */
	private String address;
	/** ���� */
	private String mz;
	/** �绰 */
	private String phone;
	/** ���� */
	private String fax;
	/** �Ա� */
	private String sex;
	/** ְ�� */
	private String zc;
	/** ʡ�� */
	private String sf;
	/** �ʱ� */
	private String post;
	/** �ֻ� */
	private String mobile;
	/** �������� */
	private String email;
	/** ס�޿�ʼʱ�� */
	private String zsstartdate;
	/** ס�޽���ʱ�� */
	private String zsenddate;
	/** ��������վ�ϵ����� */
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
