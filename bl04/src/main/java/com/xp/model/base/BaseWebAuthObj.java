package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWebAuthObj<M extends BaseWebAuthObj<M>> extends Model<M> implements IBean {

	public void setAuthId(java.lang.Integer authId) {
		set("authId", authId);
	}

	public java.lang.Integer getAuthId() {
		return get("authId");
	}

	public void setRoleId(java.lang.Integer roleId) {
		set("roleId", roleId);
	}

	public java.lang.Integer getRoleId() {
		return get("roleId");
	}

	public void setAuthName(java.lang.String authName) {
		set("authName", authName);
	}

	public java.lang.String getAuthName() {
		return get("authName");
	}

	public void setAuthValue(java.lang.String authValue) {
		set("authValue", authValue);
	}

	public java.lang.String getAuthValue() {
		return get("authValue");
	}

}
