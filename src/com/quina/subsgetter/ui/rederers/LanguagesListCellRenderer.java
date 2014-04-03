package com.quina.subsgetter.ui.rederers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import com.googlecode.opensubtitlesjapi.LANGUAGE;

public class LanguagesListCellRenderer extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6823018476352252406L;

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (value instanceof LANGUAGE) {
			LANGUAGE language = (LANGUAGE) value;
			setText(language.getDescription());
			ImageIcon icon = getIconFlag(language.getCodeISO639());
			setIcon(icon);
		}

		return this;
	}

	private ImageIcon getIconFlag(String codeISO) {
		if (getClass().getResource("/com/quina/subsgetter/ui/images/icons16/flags/" + codeISO + ".png") != null) {
			return new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/flags/" + codeISO + ".png"));
		} else {
			return new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/flags/_unknown.png"));
		}
	}

}
