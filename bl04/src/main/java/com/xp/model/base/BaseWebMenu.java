package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWebMenu<M extends BaseWebMenu<M>> extends Model<M> implements IBean {

	public void setMenuId(java.lang.Integer menuId) {
		set("menuId", menuId);
	}

	public java.lang.Integer getMenuId() {
		return get("menuId");
	}

	public void setMenuFid(java.lang.Integer menuFid) {
		set("menuFid", menuFid);
	}

	public java.lang.Integer getMenuFid() {
		return get("menuFid");
	}

	public void setMenuName(java.lang.String menuName) {
		set("menuName", menuName);
	}

	public java.lang.String getMenuName() {
		return get("menuName");
	}

	public void setMenuRemark(java.lang.String menuRemark) {
		set("menuRemark", menuRemark);
	}

	public java.lang.String getMenuRemark() {
		return get("menuRemark");
	}

	public void setMenuUrl(java.lang.String menuUrl) {
		set("menuUrl", menuUrl);
	}

	public java.lang.String getMenuUrl() {
		return get("menuUrl");
	}

	public void setMenuIcon(java.lang.String menuIcon) {
		set("menuIcon", menuIcon);
	}

	public java.lang.String getMenuIcon() {
		return get("menuIcon");
	}

	public void setMenuOrd(java.lang.Integer menuOrd) {
		set("menuOrd", menuOrd);
	}

	public java.lang.Integer getMenuOrd() {
		return get("menuOrd");
	}

	public void setIsShow(java.lang.String isShow) {
		set("isShow", isShow);
	}

	public java.lang.String getIsShow() {
		return get("isShow");
	}

}
