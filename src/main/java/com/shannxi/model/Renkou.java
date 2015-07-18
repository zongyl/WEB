package com.shannxi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SX_RENKOU")
public class Renkou {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/** 户号 */
	private String houseId;
	/** 编号 */
	private String no;
	/** 姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 身份证号 */
	private String cardId;
	/** 民族 */
	private String mz;
	/** 文化程度 */
	private String wh;
	/** 户口性质 */
	private String hk;
	/** 婚姻状况 */
	private String hy;
	/** 初婚日期 */
	private String chrq;
	/** 与户主关系 */
	private String hzgx;
	/** 户籍所在地 */
	private String hjszd;
	/** 现居住地 */
	private String xjzd;
	/** 迁移或流动类型  */
	private String qytype;
	/** 变动日期  */
	private String bdrq;
	
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getMz() {
		return mz;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getHk() {
		return hk;
	}
	public void setHk(String hk) {
		this.hk = hk;
	}
	public String getHy() {
		return hy;
	}
	public void setHy(String hy) {
		this.hy = hy;
	}
	public String getChrq() {
		return chrq;
	}
	public void setChrq(String chrq) {
		this.chrq = chrq;
	}
	public String getHzgx() {
		return hzgx;
	}
	public void setHzgx(String hzgx) {
		this.hzgx = hzgx;
	}
	public String getHjszd() {
		return hjszd;
	}
	public void setHjszd(String hjszd) {
		this.hjszd = hjszd;
	}
	public String getXjzd() {
		return xjzd;
	}
	public void setXjzd(String xjzd) {
		this.xjzd = xjzd;
	}
	public String getQytype() {
		return qytype;
	}
	public void setQytype(String qytype) {
		this.qytype = qytype;
	}
	public String getBdrq() {
		return bdrq;
	}
	public void setBdrq(String bdrq) {
		this.bdrq = bdrq;
	}
}
