package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;

import javax.swing.border.TitledBorder;



















import Database.AddLBDB;
import Database.AddVoucherDB;
import Database.LookUpDB;
import Printer.PrintDeliveryNote;
import Printer.PrintLabourBill;
import Printer.PrintTaxInvoice;
import Printer.PrintVoucher;
import Settings.SettingsDialog;
import Settings.SystemProperty;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.SpringLayout;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class MainFrame extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JPanel panel;
	JButton daily_sheet_button,search_button,add_button;
	private JTextField textField;
	JMenuItem mnuExit,mntmSettings,mntmGenerateReport,mntmAbout,mntmExport,mntmImport,help;
	public static MainFrame frame=null;
	public JFrame f_frame=null;
	JComboBox comboBox;
	LabourBill labourBill ;
	DeliveryNote deliveryNote;
	Voucher voucher;
	TaxInvoice  tax_invoice=null;
	
	
	public static JFrame MAIN_FRAME;
	public MainFrame() 
	{
		setAutoRequestFocus(false);
		f_frame=this;
		MAIN_FRAME=this;
		try
		{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		SwingUtilities.updateComponentTreeUI(frame);
		}catch(Exception e){}
		
		SystemProperty.getProperties();
		
		setTitle("Teem Tex");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10,10,1366,744);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmImport = new JMenuItem("Import..");
		mnFile.add(mntmImport);
		
		mntmExport = new JMenuItem("Export..");
		mnFile.add(mntmExport);
		
		mnuExit = new JMenuItem("Exit");
		mnFile.add(mnuExit);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		mntmSettings = new JMenuItem("Settings");
		mntmSettings.setMnemonic('s');
		mnOptions.add(mntmSettings);
		
		mntmGenerateReport = new JMenuItem("Generate Report");
		mntmGenerateReport.setMnemonic('R');
		mnOptions.add(mntmGenerateReport);
		
		JMenu mnMore = new JMenu("more");
		menuBar.add(mnMore);
		
		mntmAbout = new JMenuItem("About");
		mnMore.add(mntmAbout);
		
		help=new JMenuItem("Help");
		mnMore.add(mntmAbout);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1366, 120);
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Resources/images/title.png"));
		label.setBounds(308, 0, 751, 118);
		panel_1.add(label);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(260, 119, 1106, 553);
		
		labourBill = new LabourBill();
		deliveryNote = new DeliveryNote();
		voucher = new Voucher();
		Cheque c=new Cheque();
		tax_invoice=new TaxInvoice();
		
		//Thread t=new Thread();
		//tax_invoice=new TaxInvoice();
		tabbedPane.add(labourBill);
		tabbedPane.add( tax_invoice);
		tabbedPane.add(deliveryNote);
		tabbedPane.add(voucher);
		tabbedPane.add(c);
		
			
		getContentPane().add(tabbedPane);
		
		JLabel l1,l2,l3,l4,l5;
		l1=new JLabel("      Labour Bill      ");
		l2=new JLabel("      DeliverNote      ");
		l3=new JLabel("        Voucher        ");
		l4=new JLabel("        Cheque         ");
		l5=new JLabel("      Tax Invoice      ");
		
		tabbedPane.setTabComponentAt(0, l1);
		tabbedPane.setTabComponentAt(1, l5);
		tabbedPane.setTabComponentAt(2, l2);
		tabbedPane.setTabComponentAt(3, l3);
		tabbedPane.setTabComponentAt(4, l4);
		
		
		l1.setFont(new Font("Times New Roman",Font.BOLD,15));
		l2.setFont(new Font("Times New Roman",Font.BOLD,15));
		l3.setFont(new Font("Times New Roman",Font.BOLD,15));
		l4.setFont(new Font("Times New Roman",Font.BOLD,15));
		l5.setFont(new Font("Times New Roman",Font.BOLD,15));
		
		l1.setForeground(new Color(45,61,138));
		l2.setForeground(new Color(45,61,138));
		l3.setForeground(new Color(45,61,138));
		l4.setForeground(new Color(45,61,138));
		l5.setForeground(new Color(45,61,138));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Functions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 131, 240, 543);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		daily_sheet_button = new JButton("View Accounts");
		daily_sheet_button.setBounds(59, 42, 120, 40);
		daily_sheet_button.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_A, KeyEvent.CTRL_MASK ), JComponent.WHEN_FOCUSED );
		panel_2.add(daily_sheet_button);
		
		JPanel panel_3 = new JPanel();
		panel_3.setToolTipText("Quick search");
		panel_3.setBorder(new TitledBorder(null, "Quick Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 93, 220, 240);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblEnter = new JLabel("Enter Number ");
		lblEnter.setBounds(10, 31, 86, 14);
		panel_3.add(lblEnter);
		
		textField = new JTextField();
		textField.setBounds(106, 28, 104, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Look up in");
		lblNewLabel.setBounds(10, 84, 86, 14);
		panel_3.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(106, 81, 104, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Labour Bill", "Delivery Note", "Voucher","Tax Invoice"}));
		panel_3.add(comboBox);
		
		search_button = new JButton("");
		search_button.setBounds(85, 125, 47, 47);
		search_button.setToolTipText("Click to search..");
		search_button.setIcon(new ImageIcon("src/Resources/images/search_icon.png"));
		search_button.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_F, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		search_button.addActionListener(this);
		panel_3.add(search_button);
		pack();
		
		
		/*_____________________________________Event Listeners_____________________________________
		*/
		
		daily_sheet_button.addActionListener(this);
		mnuExit.addActionListener(this);
		mntmSettings.addActionListener(this);
		mntmGenerateReport.addActionListener(this);
		mntmAbout.addActionListener(this);
		this.setVisible(true);
		this.toFront();
		this.requestFocus();
		LabourBill.name_field.requestFocusInWindow();
		}
	
	
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==daily_sheet_button)
		{
			DailySheet d=new DailySheet();
		}

		else if(e.getSource()==mnuExit)
		{
			System.exit(0);
		}
		else if(e.getSource()==mntmSettings)
		{
			SettingsDialog dialog = new SettingsDialog();
		}
		else if(e.getSource()==mntmGenerateReport)
		{
			GenerateReport g=new GenerateReport();
		}
		else if(e.getSource()==mntmAbout)
		{
			new About();
			
		}
		else if(e.getSource()==search_button)
		{
			
			String t = textField.getText();
			Object co=comboBox.getSelectedItem();
			
			try
			{
			int x=Integer.parseInt(t);
			LookUpDB lb=new LookUpDB(t, co, comboBox.getSelectedIndex());
		
			
			if(comboBox.getSelectedIndex()==0)
				PrintLabourBill.LOOK_UP=1;
			else if(comboBox.getSelectedIndex()==1)
				PrintDeliveryNote.LOOK_UP=1;
			else if(comboBox.getSelectedIndex()==2)
				PrintVoucher.LOOK_UP=1;
			else if(comboBox.getSelectedIndex()==3)
				PrintTaxInvoice.LOOK_UP=1;
			
			}
			catch(NumberFormatException exe)
			{
				SearchResult sr=new SearchResult();
				sr.searchByName(t,comboBox.getSelectedIndex());
				
				if(comboBox.getSelectedIndex()==0)
					PrintLabourBill.LOOK_UP=1;
				else if(comboBox.getSelectedIndex()==1)
					PrintDeliveryNote.LOOK_UP=1;
				else if(comboBox.getSelectedIndex()==2)
					PrintVoucher.LOOK_UP=1;
				else if(comboBox.getSelectedIndex()==3)
					PrintTaxInvoice.LOOK_UP=1;
					
				
			}
			
			
		}

	}

	
	
	
	}
