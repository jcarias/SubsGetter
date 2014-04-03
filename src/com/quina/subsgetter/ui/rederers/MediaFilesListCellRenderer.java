package com.quina.subsgetter.ui.rederers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import com.quina.subsgetter.model.MediaFile;

public class MediaFilesListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 8471926415381751507L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		if (value instanceof MediaFile) {
			MediaFile item = (MediaFile)value;
			setText(item.getFile().getName());
			setToolTipText(item.getFile().getPath());
			setIcon(new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/film-medium.png")));
		}
		
		return this;
	}

}
