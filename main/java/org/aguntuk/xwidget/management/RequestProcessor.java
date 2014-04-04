/**
The MIT License (MIT)

Copyright (c) <year> <copyright holders>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package org.aguntuk.xwidget.management;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aguntuk.xwidget.XWidget;
import org.aguntuk.xwidget.exception.GeneralException;
import org.aguntuk.xwidget.template.TemplatePlugin;
import org.aguntuk.xwidget.util.ClassUtils;
import org.aguntuk.xwidget.util.ViewType;
import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;

import freemarker.template.TemplateException;

public enum RequestProcessor {
	INSTANCE;
	@SuppressWarnings("unchecked")
	public Object processRequest(HttpServletRequest request, HttpServletResponse response, TemplatePlugin templatePlugin, String serviceName) throws GeneralException {
		try {
			Gson gson = null;
			XWidget widgetTmpl = null;
			Object serviceMethodReturn;
			String requestType = request.getServletPath();
			String xWidgetType = request.getParameter("XWidgetType");
			Service service = ServiceFacade.INSTANCE.getService(serviceName);
			Request blockRequest = service.getRequest(requestType);
			String requestClass = blockRequest.getRequestClassName();
			RequestKey requestKeys[] = blockRequest.getRequestKey();
			Method m = blockRequest.getServiceMethod();
			
			if(requestClass != null && requestClass.length() > 0) {
				//create instance of the class
				Object bean = Class.forName(requestClass).newInstance();
				HashMap<String, String[]> map = new HashMap<String, String[]>();
				for(Enumeration<String> names = request.getParameterNames();names.hasMoreElements();) {
					String name = names.nextElement();
					map.put(name, request.getParameterValues(name));
				}
				BeanUtils.populate(bean, map);
				serviceMethodReturn = m.invoke(service.getServiceInstance(), bean);				
			} else if(requestKeys!= null && requestKeys.length > 0) {
				Object[] argValues = new Object[requestKeys.length];
				int j = 0;
				for(RequestKey requestKey:requestKeys) {
					Object argumentValue = null;
					String requestKeyType = requestKey.getKeyType();
					if(ClassUtils.INSTANCE.isArrayType(requestKeyType)) {
						argumentValue = request.getParameterValues(requestKey.getKeyName());
						argValues[j] = ClassUtils.INSTANCE.fromStringObjectToOtherArrayType((String[])argumentValue, requestKeyType);
					} else {
						argumentValue = request.getParameter(requestKey.getKeyName());
						argValues[j] = ClassUtils.INSTANCE.fromStringToOtherType((String)argumentValue, requestKeyType);
					}
					++j;
				}
				serviceMethodReturn = m.invoke(service.getServiceInstance(), argValues);				
			} else {
				serviceMethodReturn = m.invoke(service.getServiceInstance());
			}
			String returnValue = null;
			//if a xWidgetType is specified from the browser then thats our output otherwise just follow the standard annotation
			if(xWidgetType != null) {
				gson = new Gson();
				widgetTmpl = gson.fromJson(xWidgetType, XWidget.class);
				ViewType type = ViewType.toViewType(widgetTmpl.getType());
				switch(type) {
				//for a list type view we expect the java service to return a tree structure. In other words a Map of a Map
					case LIST:
						//TODO: Implement this part later. 
						break;
				}
			} else {
				switch(blockRequest.getOutputType()) {
					case html:
						String templateFile = blockRequest.getTemplate();
						returnValue = templatePlugin.process(serviceMethodReturn, templateFile);
						break;
					case json:
						gson = new Gson();
						returnValue = gson.toJson(serviceMethodReturn);
						break;
					case jsp:
						request.setAttribute("out", serviceMethodReturn);
				        request.getRequestDispatcher(blockRequest.getJsp()).forward(request, response);
				        returnValue=null;
						break;
				}
			}
			//get the template
			return returnValue;
		}catch(InstantiationException ie) {
			throw new GeneralException(ie);
		} catch(IllegalAccessException iae) {
			throw new GeneralException(iae);			
		} catch(ClassNotFoundException cnfe) {
			throw new GeneralException(cnfe);			
		} catch(InvocationTargetException ite) {
			throw new GeneralException(ite);			
		} catch(IOException ioe) {
			throw new GeneralException(ioe);			
		} catch(TemplateException te) {
			throw new GeneralException(te);			
		} catch(Exception exc) {
			throw new GeneralException(exc);			
		}
	}
}
