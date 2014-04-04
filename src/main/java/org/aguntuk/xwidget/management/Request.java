package org.aguntuk.xwidget.management;

import java.lang.reflect.Method;

import org.aguntuk.xwidget.util.OutputFormat;

public class Request {
	private Method serviceMethod;
	private String template;
	private OutputFormat outputType;
	private String requestClassName;
	private RequestKey[] requestKey;
	private String jsp;
	
	public Request(Method serviceMethod, String template, String format) {
		this.serviceMethod=serviceMethod;
		this.template=template;
		this.outputType = OutputFormat.valueOf(format);
	}

	public Method getServiceMethod() {
		return serviceMethod;
	}

	public String getTemplate() {
		return template;
	}

	public OutputFormat getOutputType() {
		return outputType;
	}
	
	public String getRequestClassName() {
		return requestClassName;
	}

	public void setRequestClassName(String requestClassName) {
		this.requestClassName = requestClassName;
	}

	public RequestKey[] getRequestKey() {
		return requestKey;
	}

	public void setRequestKey(RequestKey[] requestKey) {
		this.requestKey = requestKey;
	}

	public String getJsp() {
		return jsp;
	}

	public void setJsp(String jsp) {
		this.jsp = jsp;
	}	
}
