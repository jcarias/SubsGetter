package com.quina.subsgetter.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MediaFile {
	private File file;
	private OSSerachResult[] results;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public OSSerachResult[] getResults() {
		return results;
	}

	public void setResults(OSSerachResult[] results) {
		this.results = results;
	}

	public MediaFile(File file, OSSerachResult[] results) {
		super();
		this.file = file;
		this.results = results;
	}

	public void setResults(List<Map<String, Object>> searchResults) {
		ArrayList<OSSerachResult> list = new ArrayList<OSSerachResult>();
		for (Map<String, Object> map : searchResults) {
			list.add(new OSSerachResult(map));

		}

		this.results = list.toArray(new OSSerachResult[] {});
	}

}
