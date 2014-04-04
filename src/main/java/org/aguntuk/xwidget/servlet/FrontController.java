package org.aguntuk.xwidget.servlet;

import static java.lang.System.out;

import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aguntuk.xwidget.management.AnnotationsHandler;
import org.aguntuk.xwidget.management.RequestProcessor;
import org.aguntuk.xwidget.template.PluginInitializer;
import org.aguntuk.xwidget.template.TemplatePlugin;
import org.aguntuk.xwidget.template.TemplatePluginFactory;
public class FrontController extends HttpServlet {
	
	private TemplatePluginFactory<String> templateFactory;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2599695236613573479L;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(ServletConfig config) {
		try {
		    super.init(config);
		    String targetPkg = config.getInitParameter("servicecodebase");
		    String serviceName = config.getServletName();
		    if(targetPkg == null || targetPkg.length() == 0) {
		    	out.println("FrontController:: init. ERROR No target package specified. BlockUI will not run services.");
		    }
		    AnnotationsHandler.INSTANCE.scanClassPathForRequestInterfaces(targetPkg, serviceName);
		    
		    String templatePluginFactoryClassName = config.getInitParameter("templatePluginFactoryClassName")!=null
		    ?config.getInitParameter("templatePluginFactoryClassName"):"org.aguntuk.xwidget.template.freemarker.FreemarkerTemplatePluginFactory";
		    //instantiate the template factory
		    templateFactory = (TemplatePluginFactory<String>)Class.forName(templatePluginFactoryClassName).newInstance();
		    PluginInitializer initializer = templateFactory.getPluginInitializer();
		    initializer.setServletConfig(config);

		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			TemplatePlugin template = templateFactory.getTempatePluginInstance();			
			Object obj = RequestProcessor.INSTANCE.processRequest(request, response, template, this.getServletName());
			PrintWriter pw = response.getWriter();			
			if(obj != null) {
				pw.println(obj);				
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

}
