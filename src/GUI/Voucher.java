package GUI;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextField;

import Database.DataBase;
import Printer.NumberToWord;
import Printer.PreviewVoucher;
import Printer.PrintVoucher;
import Settings.SystemProperty;


@SuppressWarnings("serial")
public class Voucher extends JPanel implements ActionListener,TableModelListener,ListSelectionListener,FocusListener
{
	static JTable table;
	DefaultTableModel dtm;
	static JTextField name_field;
	static JTextField total1;
	public static int ROWS=10;
	static JButton btnClear,btnStore,btnPrint;
	public static JLabel num,date;
	public static Boolean AUTO;
	static  DefaultTableModel tmp_dtm;
	private static JTable tmp;
	int LENGTH=40;
	private static final String COMMIT_ACTION = "commit";
	
	
	public Voucher()
	{
		SystemProperty.getProperties();
		AUTO=SystemProperty.VOUCHER_SL_NO_AUTO;
		setSize(1106,566);
		setLayout(null);
		
		Date dNow = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

		date = new JLabel("");
		date.setForeground(Color.BLACK);
		date.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		
		date.setBounds(982, 65, 125, 14);
		date.setText(ft.format(dNow));
		add(date);
		
		num = new JLabel();
		num.setBounds(55, 65, 46, 14);
		num.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		SystemProperty.getNumber();
		num.setText(String.valueOf(SystemProperty.VOUCHER_NO));
		add(num);
	  
		
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon("src/Resources/images/voucher_header.png"));
		label1.setBounds(0,0,1106,137);
		add(label1);
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(0, 117, 1100, 435);
		add(Table_panel);
		
		Object[] title={"S.No","Particulars","Amount Rs."};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(title);
		Table_panel.setLayout(null);
		table = new JTable(dtm);
		
		JTextField textField = new JTextField();
		
