package org.aguntuk.xwidget.management;


public class RequestKey {
	private String keyName;
	private String keyType;
	
	public RequestKey(String keyName, String keyType) {
		this.keyName=keyName;
		this.keyType=keyType;
	}
	public String getKeyName() {
		return keyName;
	}
	public String getKeyType() {
		return keyType;
	}

}
