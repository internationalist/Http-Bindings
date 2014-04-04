package org.aguntuk.xwidget.management;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Service {
	
	private Class<?> serviceHandlerClass;
	private Object serviceInstance;
	private Map<String, Request> requestMethodMap = new HashMap<String, Request>();
	
	public Service(Class<?> serviceHandler, Object serviceInstance) {
		this.serviceHandlerClass = serviceHandler;
		this.serviceInstance = serviceInstance;
	}

	public Class<?> getServiceHandlerClass() {
		return serviceHandlerClass;
	}

	public Object getServiceInstance() {
		return serviceInstance;
	}
	
	public void addRequestMethod(String requestType, Request serviceRequest) {
		requestMethodMap.put(requestType, serviceRequest);
	}
	
	public Request getRequest(String requestType) {
		Set<String> keys = requestMethodMap.keySet();
		Request retVal = null;
		for(Iterator<String> iter = keys.iterator();iter.hasNext();) {
			String key = iter.next();
			if(requestType.indexOf(key) > -1) {
				retVal=requestMethodMap.get(key);
			}
		}
		return retVal;
	}
	

}
