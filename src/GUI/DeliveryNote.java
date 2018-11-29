package GUI;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;

import Database.DataBase;





import Printer.NumberToWord;
import Printer.PreviewDeliveryNote;
import Printer.PrintDeliveryNote;
import Settings.SystemProperty;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JScrollPane;


public class DeliveryNote extends JPanel implements ActionListener,TableModelListener,ListSelectionListener,FocusListener
{
	private JTable table;
	DefaultTableModel dtm;
	static  DefaultTableModel tmp_dtm;
	private static JTextField name_field;
	private static JTextField total1,total2;
	public int ROWS=10;
	private static JTable tmp;
	public static Boolean AUTO;
	JButton btnStore,btnClear,btnPrint;
	public static JLabel date,num;
	private static final String COMMIT_ACTION = "commit";
	
	
	public DeliveryNote() 
	{
		SystemProperty.getProperties();
		AUTO=SystemProperty.DELIVERY_SL_NO_AUTO;
		setSize(1106,566);
		setLayout(null);
		
		Date dNow = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");

		date = new JLabel("");
		date.setForeground(Color.BLACK);
		
		date.setBounds(984, 65, 125, 14);
		date.setText(ft.format(dNow));
		date.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		add(date);
		
		num = new JLabel();
		SystemProperty.getNumber();
		num.setText(String.valueOf(SystemProperty.DELIVERY_NO));
		num.setBounds(56, 65, 46, 14);
		num.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		add(num);
	  
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon("src/Resources/images/delivery_header.png"));
		label1.setBounds(0,0,1106,137);
		add(label1);
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(0, 117, 1100, 435);
		add(Table_panel);
		
		Object[] title={"S.No","DC.No.","Particulars","CM","Size","Dozen/Box"};
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
		table.getColumnModel().getColumn(4).setCellEditor(dce);
		table.getColumnModel().getColumn(5).setCellEditor(dce);
	
