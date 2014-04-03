package com.quina.subsgetter.ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.googlecode.opensubtitlesjapi.LANGUAGE;
import com.googlecode.opensubtitlesjapi.OpenSubtitlesAPI;
import com.googlecode.opensubtitlesjapi.OpenSubtitlesException;
import com.quina.resources.components.buttons.DropDownButton;
import com.quina.resources.components.panels.GradientPanel;
import com.quina.resources.tools.FileTools;
import com.quina.subsgetter.config.Configuration;
import com.quina.subsgetter.config.EConfigProps;
import com.quina.subsgetter.model.MediaFile;
import com.quina.subsgetter.ui.rederers.MediaFilesListCellRenderer;
import com.quina.subsgetter.utils.VideoFileFilter;

public class SubsGetterAppWindow implements ListDataListener {

	private JFrame frmSubsGetter;
	private JButton btnSearchSubtitles;
	private JButton btnRemove;
	private JList filesList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SubsGetterAppWindow window = new SubsGetterAppWindow();
					window.frmSubsGetter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SubsGetterAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSubsGetter = new JFrame();
		frmSubsGetter.setIconImage(Toolkit.getDefaultToolkit().getImage(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/other/OpenSubtitlesFavIcon.gif")));
		frmSubsGetter.setTitle("Subs Getter");
		frmSubsGetter.setBounds(100, 100, 640, 480);
		frmSubsGetter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new GradientPanel(GradientPanel.VERTICAL);
		panel.setBorder(new CompoundBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)), new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192))), new EmptyBorder(5, 5, 5, 5)));
		panel.setBackground(new Color(220, 220, 220));
		panel.setForeground(Color.WHITE);
		frmSubsGetter.getContentPane().add(panel, BorderLayout.NORTH);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOpaque(false);
		panel.add(toolBar, BorderLayout.NORTH);

		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem mntmAddVideoFile = new JMenuItem("Add video file...", new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/document-film.png")));
		mntmAddVideoFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				chooser.setDialogType(JFileChooser.OPEN_DIALOG);
				chooser.setDialogTitle("Choose files...");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setFileFilter(new VideoFileFilter(true));
				chooser.setMultiSelectionEnabled(true);

				if (chooser.showOpenDialog(frmSubsGetter) == JFileChooser.APPROVE_OPTION) {
					// TODO: add files to list
					addFilesToList(chooser.getSelectedFiles());
				}
			}
		});
		popupMenu.add(mntmAddVideoFile);

		JMenuItem mntmAddFolder = new JMenuItem("Add folder...", new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/folder-open-film.png")));
		mntmAddFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Choose folder...");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setMultiSelectionEnabled(false);
				if (chooser.showOpenDialog(frmSubsGetter) == JFileChooser.APPROVE_OPTION) {
					File[] foundFiles = getVideoFilesFromFolder(chooser.getSelectedFile());
					addFilesToList(foundFiles);
				}
			}
		});
		popupMenu.add(mntmAddFolder);

		DropDownButton ddButton = new DropDownButton("Add");
		ddButton.setText("Add files...");
		toolBar.add(ddButton);
		ddButton.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/film--plus.png")));
		ddButton.setMenu(popupMenu);

		btnRemove = new JButton("Remove");
		btnRemove.setOpaque(false);
		toolBar.add(btnRemove);
		btnRemove.setEnabled(false);
		btnRemove.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/film--minus.png")));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (getFilesList().getSelectedValues().length > 0) {
					if (JOptionPane.showConfirmDialog(frmSubsGetter, "Are you sure you want to remove the selected file(s)?", "Remove files?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						for (Object obj : getFilesList().getSelectedValues()) {
							if (obj instanceof MediaFile) {
								DefaultListModel model = (DefaultListModel) getFilesList().getModel();
								model.removeElement(obj);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(frmSubsGetter, "No videos selected.", "No videos selected.", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		toolBar.add(horizontalStrut_1);

		btnSearchSubtitles = new JButton("Search Subtitles");
		btnSearchSubtitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchSubtitles();
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmSubsGetter, e1.getLocalizedMessage(), "Error searching subtitles", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSearchSubtitles.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/balloon-ellipsis.png")));
		btnSearchSubtitles.setEnabled(false);
		btnSearchSubtitles.setOpaque(false);
		toolBar.add(btnSearchSubtitles);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_1);

		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PreferencesDialog dialog = new PreferencesDialog();
					dialog.setModal(true);
					dialog.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmSubsGetter, e.getMessage(), "Error loading preferences", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSettings.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/equalizer.png")));
		btnSettings.setOpaque(false);
		toolBar.add(btnSettings);

		GradientPanel gradientPanel = new GradientPanel(1);
		gradientPanel.setForeground(new Color(245, 245, 245));
		gradientPanel.setBorder(new CompoundBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)), new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 255, 255))), new EmptyBorder(5, 5, 5, 5)));
		gradientPanel.setBackground(new Color(220, 220, 220));
		frmSubsGetter.getContentPane().add(gradientPanel, BorderLayout.SOUTH);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		gradientPanel.add(panel_2, BorderLayout.EAST);

		JButton btnAbout = new JButton("About...");
		btnAbout.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/question-frame.png")));
		btnAbout.setOpaque(false);
		panel_2.add(btnAbout);

		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/door-open-in.png")));
		btnExit.setOpaque(false);
		panel_2.add(btnExit);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBorder(null);
		frmSubsGetter.getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		filesList = new JList();
		filesList.setModel(new DefaultListModel());
		filesList.setCellRenderer(new MediaFilesListCellRenderer());
		filesList.getModel().addListDataListener(this);
		scrollPane.setViewportView(filesList);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		toolBar_1.setOpaque(false);
		panel_1.add(toolBar_1, BorderLayout.NORTH);

		JLabel lblFilesSubtitles = new JLabel("Video files:");
		lblFilesSubtitles.setLabelFor(filesList);
		lblFilesSubtitles.setIcon(new ImageIcon(SubsGetterAppWindow.class.getResource("/com/quina/subsgetter/ui/images/icons16/films.png")));
		toolBar_1.add(lblFilesSubtitles);
		lblFilesSubtitles.setFont(new Font("Tahoma", Font.BOLD, 11));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		splitPane.setRightComponent(scrollPane_1);
	}

	protected void searchSubtitles() throws Exception {
		Proxy proxy = null;
		try {
			proxy = getProxy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		OpenSubtitlesAPI api = new OpenSubtitlesAPI(proxy);
		String propertyOSUser = Configuration.getInstance().getProperty(EConfigProps.OS_USERNAME.getProperty(), "");
		String propertyOSPass = Configuration.getInstance().getProperty(EConfigProps.OS_PASSWORD.getProperty(), "");
		String token = api.login(propertyOSUser, propertyOSPass);
		String propertyLangauge = Configuration.getInstance().getProperty(EConfigProps.LANGAGE.getProperty(), "por");

		DefaultListModel model = (DefaultListModel) getFilesList().getModel();
		for (int i = 0; i < model.size(); i++) {
			Object item = model.get(i);
			if (item instanceof MediaFile) {
				MediaFile file = (MediaFile) item;
				List<Map<String,Object>> search = api.search(token, file.getFile(), LANGUAGE.getByLanguage(propertyLangauge));
				file.setResults(search);
				System.out.println(search);
			}
		}
	
		api.logout(token);
	}

	private Proxy getProxy() throws Exception {
		String propertyUseProxy = Configuration.getInstance().getProperty(EConfigProps.USE_PROXY.getProperty(), "false");
		if (new Boolean(propertyUseProxy)) {
			String propertyProxyAddress = Configuration.getInstance().getProperty(EConfigProps.PROXY_ADDRESS.getProperty(), "");
			String propertyProxyPort = Configuration.getInstance().getProperty(EConfigProps.PROXY_PORT.getProperty(), "0");
			InetSocketAddress inetSocketAddress = new InetSocketAddress(propertyProxyAddress, new Integer(propertyProxyPort));
			Proxy proxy = new Proxy(Proxy.Type.HTTP, inetSocketAddress);

			return proxy;
		} else {
			return null;
		}
	}

	protected void addFilesToList(File[] files) {

		DefaultListModel model = (DefaultListModel) getFilesList().getModel();
		for (File file : files) {
			model.addElement(new MediaFile(file, null));
		}
	}

	protected File[] getVideoFilesFromFolder(File selectedFile) {
		ArrayList<File> listFilesForFolder = FileTools.listFilesForFolder(selectedFile, new VideoFileFilter(false));
		return listFilesForFolder.toArray(new File[] {});
	}

	public JButton getBtnSearchSubtitles() {
		return btnSearchSubtitles;
	}

	public JButton getBtnRemove() {
		return btnRemove;
	}

	public JList getFilesList() {
		return filesList;
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		updateListDependentButtons();
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
		updateListDependentButtons();

	}

	/**
	 * 
	 */
	private void updateListDependentButtons() {
		boolean listHasFiles = listHasMediaFiles();
		getBtnRemove().setEnabled(listHasFiles);
		getBtnSearchSubtitles().setEnabled(listHasFiles);
	}

	private boolean listHasMediaFiles() {
		DefaultListModel model = (DefaultListModel) getFilesList().getModel();
		if (model.getSize() > 0) {
			for (int i = 0; i < model.getSize(); i++) {
				if (model.get(i) instanceof MediaFile) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
		updateListDependentButtons();

	}
}
