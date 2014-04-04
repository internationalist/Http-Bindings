package org.aguntuk.xwidget.template;

import java.io.IOException;

public abstract class TemplatePluginFactory<T> {
	public abstract TemplatePlugin getTempatePluginInstance() throws IOException;
	public abstract PluginInitializer getPluginInitializer();
}
