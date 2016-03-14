package com.arun.app;

public class ERPInventory {
	/** 物料编码 */
	private java.lang.String itemno;
	
	/** 库存总数量 */ 
	private float	quantity;
	
	/** 01成品仓 */
	private float	whouseOne;
	
	/** 02中性机 */
	private float	whouseTwo;
	
	/** 04成品不良仓 */
	private float	whouseFour;
	
	/** 05胶料 */
	private float	whouseFive;
	
	/** 06电子料 */
	private float	whouseSix;
	
	/** 07装配料 */
	private float	whouseSeven;

	/** 08包装料 */
	private float	whouseEight;
	
	/** 09材料半成品 */
	private float	whouseNine;
	
	/** 10模具材料仓 */
	private float	whouseTen;
	
	/** 11广告品 */
	private float	whouseEleven;
	
	/** 12辅料 */
	private float	whouseTwelve;
	
	/** 13材料不良仓 */
	private float	whouseThirteen;
	
	/** 15客供品仓 */
	private float	whouseFifteen;
		
	/** 16包装清单仓 */
	private float	whouseSixteen;
	
	/** 18模具仓 */
	private float	whouseEighteen;
	
	/** 20淘宝仓 */
	private float	whouseTwenty;
	
	/** 合格数量 */
	private float   quantified;
	
	/** 不及格数量 */
	private float	unquantified;
	
	/** otherqty 其他出入库走WMS特殊业务 未审核 数量 */
	private float	otherqty;
	
	/** allstring 其他出入库走WMS特殊业务 未审核 单据 */
	private java.lang.String	allstring;
	
	/** @param difference 差异数，ERP库存总数 减去 WMS库存总数 */
	private	float	difference;
	
	/** WMS相关，allqty = 合格 + 不合格  */
	private float	allqty;
	
	/** WMS,mountqty 上架数量 */
	private float 	mqty;
	
	/** WMS,feedback qty 反馈数量 */
	private float fqty;
	
	/** WMS,unfeedback qty = mqty - fqty 未反馈数量 */
	private float ufqty;
	
	
	/** constructor with no args */
	public ERPInventory() {
		this.itemno = null;
		this.quantity = 0.0f;
		this.whouseOne = 0.0f;
		this.whouseTwo = 0.0f;
		this.whouseFour = 0.0f;
		this.whouseFive = 0.0f;
		this.whouseSix = 0.0f;
		this.whouseSeven = 0.0f;
		this.whouseEight = 0.0f;
		this.whouseNine = 0.0f;
		this.whouseTen = 0.0f;
		this.whouseEleven = 0.0f;
		this.whouseTwelve = 0.0f;
		this.whouseThirteen = 0.0f;
		this.whouseFifteen = 0.0f;
		this.whouseSixteen = 0.0f;
		this.whouseEighteen = 0.0f;
		this.whouseTwenty = 0.0f;
		this.otherqty = 0.0f;
		this.allstring = null;
		this.difference = 0.0f;
		this.allqty = 0.0f;
		this.mqty	= 0.0f;
		this.fqty	= 0.0f;
		this.ufqty	= 0.0f;
	}
	
	/** set itemno */
	public void setItemno (String itemno) {
		this.itemno = itemno;
	}
	
	/** set quantity */
	public void setQuantity (float quantity) {
		this.quantity = quantity;
	}
	
	/** set whouseOne */
	public void setWhouseOne (float whouseOne) {
		this.whouseOne = whouseOne;
	}
	
	/** set whouseTwo */
	public void setWhouseTwo (float whouseTwo) {
		this.whouseTwo = whouseTwo;
	}
	
	/** set whouseFour */
	public void setWhouseFour (float whouseFour) {
		this.whouseFour = whouseFour;
	}
	
	/** set whouseFive */
	public void setWhouseFive (float whouseFive) {
		this.whouseFive = whouseFive;
	}
	
	/** set whouseSix */
	public void setWhouseSix (float whouseSix) {
		this.whouseSix = whouseSix;
	}
	
	/** set whouseSeven */
	public void setWhouseSeven (float whouseSeven) {
		this.whouseSeven = whouseSeven;
	}
	
	/** set whouseEight */
	public void setWhouseEight (float whouseEight) {
		this.whouseEight = whouseEight;
	}
	
	/** set whouseNine */
	public void setWhouseNine (float whouseNine) {
		this.whouseNine = whouseNine;
	}
	
	/** set whouseTen */
	public void setWhouseTen (float whouseTen) {
		this.whouseTen = whouseTen;
	}
	
	/** set whouseEleven */
	public void setWhouseEleven (float whouseEleven) {
		this.whouseEleven = whouseEleven;
	}
	
