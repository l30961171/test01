package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWebRoleMenu<M extends BaseWebRoleMenu<M>> extends Model<M> implements IBean {

	public void setMenuId(java.lang.Integer menuId) {
		set("menuId", menuId);
	}

	public java.lang.Integer getMenuId() {
		return get("menuId");
	}

	public void setRoleId(java.lang.Integer roleId) {
		set("roleId", roleId);
	}

	public java.lang.Integer getRoleId() {
		return get("roleId");
	}

}
