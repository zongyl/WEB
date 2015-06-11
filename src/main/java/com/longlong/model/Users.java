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
/** �̳�  ����ӳ�����
 * SINGLE_TABLE  �����ౣ�浽ͬһ�����У�ͨ��һ���ֶ�����
 * 
 * JOINED  ��������ͬ�Ĳ��ֱ��浽ͬһ�����У���ͬ�Ĳ��ַֿ��棬ͨ�������ӻ�ȡ�������ݡ�
 * 
 * TABLE_PER_CLASS : ÿ�����Ӧ�Լ��ı�һ�㲻�Ƽ��������ַ�ʽ��
 * 
 *   */
@Inheritance(strategy =InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** ���ӱ�ʶ */
	@Column(insertable = false, updatable = false)
	private Long type;
	
	private String username;

	/**
	 * ʱ��  �ֱ���� java.util.Date   .Timestamp
	 */
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	
	/**
	 * JPA Ĭ��ӳ�������ֶ� ����   ��������ʾָ�� ��ӳ�䵽���ݱ�
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
