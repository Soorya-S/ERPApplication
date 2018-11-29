package Settings;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.print.Paper;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PrinterSettings extends JPanel
{

	static JComboBox comboBox ,printer_combo,orientation_combo,size_combo,copies_combo,print_font_combo ;
	DefaultComboBoxModel printer_name_model;
	public PrinterSettings()
	{
		//SystemProperty.getProperties();
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);
		setSize(379,390);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Printing Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 359, 119);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblPreviewTheDocuments = new JLabel("Preview before Printing");
		lblPreviewTheDocuments.setBounds(10, 27, 142, 14);
		
		
		panel.add(lblPreviewTheDocuments);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Enabled", "Disabled"}));
		comboBox.setBounds(200, 24, 113, 20);
		System.out.println("In Printer setting"+SystemProperty.PRINTER_PREVIEW_ENABLED);
		if(SystemProperty.PRINTER_PREVIEW_ENABLED)
		{
			comboBox.setSelectedIndex(0);
		}
		else
		{
			comboBox.setSelectedIndex(1);
		}
		panel.add(comboBox);
		
		JLabel lblFontSizeTo = new JLabel("Font size to print");
		lblFontSizeTo.setBounds(10, 66, 142, 14);
		panel.add(lblFontSizeTo);
		
		print_font_combo = new JComboBox();
		print_font_combo.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium", "Large"}));
		print_font_combo.setBounds(200, 63, 113, 20);
		panel.add(print_font_combo);
		
		if(SystemProperty.PRINT_FONT_SIZE==19)
			print_font_combo.setSelectedIndex(0);
		if(SystemProperty.PRINT_FONT_SIZE==21)
			print_font_combo.setSelectedIndex(1);
		if(SystemProperty.PRINT_FONT_SIZE==23)
			print_font_combo.setSelectedIndex(2);
	
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Printer Configurations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 141, 359, 197);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSelectPrinter = new JLabel("Select Printer");
		lblSelectPrinter.setBounds(10, 33, 90, 14);
		panel_1.add(lblSelectPrinter);
		
		printer_combo = new JComboBox();
		printer_combo.setBounds(161, 30, 156, 20);
		
		printer_name_model=new DefaultComboBoxModel();
		for (PrintService printer :getAvailablePrinters())
		{
			printer_name_model.addElement(printer.getName().toString());
			System.out.println("Printer: " + printer.getName()); 
		}
		printer_combo.setModel(printer_name_model);
		printer_combo.setSelectedIndex(SystemProperty.PRINTER_NAME);
		panel_1.add(printer_combo);
		
		JLabel lblSelectNumberOf = new JLabel("Select Number of copies");
		lblSelectNumberOf.setBounds(10, 71, 130, 14);
		panel_1.add(lblSelectNumberOf);
		
		copies_combo = new JComboBox();
		copies_combo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		copies_combo.setBounds(161, 68, 156, 20);
		copies_combo.setSelectedIndex(SystemProperty.PRINTER_COPIES-1);
		panel_1.add(copies_combo);
		
		JLabel lblSelectPaperSize = new JLabel("Select Paper Size");
		lblSelectPaperSize.setBounds(10, 109, 130, 14);
		panel_1.add(lblSelectPaperSize);
		
		size_combo = new JComboBox();
		size_combo.setModel(new DefaultComboBoxModel(new String[] {"A3", "A4", "A5"}));
		size_combo.setBounds(161, 106, 156, 20);
			if(SystemProperty.PRINTER_PAPER_SIZE.equals("A5"))
			{
				size_combo.setSelectedIndex(2);
			}
			else if(SystemProperty.PRINTER_PAPER_SIZE.equals("A3"))
			{
				size_combo.setSelectedIndex(0);
			}
			else
			{
				size_combo.setSelectedIndex(1);
			}
		panel_1.add(size_combo);
		
		JLabel lblSelectOri = new JLabel("Select Orientation");
		lblSelectOri.setBounds(10, 151, 130, 14);
		panel_1.add(lblSelectOri);
		
		orientation_combo = new JComboBox();
		orientation_combo.setModel(new DefaultComboBoxModel(new String[] {"Landscape", "Portrait"}));
		orientation_combo.setBounds(161, 148, 156, 20);
		if(SystemProperty.PRINTER_ORIENTATION.equals("Landscape"))
		{
			orientation_combo.setSelectedIndex(0);
		}
		else
		{
			orientation_combo.setSelectedIndex(1);
		}
		panel_1.add(orientation_combo);
		
	}
	
	public static PrintService[] getAvailablePrinters()
	{
		  PrintRequestAttributeSet pras=null;
		  PrintService pss[];
	      pss= PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras);
	      if (pss.length == 0)
			  {
	    	  	final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
	    	  	JOptionPane.showMessageDialog(null, "No printer Found!","Error!",JOptionPane.ERROR_MESSAGE);
	    	  }
	    
	    
	    return pss;
	    
	}
	
	public static void saveMyProperties()
	{
		if(comboBox.getSelectedIndex()==0)
		{
			SystemProperty.PRINTER_PREVIEW_ENABLED=true;
		}
		else
		{
			SystemProperty.PRINTER_PREVIEW_ENABLED=false;
		}
		
		SystemProperty.PRINTER_NAME=printer_combo.getSelectedIndex();
		SystemProperty.PRINTER_COPIES=Integer.parseInt(copies_combo.getSelectedItem().toString());
		SystemProperty.PRINTER_ORIENTATION=orientation_combo.getSelectedItem().toString();
		SystemProperty.PRINTER_PAPER_SIZE=size_combo.getSelectedItem().toString();
		
		if(print_font_combo.getSelectedIndex()==0)
			SystemProperty.PRINT_FONT_SIZE=19;
		if(print_font_combo.getSelectedIndex()==1)
			SystemProperty.PRINT_FONT_SIZE=21;
		if(print_font_combo.getSelectedIndex()==2)
			SystemProperty.PRINT_FONT_SIZE=23;
		
		
		
	}
}
