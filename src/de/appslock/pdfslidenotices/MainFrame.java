package de.appslock.pdfslidenotices;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.itextpdf.text.DocumentException;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 6678678963124900806L;
	private PdfSlideNotices pdfCommentBoxer;
	private File[] files;
	private ResourceBundle messages;
	private Locale currentLocale;
	private JButton choose_files_button;
	private JButton choose_target_button;
	private JButton submit_button;
	private JButton clear_selection_button;
	private JComboBox<String> orientation_selection;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JMenu jMenu1;
	private JMenu jMenu2;
	private JMenuBar jMenuBar1;
	private JMenuItem imprint_menu;
	private JMenuItem close_menu;
	private JMenuItem german_menu;
	private JMenuItem english_menu;
	private JScrollPane jScrollPane2;
	private JTextArea choosen_files_textarea;

	public MainFrame() {
		Locale l = Locale.getDefault();
		this.currentLocale = new Locale(l.getLanguage(), l.getCountry());
		this.messages = ResourceBundle.getBundle("MessagesBundle",
				this.currentLocale);
		initComponents();
		this.pdfCommentBoxer = new PdfSlideNotices();
		this.files = new File[0];
	}

	private void initComponents() {
		inititializeComponents();
		initActionListeners();
		setProperties();
		initMenu();
		setTexts();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														this.choose_target_button)
												.addComponent(
														this.jScrollPane2, -1,
														324, 32767)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.choose_files_button)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		-1,
																		32767)
																.addComponent(
																		this.clear_selection_button))
												.addComponent(this.jLabel2, -1,
														-1, 32767)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.jLabel1,
																		-2,
																		101, -2)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						this.submit_button)
																				.addComponent(
																						this.orientation_selection,
																						-2,
																						-1,
																						-2))))
								.addContainerGap(-1, 32767)));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(8, 8, 8)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														this.choose_files_button)
												.addComponent(
														this.clear_selection_button))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jScrollPane2, -2, 132, -2)
								.addGap(18, 18, 18)
								.addComponent(this.choose_target_button)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jLabel2, -2, 18, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														this.orientation_selection,
														-2, -1, -2)
												.addComponent(this.jLabel1))
								.addGap(30, 30, 30)
								.addComponent(this.submit_button)));

		pack();
		setDefaultCloseOperation(3);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("PDF Slide Notices");
	}

	private void setProperties() {
		this.jLabel2.setMinimumSize(new Dimension(0, 13));
		this.submit_button.setMargin(new Insets(5, 10, 5, 10));
		this.choosen_files_textarea.setColumns(20);
		this.choosen_files_textarea.setRows(5);
		this.choosen_files_textarea.setEnabled(false);
		this.jScrollPane2.setViewportView(this.choosen_files_textarea);
	}

	private void inititializeComponents() {
		this.orientation_selection = new JComboBox<String>();
		this.jLabel1 = new JLabel();
		this.choose_files_button = new JButton();
		this.choose_target_button = new JButton();
		this.jLabel2 = new JLabel();
		this.submit_button = new JButton();
		this.jScrollPane2 = new JScrollPane();
		this.choosen_files_textarea = new JTextArea();
		this.clear_selection_button = new JButton();
		this.jMenuBar1 = new JMenuBar();
		this.jMenu1 = new JMenu();
		this.imprint_menu = new JMenuItem();
		this.close_menu = new JMenuItem();
		this.jMenu2 = new JMenu();
		this.german_menu = new JMenuItem();
		this.english_menu = new JMenuItem();
	}

	private void initActionListeners() {
		this.orientation_selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.orientation_selectionActionPerformed(evt);
			}
		});
		this.choose_files_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.choose_files_buttonActionPerformed(evt);
			}
		});
		this.choose_target_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.choose_target_buttonActionPerformed(evt);
			}
		});
		this.clear_selection_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.clear_selection_buttonActionPerformed(evt);
			}
		});
		this.submit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.submit_buttonActionPerformed(evt);
			}
		});
	}

	private void initMenu() {
		this.imprint_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.imprint_menuActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.imprint_menu);
		this.close_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.close_menuActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.close_menu);
		this.jMenuBar1.add(this.jMenu1);
		this.german_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.german_menuActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.german_menu);
		this.english_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				MainFrame.this.english_menuActionPerformed(evt);
			}
		});
		this.jMenu2.add(this.english_menu);
		this.jMenuBar1.add(this.jMenu2);
		setJMenuBar(this.jMenuBar1);
	}

	private void clear_selection_buttonActionPerformed(ActionEvent evt) {
		this.files = new File[0];
		this.choosen_files_textarea.setText(null);
	}

	protected void english_menuActionPerformed(ActionEvent evt) {
		this.currentLocale = new Locale("en", "GB");
		this.messages = ResourceBundle.getBundle("MessagesBundle",
				this.currentLocale);
		setTexts();
	}

	protected void german_menuActionPerformed(ActionEvent evt) {
		this.currentLocale = new Locale("de", "DE");
		this.messages = ResourceBundle.getBundle("MessagesBundle",
				this.currentLocale);
		setTexts();
	}

	private void choose_files_buttonActionPerformed(ActionEvent evt) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(0);
		fc.setMultiSelectionEnabled(true);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == 0) {
			this.files = fc.getSelectedFiles();
			for (File file : this.files)
				this.choosen_files_textarea.append(file.getName() + "\r\n");
		}
	}

	private void choose_target_buttonActionPerformed(ActionEvent evt) {
		JFileChooser fc;
		if (this.files.length != 0)
			fc = new JFileChooser(this.files[0].getPath());
		else {
			fc = new JFileChooser();
		}
		fc.setFileSelectionMode(1);
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == 0) {
			String destination = fc.getSelectedFile().getAbsolutePath();
			this.pdfCommentBoxer.setDestination(destination);
			this.jLabel2.setText(destination);
		}
	}

	private void submit_buttonActionPerformed(ActionEvent evt) {
		if ((this.files == null) || (this.files.length == 0)) {
			JOptionPane.showMessageDialog(null,
					messages.getString("no_file_chosen"),
					messages.getString("error"), 2);
			return;
		}
		if (this.pdfCommentBoxer.getDestination() == null) {
			JOptionPane.showMessageDialog(null,
					messages.getString("no_destination_chosen"),
					messages.getString("error"), 2);
			return;
		}
		for (File file : this.files) {
			try {
				this.pdfCommentBoxer.handleOneFile(file);
			} catch (DocumentException | IOException ex) {
				JOptionPane.showMessageDialog(null,
						messages.getString("action_failed"),
						messages.getString("error"), 2);
				return;
			}
		}
		this.jLabel2.setText(null);
		this.choosen_files_textarea.setText(null);
		this.pdfCommentBoxer = new PdfSlideNotices();
		JOptionPane.showMessageDialog(null,
				messages.getString("action_completed"),
				messages.getString("success"), -1);
	}

	private void orientation_selectionActionPerformed(ActionEvent evt) {
		if (this.orientation_selection.getSelectedIndex() == 0)
			this.pdfCommentBoxer.setOrientation("template_landscape.pdf");
		else
			this.pdfCommentBoxer.setOrientation("template_portrait.pdf");
	}

	private void imprint_menuActionPerformed(ActionEvent evt) {
		JOptionPane.showMessageDialog(null, messages.getString("imprint_info"),
				messages.getString("imprint"), -1);
	}

	private void close_menuActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	public static void main(String[] args) {
		try {
			UIManager.LookAndFeelInfo[] arrayOfLookAndFeelInfo;
			int j = (arrayOfLookAndFeelInfo = UIManager
					.getInstalledLookAndFeels()).length;
			for (int i = 0; i < j; i++) {
				UIManager.LookAndFeelInfo info = arrayOfLookAndFeelInfo[i];
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
					ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	private void setTexts() {
		this.jLabel1.setText(this.messages.getString("orientation"));
		this.orientation_selection.setModel(new DefaultComboBoxModel<String>(
				new String[] { this.messages.getString("landscape"),
						this.messages.getString("portrait") }));
		this.jMenu2.setText(this.messages.getString("language"));
		this.german_menu.setText(this.messages.getString("german"));
		this.english_menu.setText(this.messages.getString("english"));
		this.choose_files_button.setText(this.messages
				.getString("choose_files"));
		this.choose_target_button.setText(this.messages
				.getString("choose_target_folder"));
		this.clear_selection_button.setText(this.messages
				.getString("clear_selection"));
		this.jMenu1.setText(this.messages.getString("file"));
		this.imprint_menu.setText(this.messages.getString("imprint"));
		this.close_menu.setText(this.messages.getString("quit"));
		this.submit_button.setText(this.messages.getString("start"));
	}
}