package com.quina.subsgetter.config;

import java.util.*;
import java.io.*;

public class Configuration {
	private static Configuration _instance = null;

	private final String CONFIG_FILE = "config.properties";
	
	private Properties props = null;

	private Configuration() throws Exception {
		this.props = new Properties();
		FileInputStream input = null;
		try {
			createFileIfItDoesNotExists();
			input = new FileInputStream(new File(CONFIG_FILE));
			props.load(input);
		} catch (Exception e) {
			throw e;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void createFileIfItDoesNotExists() throws IOException {
		File file = new File(CONFIG_FILE);
		if(!file.exists()){
			file.createNewFile();
		}		
	}

	public synchronized static Configuration getInstance() throws Exception {
		if (_instance == null)
			_instance = new Configuration();
		return _instance;
	}

	/**
	 * get property value by name
	 * 
	 * @param key
	 *            the property's name
	 * @return the property value if found, <code>null</code> otherwise.
	 */
	public String getProperty(String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = (String) props.get(key);
		}
		return value;
	}

	/**
	 * Sets a Property
	 * 
	 * @param key
	 *            the property's name
	 * @param value
	 *            the property's value
	 */
	public void setProperty(String key, String value) {
		this.props.setProperty(key, value);
	}

	/**
	 * Persists the configuration
	 */
	public void save() {
		OutputStream output = null;

		try {

			output = new FileOutputStream(CONFIG_FILE);
			this.props.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}