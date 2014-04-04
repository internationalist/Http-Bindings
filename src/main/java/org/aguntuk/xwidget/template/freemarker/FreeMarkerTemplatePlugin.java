package org.aguntuk.xwidget.template.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aguntuk.xwidget.template.TemplatePlugin;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class FreeMarkerTemplatePlugin extends TemplatePlugin {
	private static FreeMarkerTemplatePlugin instance;
	 Configuration cfg = null;

	
	private FreeMarkerTemplatePlugin() throws IOException {		List<TemplateLoader> loaderList = new ArrayList<TemplateLoader>();
		cfg = new Configuration();
		String templatePath =  FreemarkerPluginInitializer.getInstance().getFreemarkerTemplatePath();
		if((templatePath != null) && templatePath.length() > 0) {
			FileTemplateLoader ftl = new FileTemplateLoader(new File(templatePath));
			loaderList.add(ftl);
		}
		ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "/");
		loaderList.add(ctl);
		MultiTemplateLoader mtl = new MultiTemplateLoader(loaderList.toArray(new TemplateLoader[1]));
        //cfg.setDirectoryForTemplateLoading(new File(FreemarkerPluginInitializer.getInstance().getFreemarkerTemplatePath()));
		cfg.setTemplateLoader(mtl);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));		
	}

	@Override
	public String process(Object argument, String templateName) throws IOException, TemplateException {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("datamodel", argument);
		Template template = cfg.getTemplate(templateName);
		StringWriter sw = new StringWriter();
		template.process(root, sw);
		return sw.toString();
	}
	
	public static synchronized FreeMarkerTemplatePlugin getInstance() throws IOException {
		if(instance==null) {
			instance = new FreeMarkerTemplatePlugin();
		}
		return instance;
	}
}
