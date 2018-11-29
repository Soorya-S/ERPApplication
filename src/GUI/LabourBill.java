package GUI;


import java.awt.Color;
import java.awt.Component;


import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.text.SimpleDateFormat;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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





















import com.lowagie.toolbox.arguments.IntegerArgument;

import Database.DataBase;
import Printer.NumberToWord;
import Printer.PreviewLabourBill;
import Printer.PrintLabourBill;
import Settings.SystemProperty;

import javax.swing.JPopupMenu;



public class LabourBill extends JPanel implements TableModelListener,ActionListener,FocusListener{
	

	/**
	 * Create the panel.
	 */
	
	private static JTable table;
	public static DefaultTableModel dtm;
	public static JTextField name_field;
	private static JTextField total2;
	private static JTextField total1;
	public static int ROWS=10;
	JButton btnClear,btnPrint,btnStore;
	public static JLabel date,num ;
	private static final String COMMIT_ACTION = "commit";
	
	public LabourBill() 
	{
		setSize(1106,566);
		setLayout(null);
		
		
		Date dNow = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

		date = new JLabel("");
		date.setForeground(Color.BLACK);
		
		date.setBounds(984, 65, 125, 14);
		date.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		
		date.setText(ft.format(dNow));
		add(date);
		
		num= new JLabel("");
		SystemProperty.getNumber();
		num.setText(String.valueOf(SystemProperty.LABOUR_NO));
		num.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		
		num.setBounds(56, 65, 46, 14);
		add(num);
	  
		
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon("src/Resources/images/labour_header.png"));
		label1.setBounds(0,0,1106,137);
		add(label1);
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(0, 117, 1100, 435);

		add(Table_panel);
		
