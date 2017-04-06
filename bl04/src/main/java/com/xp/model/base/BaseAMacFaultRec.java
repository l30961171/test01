package com.xp.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAMacFaultRec<M extends BaseAMacFaultRec<M>> extends Model<M> implements IBean {

	public void setMacFaultRecId(java.lang.Long macFaultRecId) {
		set("macFaultRecId", macFaultRecId);
	}

	public java.lang.Long getMacFaultRecId() {
		return get("macFaultRecId");
	}

	public void setMacNum(java.lang.String macNum) {
		set("macNum", macNum);
	}

	public java.lang.String getMacNum() {
		return get("macNum");
	}

	public void setMacFaultNum(java.lang.String macFaultNum) {
		set("macFaultNum", macFaultNum);
	}

	public java.lang.String getMacFaultNum() {
		return get("macFaultNum");
	}

	public void setStartTime(java.util.Date startTime) {
		set("startTime", startTime);
	}

	public java.util.Date getStartTime() {
		return get("startTime");
	}

	public void setEndTime(java.util.Date endTime) {
		set("endTime", endTime);
	}

	public java.util.Date getEndTime() {
		return get("endTime");
	}

}
