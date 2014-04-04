package org.aguntuk.xwidget.management;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Baby's
 * This class will be the container for all requests in the various service interface. Basically this forms the facade.
 */
public enum ServiceFacade {
	INSTANCE;
	private Map<String, Service> serviceRequestMap = new HashMap<String, Service>();
	
	public void addService(String requesType, Service service) {
		serviceRequestMap.put(requesType, service);
	}
	
	public Service getService(String requestType) {
		return serviceRequestMap.get(requestType);
	}
	

}
