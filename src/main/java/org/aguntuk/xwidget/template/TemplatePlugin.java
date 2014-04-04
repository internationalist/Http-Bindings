package org.aguntuk.xwidget.template;

import java.io.IOException;

import freemarker.template.TemplateException;

public abstract class TemplatePlugin {
	
	public abstract String process(Object argument, String template) throws IOException, TemplateException;
}