	/** set whouseTwelve */
	public void setWhouseTwelve (float whouseTwelve) {
		this.whouseTwelve = whouseTwelve;
	}
	
	/** set whouseThirteen */
	public void setWhouseThirteen (float whouseThirteen) {
		this.whouseThirteen = whouseThirteen;
	}
	
	/** set whouseFifteen */
	public void setWhouseFifteen (float whouseFifteen) {
		this.whouseFifteen = whouseFifteen;
	}
	
	/** set whouseSixteen */
	public void setWhouseSixteen (float whouseSixteen) {
		this.whouseSixteen = whouseSixteen;
	}
	
	/** set whouseEighteen */
	public void setWhouseEighteen (float whouseEighteen) {
		this.whouseEighteen = whouseEighteen;
	}
	
	/** set whouseTwenty */
	public void setWhouseTwenty (float whouseTwenty) {
		this.whouseTwenty = whouseTwenty;
	}
	
	/** set otherqty */
	public void setOtherqty (float otherqty) {
		this.otherqty = otherqty;
	}
	
	/** set allstring */
	public void setAllstring (String allstring) {
		this.allstring = allstring;
	}
	
	/** set quantified */
	public void setQuantified (float quantified) {
		this.quantified = quantified;
	}
	
	/** set unquantified*/
	public void setUnquantified (float unquantified) {
		this.unquantified = unquantified;
	}
	
	/** set difference*/
	public void setDifference (float difference) {
		this.difference = difference;
	}
	
	/** set allqty*/
	public void setAllqty (float allqty) {
		this.allqty = allqty;
	}
	
	/** set mqty*/
	public void setMountqty (float mqty) {
		this.mqty = mqty;
	}
	
	/** set fqty*/
	public void setFeedbackqty (float fqty) {
		this.fqty = fqty;
	}
	
	/** set ufqty*/
	public void setUfeedbackqty (float ufqty) {
		this.ufqty = ufqty;
	}
	
	/** get itemno */
	public String getItemno () {
		return this.itemno;
	}
	
	/** get quantity */
	public float getQuantity () {
		return this.quantity;
	}
	
	/** get whouseOne */
	public float getWhouseOne () {
		return this.whouseOne;
	}
	
	/** get whouseTwo */
	public float getWhouseTwo () {
		return this.whouseTwo;
	}
	
	/** get whouseFour */
	public float getWhouseFour () {
		return this.whouseFour;
	}
	
	/** get whouseFive */
	public float getWhouseFive () {
		return this.whouseFive;
	}
	
	/** get whouseSix */
	public float getWhouseSix () {
		return this.whouseSix;
	}
	
	/** get whouseSeven */
	public float getWhouseSeven () {
		return this.whouseSeven;
	}
	
	/** get whouseEight */
	public float getWhouseEight () {
		return this.whouseEight;
	}
	
	/** get whouseNine */
	public float getWhouseNine () {
		return this.whouseNine;
	}
	
	/** get whouseTen */
	public float getWhouseTen () {
		return this.whouseTen;
	}
	
	/** get whouseEleven */
	public float getWhouseEleven () {
		return this.whouseEleven;
	}
	
	/** get whouseTwelve */
	public float getWhouseTwelve () {
		return this.whouseTwelve;
	}
	
	/** get whouseThirteen */
	public float getWhouseThirteen () {
		return this.whouseThirteen;
	}
	
	
	/** get whouseFifteen */
	public float getWhouseFifteen () {
		return this.whouseFifteen;
	}
	
	/** get whouseSixteen */
	public float getWhouseSixteen () {
		return this.whouseSixteen;
	}
	
	/** get whouseEighteen */
	public float getWhouseEighteen () {
		return this.whouseEighteen;
	}
	
	/** get whouseTwenty */
	public float getWhouseTwenty () {
		return this.whouseTwenty;
	}
	
	/** get otherqty */
	public float getOtherqty () {
		return this.otherqty;
	}
	
	/** get allstring */
	public String getAllstring () {
		return this.allstring;
	}
	
	/** get quantified*/
	public float getQuantified () {
		return this.quantified;
	}
	
	/** get unquantified*/
	public float getUnquantified () {
		return this.unquantified;
	}
	
	/** get difference*/
	public float getDifference () {
		return this.difference;
	}
	
	/** get allqty*/
	public float getAllqty () {
		return this.allqty;
	}
	
	/** get mqty*/
	public float getMountqty () {
		return this.mqty;
	}
	
	/** get fqty*/
	public float getFeedbackqty () {
		return this.fqty;
	}
	
	/** get ufqty*/
	public float getUfeedbackqty () {
		return this.ufqty;
	}
	
	/** isEquals */
	public boolean isEquals (ERPInventory inv) {
		if (this.getItemno().equals(inv.getItemno()) )
			return true;
		return false;
	}
}
