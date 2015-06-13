package com.yonghe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "YH_ORDER")
public class Order {

	/**
	 * 永和订单数据
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 订单金额 */
	private String totalAmount;
	/** 收货地址 */
	private String deliveryAddress;
	/** 收货人 */
	private String contactPerson;
	/** 具体地址 */
	private String deliveryAddressComments;
	/** 订单号 */
	private String orderCodeOnline;
	/**  */
	private String email;
	/**  */
	private String contactPhone;
	/** 后台ID */
	private String orderId;
	/**  */
	private String userComments;
	/**  */
	private String estimatedDeliveryTime;
	/** 门店名称 */
	private String storeName;
	/** 订单详情  */
	private String orderItemList;
	
	private String commits;
	
	public String getCommits() {
		return commits;
	}
	public void setCommits(String commits) {
		this.commits = commits;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getDeliveryAddressComments() {
		return deliveryAddressComments;
	}
	public void setDeliveryAddressComments(String deliveryAddressComments) {
		this.deliveryAddressComments = deliveryAddressComments;
	}
	public String getOrderCodeOnline() {
		return orderCodeOnline;
	}
	public void setOrderCodeOnline(String orderCodeOnline) {
		this.orderCodeOnline = orderCodeOnline;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	public String getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}
	public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(String orderItemList) {
		this.orderItemList = orderItemList;
	}
}