		Object[] title={"Rate","Particulars","Total Pieces/Dozen","Amount"};
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
		table.getColumnModel().getColumn(3).setCellEditor(dce);
		
		
		dtm.setRowCount(ROWS);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(1500);
		table.getColumnModel().getColumn(2).setPreferredWidth(280);
		table.getColumnModel().getColumn(3).setPreferredWidth(220);
		table.setFont(new Font(date.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));		
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,1100,332);
		
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		
		btnStore = new JButton("Store");
		btnStore.setBounds(350, 359, 89, 28);
		btnStore.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_S, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnStore);
		
		btnPrint = new JButton("Print");
		btnPrint.setBounds(483, 359, 89, 28);
		btnPrint.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_P, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnPrint);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(620, 359, 89, 28);
		btnClear.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_D, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnClear);
		
		
		total2 = new JTextField();
		total2.setBounds(978, 338, 122, 25);
		
		Table_panel.add(total2);
		total2.setColumns(10);
	
		
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(796, 343, 46, 14);
		lblTotal.setFont(new Font(total2.getFont().getName(),Font.BOLD,12));
		Table_panel.add(lblTotal);
		
		total1 = new JTextField();
		total1.setBounds(852, 338, 124, 25);
		total1.setFont(new Font(total2.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		total2.setFont(new Font(date.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		//Table_panel.add(total1);
		total1.setColumns(10);
		
		name_field = new JTextField();
		name_field.setBounds(75, 86, 1008, 18);
		
		
		/*******************Auto complete code*********************/
		name_field.setFocusTraversalKeysEnabled(false);
		int size=Autocomplete.getCompanyNames().size();
			for(int i=0;i<size;i++)
			{
			Autocomplete.keywords.add(Autocomplete.getCompanyNames().get(i));
			}
		Autocomplete autoComplete = new Autocomplete(name_field);
		
        name_field.getDocument().addDocumentListener(autoComplete);
        
        name_field.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
        name_field.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
        name_field.addFocusListener(this);
        
        /*******************End of Auto complete code*********************/
		
        add(name_field);
		Table_panel.add(scroll);
		name_field.setColumns(10);
		name_field.setBorder(new EmptyBorder(0,0,0,0));
		Font font1 = new Font(total2.getFont().getName(),Font.BOLD,SystemProperty.TABLE_FONT_SIZE);
		name_field.setFont(font1);
        	
		total1.setEditable(false);
		total2.setEditable(false);
        	
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
		table.setCellSelectionEnabled(true);
		
		popupMenu = new JPopupMenu();
		clear=new JMenuItem("Clear This row");
		popupMenu.add(clear);
	
		
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
	    table.getActionMap().put("Enter", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent ae) 
	        {
	        	table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
	        }
	    });
		
		dtm.addTableModelListener(this);
		btnClear.addActionListener(this);
		btnPrint.addActionListener(this);
		btnStore.addActionListener(this);
		Table_panel.setFocusable(false);
		
		no_of_copy = new JTextField();
		new SystemProperty().getProperties();
		no_of_copy.setText(String.valueOf(SystemProperty.PRINTER_COPIES));
	
		no_of_copy.setColumns(10);
		no_of_copy.setBounds(575, 359, 18, 28);
		Table_panel.add(no_of_copy);
		
		label = new JLabel("Copy :");
		label.setBounds(567, 344, 38, 14);
		Table_panel.add(label);
		  
		num.setFocusable(false);
		date.setFocusable(false);
		label1.setFocusable(false);
		name_field.requestFocusInWindow();
		table.changeSelection(0, 0, false, false);
	}


	

	float doz,amt;
	int selectedCol;
	private JPopupMenu popupMenu;
	JMenuItem clear;
	private JTextField no_of_copy;
	private JLabel label;
	
	
	@Override
	public void tableChanged(TableModelEvent e)
	{
		
		
		doz=0;
		amt=0;
		
		if(e.getType()==e.UPDATE)
		{
			try
			{
				selectedCol=e.getColumn();
				if(selectedCol==2)
				{
					for (int i=0; i<ROWS; i++)
					{
						if(dtm.getValueAt(i, selectedCol)!=null)
						{
							float intValue = Float.parseFloat(dtm.getValueAt(i, selectedCol).toString());
							doz += intValue;
						}
					}
					total1.setText(String.valueOf(doz));
				}
				else if(selectedCol==3)
				{
					for (int i=0; i<ROWS; i++)
					{
						if(dtm.getValueAt(i, selectedCol)!=null)
						{
							float intValue = Float.parseFloat(dtm.getValueAt(i, selectedCol).toString());
							amt += intValue;
						}
					}
					total2.setText(String.valueOf(amt));
				}
			}catch(StackOverflowError | Exception exc)
			{
				
			}
			
				float j=0;
				float k=0;
			
			if(e.getColumn()==0 ||e.getColumn()==2)
			{
				try
				{
					if(table.getValueAt(table.getSelectedRow(), 0)!=null && table.getValueAt(table.getSelectedRow(), 2)!=null
							&& (!table.getValueAt(table.getSelectedRow(), 0).toString().equals("")) && (!table.getValueAt(table.getSelectedRow(), 2).toString().equals("")))
					{
						j=Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString());
						k=Float.parseFloat(table.getValueAt(table.getSelectedRow(), 0).toString());
						System.out.println(j+"\t"+k);
						table.setValueAt(j*k, table.getSelectedRow(), 3);
						table.changeSelection(table.getSelectedRow()+1, 0, false, false);
					}
					else
					{
						
					}
				}
				catch(Exception ec)
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(this, "Enter Valid data!");
				}
			}
		}
	}
	

	
	
	@Override
	public void actionPerformed(ActionEvent evt)
	{
		 
		if(evt.getSource()==btnClear)
		{
			clearFields();
		}
		
		else if(evt.getSource()==btnStore)
		{
			if(getDataRows()!=0)
				table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
			String data2[]=new String[7];
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
			data2[4]=total2.getText();
			NumberToWord ntw=new NumberToWord();
			
			if(total2.getText().equals(null) || total2.getText().equals(""))
			{
				data2[5]="";
				data2[6]="";
			}
			else
			{
				int m,n;
				
				m=Math.round(Float.valueOf((total2.getText())));
				data2[5]=ntw.convertNumberToWords(m)[0];
				data2[6]=ntw.convertNumberToWords(m)[1];
			}
			
			
			/******************Validating the data for null or empty ********************/
			
			int e=0;
			
			for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<4;j++)
				{
					
					if(data[i][j]==null )
						{
						data[i][j]="";
						}
				}
			}
			
			for(int i=0;i<5;i++)
			{
				
					if(data2[i].equals(""))
					{
						data2[i]="";
					}
				
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				try
				{
					String qur="INSERT INTO Labourbill VALUES("+(i+1)+",'"+data2[0]+"','"+data2[2]+"','"+(data[i][1]).toString()+"','"+data[i][0].toString()+"','"+data[i][2].toString()+"','"+data[i][3].toString()+"','"+data2[1]+"','"+data2[3]+"','"+data2[4]+"',1)";
					System.out.println("in store : "+qur);
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
					DataBase lb=new DataBase(data,data2,0);
					if(lb.RESULT==1)
					{
						JOptionPane.showMessageDialog(this, "Data was Stored Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
						clearFields();
						
						SystemProperty.LABOUR_NO++;
						SystemProperty.setNumber();
						
						try
						{
							Thread.sleep(1000);
						} catch (InterruptedException w){w.printStackTrace();}
					
						SystemProperty.getNumber();
						num.setText(String.valueOf(SystemProperty.LABOUR_NO));
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
		else if(evt.getSource()==btnPrint)
		{
		
			table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
			String data2[]=new String[7];
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
			data2[4]=total2.getText();
			
			NumberToWord ntw=new NumberToWord();
			
			if(total2.getText().equals(null) || total2.getText().equals(""))
			{
				data2[5]="";
				data2[6]="";
			}
			else
			{
				int n=Math.round(Float.valueOf(total2.getText()));
				data2[5]=ntw.convertNumberToWords(n)[0];
				data2[6]=ntw.convertNumberToWords(n)[1];
			
			}
			

			/******************Validating the data for null or empty ********************/
			
			int e=0;
			for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<4;j++)
				{
					
					if(data[i][j]==null )
						{
						data[i][j]="";
						}
				}
			}
			
			for(int i=0;i<5;i++)
			{
				
					if(data2[i].equals(""))
					{
						data2[i]="";
					}
				
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				try
				{
					String qur="INSERT INTO Labourbill VALUES("+(i+1)+",'"+data2[0]+"','"+data2[2]+"','"+(data[i][1]).toString()+"','"+data[i][0].toString()+"','"+data[i][2].toString()+"','"+data[i][3].toString()+"','"+data2[1]+"','"+data2[3]+"','"+data2[4]+"',1)";			
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
						PreviewLabourBill obj=new PreviewLabourBill(data,data2,0) ;
					}
					else
					{
						PrintLabourBill obj=new PrintLabourBill(data,data2,0);						
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
	public void focusGained(FocusEvent arg0)
	{
		if(arg0.getSource()==name_field)
		{
			Autocomplete.keywords=Autocomplete.getCompanyNames();
		}
		
	}
	

	@Override
	public void focusLost(FocusEvent arg0)
	{
		if(arg0.getSource()==name_field)
		{
			if(!(Autocomplete.isCompanyExists(name_field.getText())))
			{
				Autocomplete.addCompanyName(name_field.getText());
			}
		}
	}
	
	public static int getDataRows()
	{
		int rslt=0;
		int cell=0;
		for (int i = 0; i < table.getRowCount(); i++)
		{
			for(int j = 0; j < table.getColumnCount(); j++) 
		    {
				if(dtm.getValueAt(i, j)!=null && (!dtm.getValueAt(i, j).equals("")))
					cell++;
		    }
			
			if(cell!=0)
			{
				rslt++;
				cell=0;
			}
			
		}
		
		return rslt;
	}
	
	//static JPanel tmp_panel=this;
	
	public static void clearFields()
	{
		table.editCellAt(100, 4);
		//dtm.removeTableModelListener(this );
		/*for (int i = 0; i < table.getRowCount(); i++)
		      for(int j = 0; j < table.getColumnCount(); j++) 
		      {
		    	  table.setValueAt(null, i, j);
		      }*/
			
		dtm.setRowCount(0);
		
		dtm.setRowCount(10);
		
		total1.setText("");
    	
		total2.setText("");
    	
		name_field.setText("");
		
		table.changeSelection(0, 0, false, false);
		name_field.requestFocus();
	
	}
	
}
