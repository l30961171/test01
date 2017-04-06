package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseIoOriginRec<M extends BaseIoOriginRec<M>> extends Model<M> implements IBean {

	public void setRecId(java.lang.Long recId) {
		set("recId", recId);
	}

	public java.lang.Long getRecId() {
		return get("recId");
	}

	public void setRecSeq(java.lang.String recSeq) {
		set("recSeq", recSeq);
	}

	public java.lang.String getRecSeq() {
		return get("recSeq");
	}

	public void setPlantId(java.lang.String plantId) {
		set("plantId", plantId);
	}

	public java.lang.String getPlantId() {
		return get("plantId");
	}

	public void setCardNumber(java.lang.String cardNumber) {
		set("cardNumber", cardNumber);
	}

	public java.lang.String getCardNumber() {
		return get("cardNumber");
	}

	public void setSign(java.lang.String sign) {
		set("sign", sign);
	}

	public java.lang.String getSign() {
		return get("sign");
	}

	public void setOriginStr(java.lang.String originStr) {
		set("originStr", originStr);
	}

	public java.lang.String getOriginStr() {
		return get("originStr");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("createTime", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("createTime");
	}

}
