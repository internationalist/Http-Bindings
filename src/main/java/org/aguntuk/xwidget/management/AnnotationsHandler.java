package org.aguntuk.xwidget.management;

import static java.lang.System.out;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import org.aguntuk.xwidget.annotations.RequestMeta;
import org.aguntuk.xwidget.annotations.ServiceInterfaceMeta;
import org.aguntuk.xwidget.exception.InitializationException;
import org.reflections.Reflections;

public enum AnnotationsHandler {
	INSTANCE;
	
	public void scanClassPathForRequestInterfaces(String targetPkg, String serviceName) throws InitializationException {
		try {
		    out.println(targetPkg);
		    Reflections reflections = new Reflections(targetPkg);
	
		     Set<Class<?>> annotated = 
		               reflections.getTypesAnnotatedWith(ServiceInterfaceMeta.class);
		     out.println(annotated.size());
		     
		     for(Iterator<Class<?>> iter = annotated.iterator();iter.hasNext()== true;) {
		    	 Class<?> obj = iter.next();
		    	 out.println("Annotated object found is " + obj);
		    	 Object instance = obj.newInstance();
		    	 Service service = ServiceFacade.INSTANCE.getService(serviceName);
		    	 if(service == null) {
			    	 service = new Service(obj, instance);
	    			 ServiceFacade.INSTANCE.addService(serviceName, service);			    	 
		    	 }
		    	 //iterate over the methods to find annotations of BlockRequest type
		    	 AnnotatedElement[] annotationElements = obj.getMethods();
		    	 for(AnnotatedElement annotationElement:annotationElements) {
		    		 if(annotationElement.isAnnotationPresent(RequestMeta.class)) {
		    			 RequestMeta requestMeta = annotationElement.getAnnotation(RequestMeta.class);
		    			 String requestName = requestMeta.requestName();
		    			 out.println("Request type is " + requestName);
		    			 String templateFile = requestMeta.templateFile();
		    			 String outputType = requestMeta.outputType();
		    			 String requestClass = requestMeta.requestObject();
		    			 String jsp = requestMeta.jsp();
		    			 RequestKey[] requestKeys = null;
		    			 if(requestClass == null || requestClass.length()==0) {
		    					 String keys[] = requestMeta.requestKeyName();
		    					 requestKeys = new RequestKey[keys.length];
		    					 Method method = (Method)annotationElement;
		    					 Class<?>[] classes = method.getParameterTypes();
		    					 for(int i = 0; i < keys.length; i++) {
		    						 requestKeys[i] = new RequestKey(keys[i], classes[i].getName());
		    					 }	    					 
		    			 }
		    			 
		    			 //create the Request class to hold all the info	    			 
		    			 Request request = new Request((Method)annotationElement, templateFile, outputType);
		    			 request.setRequestClassName(requestClass);
		    			 request.setRequestKey(requestKeys);
		    			 request.setJsp(jsp);
		    			 service.addRequestMethod(requestName, request);
		    		 }
		    	 }
		     }
		}catch(InstantiationException ie) {
			throw new InitializationException(ie);
		}catch(IllegalAccessException iae) {
			throw new InitializationException(iae);			
		}
	}

}
