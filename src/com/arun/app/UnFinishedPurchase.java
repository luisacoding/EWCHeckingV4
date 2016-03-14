package com.arun.app;

public class UnFinishedPurchase {
	private String	spurnoitemno;
	public String getSpurnoitemno() {
		return spurnoitemno;
	}
	public void setSpurnoitemno(String spurnoitemno) {
		this.spurnoitemno = spurnoitemno;
	}
	private	String 	spurno;
	private	String	duedt;
	private	String	lastname;
	private	String	u_name;
	private	String	compno;
	private	String	compname;
	private	int		lineid;
	private	String	itemno;
	public String getItemno() {
		return itemno;
	}
	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
	private	String	itemname;
	private	String	descript;
	private	String	msname;
	private	float	purqty;
	private	float	purprc;
	private	float	pursum;
	private	float	bgnqty;
	private	float	inqty;
	private float	outqty;
	private	float	jiecun;
	private	int		year;
	private	int		month;
	private	String	prdname;
	/*
	 *  	送检总数量 recqty_z     
        	已送待检数 recqty_daij 
       		已送已检数 recqty_yij 
        	正常接收数 rcvqty_yij 
       		让步接收数, crcqty_yij 
        	退换货数,rtnqty_yij 
	 */
	private	float	recqty_z;
	private	float	recqty_daij;
	private	float	recqty_yij;
	private	float	rcvqty_yij;
	private float	crcqty_yij;
	private	float	rtnqty_yij;
	private	float	qianjiaoshu;
	private	float	unpicked;
	private	String	remarks;
	private String	handoverDate;
	private	float	realusage;
	
	
	public float getRealusage() {
		return realusage;
	}
	public void setRealusage(float realusage) {
		this.realusage = realusage;
	}
	public String getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(String handoverDate) {
		this.handoverDate = handoverDate;
	}
	public String getSpurno() {
		return spurno;
	}
	public void setSpurno(String spurno) {
		this.spurno = spurno;
	}
	public String getDuedt() {
		return duedt;
	}
	public void setDuedt(String duedt) {
		this.duedt = duedt;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getCompno() {
		return compno;
	}
	public void setCompno(String compno) {
		this.compno = compno;
	}
	public String getCompname() {
		return compname;
	}
	public void setCompname(String compname) {
		this.compname = compname;
	}
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getMsname() {
		return msname;
	}
	public void setMsname(String msname) {
		this.msname = msname;
	}
	public float getPurqty() {
		return purqty;
	}
	public void setPurqty(float purqty) {
		this.purqty = purqty;
	}
	public float getPurprc() {
		return purprc;
	}
	public void setPurprc(float purprc) {
		this.purprc = purprc;
	}
	public float getPursum() {
		return pursum;
	}
	public void setPursum(float pursum) {
		this.pursum = pursum;
	}
	public float getBgnqty() {
		return bgnqty;
	}
	public void setBgnqty(float bgnqty) {
		this.bgnqty = bgnqty;
	}
	public float getInqty() {
		return inqty;
	}
	public void setInqty(float inqty) {
		this.inqty = inqty;
	}
	public float getOutqty() {
		return outqty;
	}
	public void setOutqty(float outqty) {
		this.outqty = outqty;
	}
	public float getJiecun() {
		return jiecun;
	}
	public void setJiecun(float jiecun) {
		this.jiecun = jiecun;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getPrdname() {
		return prdname;
	}
	public void setPrdname(String prdname) {
		this.prdname = prdname;
	}
	public float getRecqty_z() {
		return recqty_z;
	}
	public void setRecqty_z(float recqty_z) {
		this.recqty_z = recqty_z;
	}
	public float getRecqty_daij() {
		return recqty_daij;
	}
	public void setRecqty_daij(float recqty_daij) {
		this.recqty_daij = recqty_daij;
	}
	public float getRecqty_yij() {
		return recqty_yij;
	}
	public void setRecqty_yij(float recqty_yij) {
		this.recqty_yij = recqty_yij;
	}
	public float getRcvqty_yij() {
		return rcvqty_yij;
	}
	public void setRcvqty_yij(float rcvqty_yij) {
		this.rcvqty_yij = rcvqty_yij;
	}
	public float getCrcqty_yij() {
		return crcqty_yij;
	}
	public void setCrcqty_yij(float crcqty_yij) {
		this.crcqty_yij = crcqty_yij;
	}
	public float getRtnqty_yij() {
		return rtnqty_yij;
	}
	public void setRtnqty_yij(float rtnqty_yij) {
		this.rtnqty_yij = rtnqty_yij;
	}
	public float getQianjiaoshu() {
		return qianjiaoshu;
	}
	public void setQianjiaoshu(float qianjiaoshu) {
		this.qianjiaoshu = qianjiaoshu;
	}
	public float getUnpicked() {
		return unpicked;
	}
	public void setUnpicked(float unpicked) {
		this.unpicked = unpicked;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
