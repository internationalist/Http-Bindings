package org.aguntuk.xwidget.template.freemarker;

import javax.servlet.ServletConfig;

import org.aguntuk.xwidget.template.PluginInitializer;

class FreemarkerPluginInitializer extends PluginInitializer {
	private ServletConfig config;
	private static FreemarkerPluginInitializer instance;
	private FreemarkerPluginInitializer() {
		
	}
	
	static synchronized FreemarkerPluginInitializer getInstance() {
		if(instance == null) {
			instance = new FreemarkerPluginInitializer();
		}
		return instance;
	}
	@Override
	public void setServletConfig(ServletConfig config) {
		this.config=config;
	}
	
	String getFreemarkerTemplatePath() {
		return config.getInitParameter("FreemarkerTemplatePath");
	}


}
