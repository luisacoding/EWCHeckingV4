package com.arun.app;

public class Purchase {
	private java.lang.String spurno;
	private java.lang.String compno;
	private java.lang.String itemno;
	private int	lineid;
	private float	purqty;
	private	float	purprc;
	
	/** 明细总行数 */
	private int lineTotal;
	
	public Purchase() {
		this.spurno = null;
		this.compno = null;
		this.itemno = null;
		this.lineid = 0;
		this.purqty = 0.0f;
		this.purprc = 0.0f;
		this.lineTotal = 0;
	}
	
	public void setSpurno(String spurno) {
		this.spurno = spurno;
	}
	
	public void setCompno(String compno) {
		this.compno = compno;
	}
	
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	
	public void setPurqty(float purqty) {
		this.purqty = purqty;
	}
	
	public void setPurprc(float purprc) {
		this.purprc = purprc;
	}
	
	public void setLineTotal(int lineTotal) {
		this.lineTotal = lineTotal;
	}
	
	/** get method */
	public String getSpurno() {
		return this.spurno;
	}
	
	public String getCompno() {
		return this.compno;
	}
	
	public String getItemno() {
		return this.itemno;
	}
	
	public int getLineid() {
		return this.lineid;
	}
	
	public float getPurqty() {
		return this.purqty;
	}
	
	public float getPurprc() {
		return this.purprc; 
	}
	
	public int getLineTotal() {
		return this.lineTotal;
	}
}
