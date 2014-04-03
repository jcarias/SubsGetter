package com.quina.subsgetter.ui.rederers;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.quina.subsgetter.model.MediaFile;
import com.quina.subsgetter.model.OSSerachResult;

public class MoviesTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5420143710854117062L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;

		if (node.getUserObject() instanceof MediaFile) {
			MediaFile item = (MediaFile) node.getUserObject();
			setText(item.getFile().getName());
			setToolTipText(item.getFile().getPath());
			setIcon(new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/film-medium.png")));
		}

		else if (node.getUserObject() instanceof OSSerachResult) {
			OSSerachResult item = (OSSerachResult) node.getUserObject();
			setText(item.getSubFileName());
			if (getClass().getResource("/com/quina/subsgetter/ui/images/icons16/flags/" + item.getiSO639() + ".png") != null) {
				setIcon(new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/flags/" + item.getiSO639() + ".png")));
			} else {
				setIcon(new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/flags/_unknown.png")));
			}

		} else {
			setIcon(new ImageIcon(getClass().getResource("/com/quina/subsgetter/ui/images/icons16/films.png")));
		}

		return this;
	}

}
