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

import java.util.HashMap;
import java.util.Iterator;
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
	
	public Service getService(String serviceName) {
		return serviceRequestMap.get(serviceName);
	}
	
	public Service getServiceForRequestType(String requestType) {
		Service retVal = null;
		for(Iterator<String> iter=serviceRequestMap.keySet().iterator();iter.hasNext();) {
			Service service = serviceRequestMap.get(iter.next());
			if(service.getRequest(requestType)!=null) {
				retVal = service;
			}
		}
		return retVal;
	}
	

}
