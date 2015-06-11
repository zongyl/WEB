package com.longlong.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author ZONGYL
 *
 */
@Entity
@Table(name = "TEST_UNIT")
public class Units {

//	/**
//	 * 表明 关联关系
//	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	
//	/**
//	 * 外键关联字段
//	 */
//	@JoinColumn(name = "FK")
//	private Users users;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 部门名称 */
	private String unitName;
	
	/**
	 * 一个部门下面多个员工
	 * 
	 */
	@OneToMany(mappedBy = "unit", targetEntity = Users.class, fetch = FetchType.LAZY)
	private List<Users> emys;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<Users> getEmys() {
		return emys;
	}

	public void setEmys(List<Users> emys) {
		this.emys = emys;
	}
	
	
//	@Lob
//	@Basic(fetch = FetchType.EAGER)
//	/*
//	 * 指定字段类型    该字段类型 为 longtext  且为非空
//	 */
//	@Column(name = "POST_TEXT", columnDefinition = "LONGTEXT NOT NULL")
//	private String postText;
//	
//	@Lob
//	@Basic(fetch = FetchType.LAZY)
//	@Column(name = "POST_ATTACH", columnDefinition = "BLOB")
//	private byte[] postAttach;
	
}
