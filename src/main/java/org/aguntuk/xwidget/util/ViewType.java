package org.aguntuk.xwidget.util;

public enum ViewType {
	LIST;

	public static ViewType toViewType(String type) {
		return ViewType.valueOf(type.toUpperCase());
	}
}
