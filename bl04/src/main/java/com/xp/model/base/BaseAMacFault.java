package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAMacFault<M extends BaseAMacFault<M>> extends Model<M> implements IBean {

	public void setMacFaultNum(java.lang.String macFaultNum) {
		set("macFaultNum", macFaultNum);
	}

	public java.lang.String getMacFaultNum() {
		return get("macFaultNum");
	}

	public void setMacFaultName(java.lang.String macFaultName) {
		set("macFaultName", macFaultName);
	}

	public java.lang.String getMacFaultName() {
		return get("macFaultName");
	}

	public void setIsUse(java.lang.String isUse) {
		set("isUse", isUse);
	}

	public java.lang.String getIsUse() {
		return get("isUse");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setAddDate(java.util.Date addDate) {
		set("addDate", addDate);
	}

	public java.util.Date getAddDate() {
		return get("addDate");
	}

	public void setAddUser(java.lang.String addUser) {
		set("addUser", addUser);
	}

	public java.lang.String getAddUser() {
		return get("addUser");
	}

	public void setAltDate(java.util.Date altDate) {
		set("altDate", altDate);
	}

	public java.util.Date getAltDate() {
		return get("altDate");
	}

	public void setAltUser(java.lang.String altUser) {
		set("altUser", altUser);
	}

	public java.lang.String getAltUser() {
		return get("altUser");
	}

}