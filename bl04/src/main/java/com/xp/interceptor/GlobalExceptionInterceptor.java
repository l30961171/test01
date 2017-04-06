/**
 * Copyright (c) 2011-2016, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xp.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.xp.util.XPFunctions;

/**
 * ActiveRecord declare transaction.
 * Example: @Before(Tx.class)
 */
public class GlobalExceptionInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
			try {
				inv.invoke();
			} catch (Exception e) {
				e.printStackTrace();
				Controller controller = inv.getController();
				controller.renderText(XPFunctions.getCurTime("yyyy-MM-dd HH:mm:ss")+"-->"+e.toString());
			}
	}
}



