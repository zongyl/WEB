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
//	 * ���� ������ϵ
//	 */
//	@ManyToOne(fetch = FetchType.LAZY)
//	
//	/**
//	 * ��������ֶ�
//	 */
//	@JoinColumn(name = "FK")
//	private Users users;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** �������� */
	private String unitName;
	
	/**
	 * һ������������Ա��
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
//	 * ָ���ֶ�����    ���ֶ����� Ϊ longtext  ��Ϊ�ǿ�
//	 */
//	@Column(name = "POST_TEXT", columnDefinition = "LONGTEXT NOT NULL")
//	private String postText;
//	
//	@Lob
//	@Basic(fetch = FetchType.LAZY)
//	@Column(name = "POST_ATTACH", columnDefinition = "BLOB")
//	private byte[] postAttach;
	
}
