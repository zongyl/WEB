package com.longlong.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "TEST_USERS")
/** 继承  三种映射策略
 * SINGLE_TABLE  父子类保存到同一个表中，通过一个字段区别。
 * 
 * JOINED  父子类相同的部分保存到同一个表中，不同的部分分开存，通过表连接获取完整数据。
 * 
 * TABLE_PER_CLASS : 每个类对应自己的表，一般不推荐采用这种方式。
 * 
 *   */
@Inheritance(strategy =InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** 父子标识 */
	@Column(insertable = false, updatable = false)
	private Long type;
	
	private String username;

	/**
	 * 时间  分别对用 java.util.Date   .Timestamp
	 */
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	
	/**
	 * JPA 默认映射所有字段 到表   该属性显示指定 不映射到数据表
	 */
	@Transient
	private String remake;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Units.class)
	@JoinColumn(name = "uid", referencedColumnName = "id")
	private Units unit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		this.unit = unit;
	}
	
//	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, targetEntity = Users.class)
//	private List<Users> children;

	
	
}
