package org.aguntuk.xwidget.template.freemarker;

import java.io.IOException;

import org.aguntuk.xwidget.template.PluginInitializer;
import org.aguntuk.xwidget.template.TemplatePlugin;
import org.aguntuk.xwidget.template.TemplatePluginFactory;

public class FreemarkerTemplatePluginFactory extends TemplatePluginFactory<String> {

	@Override
	public TemplatePlugin getTempatePluginInstance() throws IOException {
		return FreeMarkerTemplatePlugin.getInstance();
	}

	@Override
	public PluginInitializer getPluginInitializer() {
		return FreemarkerPluginInitializer.getInstance();
	}

}
