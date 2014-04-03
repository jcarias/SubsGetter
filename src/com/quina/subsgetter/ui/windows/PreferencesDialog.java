package com.quina.subsgetter.ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.googlecode.opensubtitlesjapi.LANGUAGE;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.quina.resources.components.panels.GradientPanel;
import com.quina.subsgetter.config.Configuration;
import com.quina.subsgetter.config.EConfigProps;
import com.quina.subsgetter.ui.rederers.LanguagesListCellRenderer;

public class PreferencesDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4036302916716792647L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtProxyAddress;
	private JTextField txtProxyPort;
	private JTextField txtOsUsername;
	private JTextField txtOsPassword;
	private JTextField textField;
	private JCheckBox chckbxUseHttpProxy;
	private JComboBox comboBoxLanguage;

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	public PreferencesDialog() throws Exception {
		setResizable(false);
		initialize();
		Configuration configuration = Configuration.getInstance();
		boolean useProxy = new Boolean(configuration.getProperty(EConfigProps.USE_PROXY.getProperty(), "false"));
		String proxyAddress = configuration.getProperty(EConfigProps.PROXY_ADDRESS.getProperty(), "");
		String proxyPort = configuration.getProperty(EConfigProps.PROXY_PORT.getProperty(), "");
		String osUsername = configuration.getProperty(EConfigProps.OS_USERNAME.getProperty(), "");
		String osPassword = configuration.getProperty(EConfigProps.OS_PASSWORD.getProperty(), "");
		String language = configuration.getProperty(EConfigProps.LANGAGE.getProperty(), "");
		String fileNameType = configuration.getProperty(EConfigProps.SUBTITLES_FILENAME.getProperty(), "1");
		String downloadDestinationType = configuration.getProperty(EConfigProps.DOWNLOAD_DESTINATION_TYPE.getProperty(), "1");
		String sysPropUserHome = System.getProperty("user.home");
		String downloadDestinationFolder = configuration.getProperty(EConfigProps.DOWNLOAD_DESTINATION_FOLDER.getProperty(), "sysPropUserHome");

		getChckbxUseHttpProxy().setSelected(useProxy);
		getTxtProxyAddress().setText(proxyAddress);
		getTxtProxyPort().setText(proxyPort);
		getTxtOsUsername().setText(osUsername);
		getTxtOsPassword().setText(osPassword);
		getComboBoxLanguage().setSelectedItem(language);

	}

	/**
	 * 
	 */
	private void initialize() {
		setTitle("Preferences");
		setIconImage(Toolkit.getDefaultToolkit().getImage(PreferencesDialog.class.getResource("/com/quina/subsgetter/ui/images/icons16/equalizer.png")));
		setBounds(100, 100, 717, 570);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.PARAGRAPH_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.PARAGRAPH_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.UNRELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, FormFactory.NARROW_LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblConnectionSettings = new JLabel("Connection settings");
		lblConnectionSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPanel.add(lblConnectionSettings, "2, 2, 3, 1");

		JSeparator separator = new JSeparator();
		contentPanel.add(separator, "2, 4, 3, 1");

		JLabel lblUseHttpProxy = new JLabel("Use HTTP proxy:");
		contentPanel.add(lblUseHttpProxy, "2, 6, right, default");

		chckbxUseHttpProxy = new JCheckBox("");
		chckbxUseHttpProxy.setIcon(null);
		contentPanel.add(chckbxUseHttpProxy, "4, 6, left, default");

		JLabel lblProxyAddress = new JLabel("Proxy address:");
		contentPanel.add(lblProxyAddress, "2, 8, right, default");

		txtProxyAddress = new JTextField();
		txtProxyAddress.setText("proxy address");
		contentPanel.add(txtProxyAddress, "4, 8, fill, default");
		txtProxyAddress.setColumns(10);

		JLabel lblProxyPort = new JLabel("Proxy port:");
		contentPanel.add(lblProxyPort, "2, 10, right, default");

		txtProxyPort = new JTextField();
		txtProxyPort.setText("proxy port");
		contentPanel.add(txtProxyPort, "4, 10, left, default");
		txtProxyPort.setColumns(10);

		JLabel lblOpensubtitlesLogin = new JLabel("Opensubtitles login");
		lblOpensubtitlesLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPanel.add(lblOpensubtitlesLogin, "2, 12, 3, 1");

		JSeparator separator_1 = new JSeparator();
		contentPanel.add(separator_1, "2, 14, 3, 1");

		JLabel lblUsername = new JLabel("username:");
		contentPanel.add(lblUsername, "2, 16, right, default");

		txtOsUsername = new JTextField();
		txtOsUsername.setText("os username");
		contentPanel.add(txtOsUsername, "4, 16, left, default");
		txtOsUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		contentPanel.add(lblPassword, "2, 18, right, default");

		txtOsPassword = new JTextField();
		txtOsPassword.setText("os password");
		contentPanel.add(txtOsPassword, "4, 18, left, default");
		txtOsPassword.setColumns(10);

		JLabel lblSubtitleSettings = new JLabel("Subtitle settings");
		lblSubtitleSettings.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPanel.add(lblSubtitleSettings, "2, 20, 3, 1");

		JSeparator separator_2 = new JSeparator();
		contentPanel.add(separator_2, "2, 22, 3, 1");

		JLabel lblSelectedLanguage = new JLabel("Selected language:");
		contentPanel.add(lblSelectedLanguage, "2, 24, right, default");

		comboBoxLanguage = new JComboBox();
		comboBoxLanguage.setBorder(new EmptyBorder(1, 2, 1, 2));
		comboBoxLanguage.setModel(new DefaultComboBoxModel(LANGUAGE.values()));
		comboBoxLanguage.setRenderer(new LanguagesListCellRenderer());
		contentPanel.add(comboBoxLanguage, "4, 24, fill, default");

		JLabel lblSubtitlesFilename = new JLabel("Subtitles filename:");
		contentPanel.add(lblSubtitlesFilename, "2, 26, right, default");

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEADING);
		contentPanel.add(panel, "4, 26, fill, fill");

		JRadioButton rdbtnMatchLocalVideo = new JRadioButton("match local video file name");
		rdbtnMatchLocalVideo.setSelected(true);
		panel.add(rdbtnMatchLocalVideo);

		JRadioButton rdbtnMatchLocalVideo_1 = new JRadioButton("match local + lang. code");
		panel.add(rdbtnMatchLocalVideo_1);

		JRadioButton rdbtnKeepOriginalFile = new JRadioButton("Keep original file name");
		panel.add(rdbtnKeepOriginalFile);

		ButtonGroup groupSubtitlesFileName = new ButtonGroup();
		groupSubtitlesFileName.add(rdbtnMatchLocalVideo);
		groupSubtitlesFileName.add(rdbtnMatchLocalVideo_1);
		groupSubtitlesFileName.add(rdbtnKeepOriginalFile);

		JLabel lblDownloadDestination = new JLabel("Download destination:");
		contentPanel.add(lblDownloadDestination, "2, 28");

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		flowLayout_1.setVgap(0);
		contentPanel.add(panel_1, "4, 28, fill, fill");

		JRadioButton rdbtnSameLocationAs = new JRadioButton("Same location as video file");
		rdbtnSameLocationAs.setSelected(true);
		panel_1.add(rdbtnSameLocationAs);

		JRadioButton rdbtnOtherLocation = new JRadioButton("Other location...");
		panel_1.add(rdbtnOtherLocation);

		JPanel panel_2 = new JPanel();
		contentPanel.add(panel_2, "4, 30, fill, fill");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnBrowse = new JButton("Browse...");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("471px:grow"), FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("right:79px"), }, new RowSpec[] { RowSpec.decode("23px"), }));
		panel_2.add(textField, "1, 1, fill, center");
		panel_2.add(btnBrowse, "3, 1, left, top");
		{
			JPanel buttonPane = new GradientPanel(GradientPanel.VERTICAL);
			buttonPane.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(192, 192, 192)), new MatteBorder(1, 0, 0, 0, (Color) new Color(255, 255, 255))));
			buttonPane.setBackground(new Color(220, 220, 220));
			buttonPane.setForeground(new Color(0xf0f0f0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							savePreferences();
							setVisible(false);
							dispose();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error saving preferences", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setOpaque(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setOpaque(false);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void savePreferences() throws Exception {
		Configuration configuration = Configuration.getInstance();
		configuration.setProperty(EConfigProps.USE_PROXY.getProperty(), getChckbxUseHttpProxy().isSelected() ? "true" : "false");
		configuration.setProperty(EConfigProps.PROXY_ADDRESS.getProperty(), getTxtProxyAddress().getText());
		configuration.setProperty(EConfigProps.PROXY_PORT.getProperty(), getTxtProxyPort().getText());
		configuration.setProperty(EConfigProps.OS_USERNAME.getProperty(), getTxtOsUsername().getText());
		configuration.setProperty(EConfigProps.OS_PASSWORD.getProperty(), getTxtOsPassword().getText());
		
		if (getComboBoxLanguage().getSelectedItem() instanceof LANGUAGE) {
			LANGUAGE selLang = (LANGUAGE) getComboBoxLanguage().getSelectedItem();
			configuration.setProperty(EConfigProps.LANGAGE.getProperty(), selLang.getLanguage());
		}
		
		// String fileNameType =
		// configuration.getProperty(EConfigProps.SUBTITLES_FILENAME.getProperty(),
		// "1");
		// String downloadDestinationType =
		// configuration.getProperty(EConfigProps.DOWNLOAD_DESTINATION_TYPE.getProperty(),
		// "1");
		// String sysPropUserHome = System.getProperty("user.home");
		// String downloadDestinationFolder =
		// configuration.getProperty(EConfigProps.DOWNLOAD_DESTINATION_FOLDER.getProperty(),
		// "sysPropUserHome");
		
		configuration.save();

	}

	public JTextField getTxtOsUsername() {
		return txtOsUsername;
	}

	public JTextField getTxtProxyAddress() {
		return txtProxyAddress;
	}

	public JTextField getTxtProxyPort() {
		return txtProxyPort;
	}

	public JCheckBox getChckbxUseHttpProxy() {
		return chckbxUseHttpProxy;
	}

	public JTextField getTxtOsPassword() {
		return txtOsPassword;
	}

	public JComboBox getComboBoxLanguage() {
		return comboBoxLanguage;
	}
}
