package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWebUserRole<M extends BaseWebUserRole<M>> extends Model<M> implements IBean {

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}

	public java.lang.Integer getUserId() {
		return get("userId");
	}

	public void setRoleId(java.lang.Integer roleId) {
		set("roleId", roleId);
	}

	public java.lang.Integer getRoleId() {
		return get("roleId");
	}

}