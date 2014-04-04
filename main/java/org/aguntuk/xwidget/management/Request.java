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