		dtm.setRowCount(ROWS);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(1500);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(230);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,1100,332);
		
		table.setFillsViewportHeight(true);
		table.setFont(new Font(date.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		table.setRowHeight(30);
		
		
		Table_panel.add(scroll);
		
		
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
		
		total2.setBounds(988, 343, 112, 25);
		Table_panel.add(total2);
		total2.setColumns(10);
		
		total1 = new JTextField();
		total1.setBounds(905, 343, 79, 25);
		Table_panel.add(total1);
		total2.setColumns(10);
		
		
		
		
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setBounds(856, 343, 50, 14);
		lblTotal.setFont(new Font(total2.getFont().getName(),Font.BOLD,12));
		Table_panel.add(lblTotal);
		
		
		total2.setFont(new Font(total2.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		
		total2.setEditable(false);
		
		total1.setFont(new Font(total2.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
		
		total1.setEditable(false);
		
		name_field = new JTextField();
		name_field.setBounds(75, 86, 1008, 18);
		
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
		
		Font font1 = new Font(total2.getFont().getName(),Font.BOLD,SystemProperty.TABLE_FONT_SIZE);
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
		tmp=table;
		
		no_of_copy = new JTextField();
		new SystemProperty().getProperties();
		no_of_copy.setText(String.valueOf(SystemProperty.PRINTER_COPIES));
	
		no_of_copy.setBounds(577, 359, 18, 28);
		Table_panel.add(no_of_copy);
		no_of_copy.setColumns(10);
		
		JLabel lblCopies = new JLabel("Copy :");
		lblCopies.setBounds(569, 344, 38, 14);
		Table_panel.add(lblCopies);
		tmp_dtm=dtm;
		
		dtm.addTableModelListener(this);
		btnClear.addActionListener(this);
		btnPrint.addActionListener(this);
		btnStore.addActionListener(this);
														
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
	    table.getActionMap().put("Enter", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent ae) 
	        {
	        	table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
	        }
	    });
	    
		name_field.requestFocus();
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(this);
	   		if(AUTO)
		{
			table.changeSelection(0, 1, false, false);
		}
		else
		{
			table.changeSelection(0, 0, false, false);
		}
	}
	
	float doz,size,amt;
	int selectedCol;
	private JTextField no_of_copy;
	
	
	
	@Override
	public void tableChanged(TableModelEvent e)
	{		
		if(e.getType()==TableModelEvent.UPDATE)
		{
			doz=0;
			size=0;
		try
		{
		selectedCol=e.getColumn();
	/*	if(selectedCol==4)
		{
			  for (int i=0; i<ROWS; i++)
	          {
				if(dtm.getValueAt(i, selectedCol)!=null)
				{
	            float intValue = Float.valueOf(dtm.getValueAt(i, selectedCol).toString());
	            size += intValue;
				}
	          }
			total1.setText(String.valueOf(size));
		}
		*/
		if(selectedCol==5)
		{

			String no1="0.00",no2="0.00";
			int x3=0,x4=0,x5,x6;
			
			
			for (int i=0; i<getDataRows(); i++)
	        {
					
				if(dtm.getValueAt(i, selectedCol)==null || (dtm.getValueAt(i, selectedCol).equals("")))
				{
					no1="0.00";
					no2="0.00";
				}
				else
				{
					no1=dtm.getValueAt(i, selectedCol).toString();
					no2=dtm.getValueAt(i, selectedCol).toString();
					if(Math.floor(Float.valueOf(dtm.getValueAt(i, selectedCol).toString())) == (Float.valueOf(dtm.getValueAt(i, selectedCol).toString())))
					{
						no1=String.valueOf(Float.parseFloat(dtm.getValueAt(i, selectedCol).toString()));
						no2=String.valueOf(Float.parseFloat(dtm.getValueAt(i, selectedCol).toString()));
					} 
				}
				
				
				no2=no2.substring(0,no1.indexOf("."));
				no1=no1.substring(no1.indexOf(".")).substring(1);
				x3=x3+Integer.valueOf(no1);
				x4=x4+Integer.valueOf(no2);				
	        }
		
			x5=x3/12;
			x6=x3%12;
			x4=x4+x5;
			x3=x6;
			no1=x4+"."+x3;
			total2.setText(no1);
				
			
			/**************************************end of code to calculate dozens***************************************/

		}
			
		}catch(NumberFormatException exc)
		{
			System.out.println("err : "+exc);			
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
			table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
			String data2[]=new String[7];
			for (int i = 0; i < getDataRows(); i++)
			{
				for(int j = 0; j < table.getColumnCount(); j++) 
			    {
					data[i][j]=dtm.getValueAt(i, j);
					System.out.print(data[i][j]+" ");
			    }
				System.out.println();
			}
			
			data2[0]=num.getText();
			data2[1]=date.getText();
			
			data2[2]=name_field.getText();
			data2[3]=total1.getText(); 
			data2[4]=total2.getText(); 
		
			/******************Validating the data for null or empty- Before store in DB********************/
			int e=0;
			/*	for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<6;j++)
				{
								
					if(j!=1 && j!=2 &&j!=3 && j!=4 )
					{
						if(data[i][j]==null || data[i][j].equals(""))
						{
							e++;
						}
					}
				
				}
			}
		*/
			
			for(int i=0;i<5;i++)
			{	
				
					if(data2[i]==null )
					{
						data2[i]="";
					}
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				if(data[i][0]==null)
				{
					data[i][0]="";
				}
				if(data[i][1]==null)
				{
					data[i][1]="";
				}
				if(data[i][2]==null)
				{
					data[i][2]="";
				}
				if(data[i][3]==null)
				{
					data[i][3]="";
				}
				if(data[i][4]==null)
				{
					data[i][4]="";
				}
				if(data[i][5]==null)
				{
					data[i][5]="";
				}
				try
				{
					String qur="INSERT INTO DeliveryNote VALUES('"+data[i][0].toString()+"','"+data2[0]+"','"+data2[2]+"','"+data[i][1].toString()+"','"+data2[1]+"','"+data[i][2].toString()+"','"+data[i][3].toString()+"','"+data[i][4].toString()+"','"+data[i][5].toString()+"','"+data2[3]+"','"+data2[4]+"')";	
				}
				catch(Exception d)
				{
					System.out.println(d);
					f++;
				}
			}
			
			
			if(e==0)
			{
				if(f==0)
				{
					DataBase db=new DataBase(data,data2,1);
						if(db.RESULT==1)
						{
							JOptionPane.showMessageDialog(this, "Data was Stored Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
							clearFields();
							SystemProperty.DELIVERY_NO++;
							SystemProperty.setNumber();
							
							try
							{
								Thread.sleep(1000);
							} catch (InterruptedException w){w.printStackTrace();}
						
							SystemProperty.getNumber();
							num.setText(String.valueOf(SystemProperty.DELIVERY_NO));
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
			SystemProperty.PRINTER_COPIES=Integer.parseInt(no_of_copy.getText());
			
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
			
			
			/******************Validating the data for null or empty ********************/
			
			int e=0;
			/*	for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<6;j++)
				{
								
					if(j!=1 && j!=2 &&j!=3 && j!=4 )
					{
						if(data[i][j]==null || data[i][j].equals(""))
						{
							e++;
						}
					}
				
				}
			}
		*/
			for(int i=0;i<5;i++)
			{	

				if(data2[i]==null )
				{
					data2[i]="";
				}
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				if(data[i][0]==null)
				{
					data[i][0]="";
				}
				if(data[i][1]==null)
				{
					data[i][1]="";
				}
				if(data[i][2]==null)
				{
					data[i][2]="";
				}
				if(data[i][3]==null)
				{
					data[i][3]="";
				}
				if(data[i][4]==null)
				{
					data[i][4]="";
				}
				if(data[i][5]==null)
				{
					data[i][5]="";
				}
				try
				{
					String qur="INSERT INTO DeliveryNote VALUES('"+data[i][0].toString()+"','"+data2[0]+"','"+data2[2]+"','"+data[i][1].toString()+"','"+data2[1]+"','"+data[i][2].toString()+"','"+data[i][3].toString()+"','"+data[i][4].toString()+"','"+data[i][5].toString()+"','"+data2[3]+"','"+data2[4]+"')";	
				}
				catch(Exception d)
				{
					//System.out.println(d);
					f++;
				}
			}
			
			
			if(e==0)
			{
				if(f==0)
				{
					if(SystemProperty.PRINTER_PREVIEW_ENABLED)
					{
						PreviewDeliveryNote obj=new PreviewDeliveryNote(data,data2,0) ;
					}
					else
					{
						PrintDeliveryNote obj=new PrintDeliveryNote(data,data2,0);
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
		
		return rslt;
		
	}

	@Override 
	public void valueChanged(ListSelectionEvent arg0)
	{
		if(AUTO)
		{
			dtm.setValueAt(table.getSelectedRow()+1, table.getSelectedRow(),0);
				if(table.getSelectedColumn()==0)
				{
					table.changeSelection(table.getSelectedRow(), 1, false, false);
				}	
		}
	}
	
	
	
	public static void clearFields()
	{
		tmp.editCellAt(100, 100);
	   //	tmp_dtm.removeTableModelListener(this);
		for (int i = 0; i < tmp.getRowCount(); i++)
		      for(int j = 0; j < tmp.getColumnCount(); j++) 
		      {
		    	  tmp.setValueAt(null, i, j);
		      }
	//	dtm.addTableModelListener(this);
		
	  	 
		total2.setText("");
    	
		name_field.setText("");
  
		if(AUTO)
		{
			tmp.setValueAt(1, 0, 0);
			tmp.changeSelection(0,1, false, false);
			
		}
		else
		{
			tmp.changeSelection(0,0, false, false);
		}
		
		name_field.requestFocus();
	}
}

