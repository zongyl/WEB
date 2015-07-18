package com.shannxi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 陕西人口
 * @author long
 *
 */
@Entity
@Table(name = "SX_HUKOU" )
public class Hukou {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String houseId;
	//登记时间
	private String djsj;
	//登记人员
	private String djry;
	
	private String info;
	//调查员
	private String dcy;
	//户别  家庭户
	private String type;
	//公安户口薄号
	private String no;
	//户口类型
	private String type1;
	//小区名称
	private String address;
	//门牌号
	private String doorno;
	//建卡日期
	private String createCardDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getDjsj() {
		return djsj;
	}
	public void setDjsj(String djsj) {
		this.djsj = djsj;
	}
	public String getDjry() {
		return djry;
	}
	public void setDjry(String djry) {
		this.djry = djry;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDcy() {
		return dcy;
	}
	public void setDcy(String dcy) {
		this.dcy = dcy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDoorno() {
		return doorno;
	}
	public void setDoorno(String doorno) {
		this.doorno = doorno;
	}
	public String getCreateCardDate() {
		return createCardDate;
	}
	public void setCreateCardDate(String createCardDate) {
		this.createCardDate = createCardDate;
	}
}
