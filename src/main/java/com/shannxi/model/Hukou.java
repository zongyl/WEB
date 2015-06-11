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
@Table(name = "" )
public class Hukou {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
