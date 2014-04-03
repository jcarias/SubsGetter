package com.quina.subsgetter.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.quina.resources.tools.FileTools;

public class OSSubtitlesFileFilter extends FileFilter {

	private static final String[] ALLOWED_EXTENSIONS = new String[] { "*.srt", "*.sub", "*.smi", "*.txt", "*.ssa", "*.ass", "*.mpl" };

	private boolean acceptDirectories;

	public OSSubtitlesFileFilter(boolean acceptDirectories) {
		super();
		this.acceptDirectories = acceptDirectories;
	}

	@Override
	public boolean accept(File file) {

		if (this.acceptDirectories && file.isDirectory()) {
			return true;
		}

		String fileExtension = FileTools.getExtension(file);

		if (fileExtension != null && !fileExtension.isEmpty()) {
			for (String ext : ALLOWED_EXTENSIONS) {
				if (ext.equalsIgnoreCase(fileExtension)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public String getDescription() {
		return "All Supported Vided files";
	}

}
