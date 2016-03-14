package com.arun.app;

public class Battery {
	private  String	classThree;
	private	 String Status;
	private	float	qty;
	private	float	allocatedqty;
	private	float	mountqty;

	private	String	itemname;
	private	String	description;
	private	String	itemno;
	private	String	unit;
	
	public Battery() {
		super();
	}
	public String getClassThree() {
		return classThree;
	}
	public void setClassThree(String classThree) {
		this.classThree = classThree;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	public float getAllocatedqty() {
		return allocatedqty;
	}
	public void setAllocatedqty(float allocatedqty) {
		this.allocatedqty = allocatedqty;
	}
	public float getMountqty() {
		return mountqty;
	}
	public void setMountqty(float mountqty) {
		this.mountqty = mountqty;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItemno() {
		return itemno;
	}
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
}
