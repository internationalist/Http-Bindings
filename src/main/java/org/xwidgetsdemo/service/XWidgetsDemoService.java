package org.xwidgetsdemo.service;

import org.aguntuk.xwidget.annotations.RequestMeta;
import org.aguntuk.xwidget.annotations.ServiceInterfaceMeta;
import org.aguntuk.xwidget.util.OutputFormat;

@ServiceInterfaceMeta
public class XWidgetsDemoService {
	@RequestMeta(requestName="navbar", outputType=OutputFormat.JSON)
	public String[] getNavBar() {
		String[] navItems =  {"Politics", "Games", "Cars"};
		return navItems;
	}
	@RequestMeta(requestName="textpage", outputType=OutputFormat.JSON, requestKeyName={"pageNumber"})
	public PagerObject getPageText(int pageNumber) {
		PagerObject po = new PagerObject();
		DummyStaticText dst = new DummyStaticText();
		po.setCurrentPage(pageNumber);
		po.setNumPages(dst.getStore().size());
		po.setPageText(dst.getStore().get(String.valueOf(pageNumber)));
		return po;
	}
}
