package com.yhjj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 余杭交警用户表(暂扣系统)
 * @author long
 *
 */
@Entity
@Table(name = "YHJJ_USRE")
public class YhUsers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**  */
	private String loginName;
	/**  */
	private String loginPwd;
	/**  */
	private String realName;
	/**  */
	private String pwdTipQuestion;
	/**  */
	private String pwdTipAnswer;
	/**  */
	private String birthday;
	/**  */
	private String dep;
	/**  */
	private String auth;
	/**  */
	private String mobile;
	/**  */
	private String telephone;
	/**  */
	private String QQ;
	/**  */
	private String msn;
	/**  */
	private String remarker;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPwdTipQuestion() {
		return pwdTipQuestion;
	}
	public void setPwdTipQuestion(String pwdTipQuestion) {
		this.pwdTipQuestion = pwdTipQuestion;
	}
	public String getPwdTipAnswer() {
		return pwdTipAnswer;
	}
	public void setPwdTipAnswer(String pwdTipAnswer) {
		this.pwdTipAnswer = pwdTipAnswer;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getRemarker() {
		return remarker;
	}
	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}
	
}
