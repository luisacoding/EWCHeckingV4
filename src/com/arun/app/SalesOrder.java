package com.arun.app;

public class SalesOrder {
	private java.lang.String sysno;
	private java.lang.String compno;
	private int lineid;
	private java.lang.String itemno;
	private float qty;
	private float prc;
	private float locsum;
	
	/* 订单明细汇总 */
	private int	lineTotal;
	
	/** Default constructor with no args */
	public SalesOrder() {
		sysno = null;
		compno = null;
		lineid = 0;
		itemno = null;
		qty = 0;
		prc = 0;
		locsum =  0;
		lineTotal = 0;
	}
	
	/** set sysno */
	public void setSysno(String sysno) {
		this.sysno = sysno;
	}
	
	/** set compno */
	public void setCompno(String compno) {
		this.compno = compno;
	}
	
	/** set lineid */
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	
	/** set itemno */
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	
	/** set quantity */
	public void setQty(float qty) {
		this.qty = qty;
	}
	
	/** set price */
	public void setPrice(float prc) {
		this.prc = prc;
	}
	
	/** set locsum */
	public void setLocsum(float locsum) {
		this.locsum = locsum;
	}
	
	/** set lineTotal */
	public void setLineTotal(int lineTotal) {
		this.lineTotal = lineTotal;
	}
	
	/** get sysno */
	public String getSysno() {
		return sysno;
	}
	
	/** get company number */
	public String getCompno() {
		return compno;
	}
	
	/** get line number */
	public int getLineid() {
		return lineid;
	}
	
	/** get itemno */
	public String getItemno() {
		return itemno;
	}
	
	/** get quantity **/
	public float getQty() {
		return qty;
	}
	
	/** get price */
	public float getPrc() {
		return prc;
	}
	
	/** get locsum */
	public float getLocsum() {
		return locsum;
	}
	
	/** get lineTotal */
	public int getLineTotal() {
		return lineTotal;
	}
}
