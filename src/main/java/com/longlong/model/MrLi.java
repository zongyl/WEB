package com.longlong.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "2")
public class MrLi extends Users implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7246202132531761468L;
	
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
