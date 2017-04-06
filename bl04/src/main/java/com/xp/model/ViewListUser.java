package com.xp.model;

import com.jfinal.plugin.activerecord.Page;
import com.xp.model.base.BaseViewListUser;
import com.xp.model.ViewListUser;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class ViewListUser extends BaseViewListUser<ViewListUser> {
	public static final ViewListUser dao = new ViewListUser();
	
	//分页查询用户
		public Page<ViewListUser> listUser(
				int page,
				int rows,
				String where,
				String whereMap
				){
			Object[] whereArr = {};
			if(!where.equals("")){
				where = " where " + where;
				whereArr = whereMap.split("~@~");
			}
			Page<ViewListUser> users = paginate(
					page, 
					rows, 
					"select *", 
					"from view_list_user"+where,
					whereArr
					);
			return users;
		}
}