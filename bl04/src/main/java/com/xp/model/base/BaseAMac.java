package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAMac<M extends BaseAMac<M>> extends Model<M> implements IBean {

	public void setMacNum(java.lang.String macNum) {
		set("macNum", macNum);
	}

	public java.lang.String getMacNum() {
		return get("macNum");
	}

	public void setMacName(java.lang.String macName) {
		set("macName", macName);
	}

	public java.lang.String getMacName() {
		return get("macName");
	}

	public void setMacTonId(java.lang.Integer macTonId) {
		set("macTonId", macTonId);
	}

	public java.lang.Integer getMacTonId() {
		return get("macTonId");
	}

	public void setMacTypeId(java.lang.Integer macTypeId) {
		set("macTypeId", macTypeId);
	}

	public java.lang.Integer getMacTypeId() {
		return get("macTypeId");
	}

	public void setMacClassId(java.lang.Integer macClassId) {
		set("macClassId", macClassId);
	}

	public java.lang.Integer getMacClassId() {
		return get("macClassId");
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