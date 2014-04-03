package com.quina.subsgetter.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.quina.resources.tools.FileTools;

public class VideoFileFilter extends FileFilter implements java.io.FileFilter{

	private static final String[] VIDEO_EXTENSIONS = new String[] { "3g2", "3gp", "3gp2", "3gpp", "60d", "ajp", "asf", "asx", "avchd", "avi", "bik", "bix", "box", "cam", "dat", "divx", "dmf", "dv", "dvr-ms", "evo", "flc", "fli", "flic", "flv", "flx", "gvi", "gvp", "h264", "m1v", "m2p", "m2ts", "m2v", "m4e", "m4v", "mjp", "mjpeg", "mjpg", "mkv", "moov", "mov", "movhd", "movie", "movx", "mp4", "mpe", "mpeg", "mpg", "mpv", "mpv2", "mxf", "nsv", "nut", "ogg", "ogm", "omf", "ps", "qt", "ram", "rm", "rmvb", "swf", "ts", "vfw", "vid", "video", "viv", "vivo", "vob", "vro", "wm", "wmv", "wmx", "wrap", "wvx", "wx", "x264", "xvid" };
	
	private boolean acceptDirectories;
	
	

	public VideoFileFilter(boolean acceptDirectories) {
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
			for (String ext : VIDEO_EXTENSIONS) {
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
