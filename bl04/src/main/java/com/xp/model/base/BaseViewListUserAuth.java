package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseViewListUserAuth<M extends BaseViewListUserAuth<M>> extends Model<M> implements IBean {

	public void setUserId(java.lang.Integer userId) {
		set("userId", userId);
	}

	public java.lang.Integer getUserId() {
		return get("userId");
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