		textField.setFont(new Font(date.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		textField.setBorder(new LineBorder(Color.BLACK));
		
		DefaultCellEditor dce = new DefaultCellEditor( textField );
		table.getColumnModel().getColumn(0).setCellEditor(dce);
		table.getColumnModel().getColumn(1).setCellEditor(dce);
		table.getColumnModel().getColumn(2).setCellEditor(dce);
		
		dtm.setRowCount(ROWS);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(1500);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,1100,332);
		
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setFont(new Font(date.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		
		Table_panel.add(scroll);
		
		btnStore = new JButton("Store");
		btnStore.setBounds(350, 359, 89, 28);
		btnStore.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_S, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnStore);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(620, 359, 89, 28);
		btnClear.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_D, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnClear);
		
		
		btnPrint = new JButton("Print");
		btnPrint.setBounds(483, 359, 89, 28);
		btnPrint.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_P, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnPrint);
		
		
		total1 = new JTextField();
		total1.setBounds(944, 338, 155, 25);
		Table_panel.add(total1);
		total1.setFont(new Font(total1.getFont().getName(),Font.PLAIN,14));
		total1.setColumns(10);
		total1.setEditable(false);
		
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()));
		lblTotal.setBounds(900, 343, 46, 14);
		Table_panel.add(lblTotal);
		
		name_field = new JTextField();
		name_field.setBounds(75, 87, 1008, 18);
		
		/*******************Auto complete code*********************/
		name_field.setFocusTraversalKeysEnabled(false);
		Autocomplete.keywords=Autocomplete.getCompanyNames();
		
        

		Autocomplete autoComplete = new Autocomplete(name_field);
		
        name_field.getDocument().addDocumentListener(autoComplete);
        
        name_field.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
        name_field.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
        name_field.addFocusListener(this);
        
        /*******************End of Auto complete code*********************/
		
	

		add(name_field);
		name_field.setColumns(10);
		name_field.setBorder(new EmptyBorder(0,0,0,0));
		Font font1 = new Font(name_field.getFont().getName(),Font.BOLD,SystemProperty.TABLE_FONT_SIZE);
		name_field.setFont(font1);
        
        	
		Border border = BorderFactory.createLineBorder(Color.green); 
		table.getTableHeader().setBackground(new Color(150,255,150));
		JTableHeader hed = table.getTableHeader();
		hed.setBorder(border);
		hed.setFont(new Font("Helvetica",Font.BOLD,14));
		hed.setForeground(new Color(33,50,118));
		hed.setReorderingAllowed(false);
		hed.setResizingAllowed(false);
		hed.setDefaultRenderer(new HeaderRenderer(table));
		
		table.setSelectionForeground(Color.BLACK);
		table.setSelectionBackground(Color.WHITE);
		
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
	    table.getActionMap().put("Enter", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent ae) 
	        {
	        	table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
	        }
	    });
		
		dtm.addTableModelListener(this);
		
		name_field.requestFocus();
		
		
		btnStore.addActionListener(this);
		btnClear.addActionListener(this);
		btnPrint.addActionListener(this);
		
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(this);

	    tmp_dtm=dtm;
	    tmp=table;
	    
	    no_of_copy = new JTextField();
	    new SystemProperty().getProperties();
		no_of_copy.setText(String.valueOf(SystemProperty.PRINTER_COPIES));
	
	    no_of_copy.setColumns(10);
	    no_of_copy.setBounds(574, 359, 18, 28);
	    Table_panel.add(no_of_copy);
	    
	    label = new JLabel("Copy :");
	    label.setBounds(566, 344, 38, 14);
	    Table_panel.add(label);
		
		if(AUTO)
		{
			table.changeSelection(0, 1, false, false);
		}
		else
		{
			table.changeSelection(0, 0, false, false);
		}
	}
	
	
	public static JFrame VOUCHER_FRAME;
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
		if(arg0.getSource()==btnClear)
		{
			clearFields();
		}
		
		else if(arg0.getSource()==btnStore)
		{    	
			table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
			String data2[]=new String[4];
			for (int i = 0; i < getDataRows(); i++)
			{
				for(int j = 0; j < table.getColumnCount(); j++) 
				{
					data[i][j]=dtm.getValueAt(i, j);
				}
			}
			
			data2[0]=num.getText();
			data2[1]=date.getText();
			data2[2]=name_field.getText();
			data2[3]=total1.getText();
    
			
			/******************Validating the data for null or empty- Before store in DB********************/
			
			int e=0;
			for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<3;j++)
				{
					if(j!=1)
					if(data[i][j]==null )
					{
						data[i][j]="";
					}
				}
			}
			
			for(int i=0;i<4;i++)
			{
				if(i!=3)
				if(data2[i].equals("") )
				{
					data2[i]="";
				}
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				try
				{
					if(data[i][1]==null)
						data[i][1]="";
					String qur="INSERT INTO Voucher VALUES('"+data[i][0].toString()+"','"+data2[0]+"','"+data2[2]+"','"+data2[1]+"','"+(data[i][1].toString())+"','"+data[i][2].toString()+"','"+data2[3]+"')";
				}
				catch(Exception d)
				{
					System.out.println(d);
					f++;
				}
			}
			
			System.out.println("e :"+e+"\nf :"+f);
			if(e==0)
			{
				if(f==0)
				{
					DataBase db=new DataBase(data,data2,2);
						if(db.RESULT==1)
						{
							JOptionPane.showMessageDialog(this, "Data was Stored Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
						
								clearFields();	
								SystemProperty.VOUCHER_NO++;
								SystemProperty.setNumber();
								try
								{
									Thread.sleep(1000);
								} catch (InterruptedException w){w.printStackTrace();}
							
								SystemProperty.getNumber();
								num.setText(String.valueOf(SystemProperty.VOUCHER_NO));
							
						}
						else
						{
							final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
							if (runnable != null) runnable.run();
							JOptionPane.showMessageDialog(this, "Oops! Problem in Storing Data. Please Try again", "Message", JOptionPane.ERROR_MESSAGE);
						}
				}
				else
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(null, "Please Enter only valid Data!", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			else
			{
				final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
				
				JOptionPane.showMessageDialog(this, "All Fields are Required!", "Error", JOptionPane.ERROR_MESSAGE);
				e=0;
			}
				
		}
	
	
	
	else if(arg0.getSource()==btnPrint)
	{    	
		table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
		
		Object data[][]=new Object[getDataRows()][table.getColumnCount()];
		String data2[]=new String[6];
		
		for (int i = 0; i < getDataRows(); i++)
		{
			for(int j = 0; j < table.getColumnCount(); j++) 
			{
				data[i][j]=dtm.getValueAt(i, j);
			}
		}
		
		data2[0]=num.getText();
		data2[1]=date.getText();
		data2[2]=name_field.getText();
		data2[3]=total1.getText();
		
		
		
		
		/******************Validating the data for null or empty- Before store in DB********************/
		
		int e=0;
	
		for(int i=0;i<getDataRows();i++)
		{
			for(int j=0;j<3;j++)
			{
				if(j!=1)
				if(data[i][j]==null )
				{
					data[i][j]="";
				}
			}
		}
		
		for(int i=0;i<4;i++)
		{
			if(i!=3)
			if(data2[i].equals("") )
			{
				data2[i]="";
			}
		}
		
		int f=0;
		for(int i=0;i<data.length;i++)
		{
			try
			{
				if(data[i][1]==null)
					data[i][1]="";
				String qur="INSERT INTO Voucher VALUES('"+data[i][0].toString()+"','"+data2[0]+"','"+data2[2]+"','"+data2[1]+"','"+(data[i][1].toString())+"','"+data[i][2].toString()+"','"+data2[3]+"')";
		}
			catch(Exception d)
			{
				System.out.println(d);
				f++;
			}
		}
		
		System.out.println("e :"+e+"\nf :"+f);
		if(e==0)
		{
			if(f==0)
			{
				if(SystemProperty.PRINTER_PREVIEW_ENABLED)
				{
					PreviewVoucher obj=new PreviewVoucher(data,data2,0) ;
					
				}
				else
				{
					PrintVoucher obj=new PrintVoucher(data,data2,0);
				}
			}
			else
			{
				final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
				JOptionPane.showMessageDialog(null, "Please Enter only valid Data!", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
		else
		{
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
			JOptionPane.showMessageDialog(this, "All Fields are Required!", "Error", JOptionPane.ERROR_MESSAGE);
			e=0;
		}
		
		
			
	}

}

	@Override
	public void focusGained(FocusEvent e) 
	{
		if(e.getSource()==name_field)
		{
			Autocomplete.keywords=Autocomplete.getCompanyNames();
		}
	}



	@Override
	public void focusLost(FocusEvent e) 
	{
		if(e.getSource()==name_field)
		{
			if(!(Autocomplete.isCompanyExists(name_field.getText())))
			{
				Autocomplete.addCompanyName(name_field.getText());
			}
		}
		
	}
	
	
	
	int total;
	int selectedCol;
	private JTextField no_of_copy;
	private JLabel label;
	@Override
	public void tableChanged(TableModelEvent e)
	{
		total=0;
		if(e.getType()==TableModelEvent.UPDATE)
		{
		try
		{
		selectedCol=e.getColumn();
		if(selectedCol==2)
		{
			  for (int i=0; i<ROWS; i++)
	          {
				if(dtm.getValueAt(i, selectedCol)!=null && (!dtm.getValueAt(i, selectedCol).equals("")))
				{
	            int intValue = Integer.parseInt(dtm.getValueAt(i, selectedCol).toString());
	            total += intValue;
				}
	          }
			total1.setText(String.valueOf(total));
		}
		
		}catch(Exception exc)
		{
			System.out.println(exc);			
		}
		}
	
	}



	@Override
	public void valueChanged(ListSelectionEvent arg0)
	{
		if(AUTO)
		{
				dtm.setValueAt(table.getSelectedRow()+1, table.getSelectedRow(), 0);
				if(table.getSelectedColumn()==0)
				{
					table.changeSelection(table.getSelectedRow(), 1, false, false);
				}
			
		}
	}
	


public static int getDataRows()
{
	int rslt=0;
	int cell=0;
	/*tmp static variable points to table*/
	Object data[][]=new Object[tmp.getRowCount()][tmp.getColumnCount()];
	String data2[]=new String[5];
	for (int i = 0; i < tmp.getRowCount(); i++)
	{
		for(int j = 0; j < tmp.getColumnCount(); j++) 
	    {
			if(tmp_dtm.getValueAt(i, j)!=null && (!tmp_dtm.getValueAt(i, j).equals("")))
				cell++;
	    }
		
		if(cell!=0)
		{
			rslt++;
			cell=0;
		}
		
	}
	System.out.println("rows : "+rslt);
	return rslt;
	
}

public static void clearFields()
{
	table.editCellAt(100, 100);
	   //	tmp_dtm.removeTableModelListener(this);
		for (int i = 0; i < table.getRowCount(); i++)
		      for(int j = 0; j < table.getColumnCount(); j++) 
		      {
		    	  table.setValueAt(null, i, j);
		    	  total1.setText("");
		    	  total1.setText("");
		    	  name_field.setText("");
		      }
	//	dtm.addTableModelListener(this);
		
		if(AUTO)
		{
			table.setValueAt(1, 0, 0);
			table.changeSelection(0,1, false, false);
		}
		else
		{
			table.changeSelection(0,0, false, false);
		}
		
		name_field.requestFocus();

}




}
/*------------------------------------------------------------------------------------------*/
