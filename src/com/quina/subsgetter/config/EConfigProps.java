package com.quina.subsgetter.config;

public enum EConfigProps {

	USE_PROXY("useProxy"),
	PROXY_ADDRESS("proxyAddress"),
	PROXY_PORT("proxyPort"),
	OS_USERNAME("osUsername"),
	OS_PASSWORD("osPassword"),
	LANGAGE("language"),
	SUBTITLES_FILENAME("filenameType"), 
	DOWNLOAD_DESTINATION_TYPE("dlDestType"),
	DOWNLOAD_DESTINATION_FOLDER("dlDestFolder");

	private String property;

	EConfigProps(String value) {
		this.setProperty(value);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String language) {
		this.property = language;
	}

}
