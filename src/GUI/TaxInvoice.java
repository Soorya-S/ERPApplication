package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Database.DataBase;
import Settings.SystemProperty;
import Printer.NumberToWord;
import Printer.PreviewTaxInvoice;
import Printer.PrintTaxInvoice;


public class TaxInvoice extends JPanel implements ActionListener,TableModelListener,ListSelectionListener,FocusListener,MouseListener
{
	static JTable table;
	static DefaultTableModel dtm;
	public static JTextField num,name_field,address_field,gstin_field,state_field,state_code_field,vehicle_info_field;
	static JTextField total1;
	public static int ROWS=7;
	static JButton btnClear,btnStore,btnPrint;
	public static Boolean AUTO;
	static  DefaultTableModel tmp_dtm;
	private static JTable tmp;
	int LENGTH=40;
	private static final String COMMIT_ACTION = "commit";
	private static JTextField grand_total_field,date;
	private JTextField sgst_field;
	private JTextField cgst_field;
	private JTextField no_of_copy;
	private JLabel label;
	
	public TaxInvoice() 
	{
		setSize(1106,566);
		setLayout(null);
		
		
		SystemProperty.getProperties();
		
		Date dNow = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
	    
		date = new JTextField("");
		date.setForeground(Color.BLACK);
		date.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		
		date.setBounds(983, 67, 125, 18);
		date.setText(ft.format(dNow));
		date.setBorder(new EmptyBorder(0,0,0,0));
		
		date.setFocusable(false);
		date.setEditable(false);
		date.addMouseListener(this);
		date.setBackground(Color.WHITE);
		add(date);
		
		
		num = new JTextField();
		num.setBounds(110, 66, 46, 14);
		num.setBorder(new EmptyBorder(0,0,0,0));
		num.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()+1));
		SystemProperty.getNumber();
		num.setText(String.valueOf(SystemProperty.TAX_INOVOICE_NO));
		num.setFocusable(false);
		num.setEditable(false);
		num.addMouseListener(this);
		num.setBackground(Color.WHITE);
		add(num);
	  
		
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon("src/Resources/images/TaxInvoice_header.png"));
		label1.setBounds(0,0,1106,192);
		add(label1);
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(0, 177, 1100, 417);
		add(Table_panel);
		
		Object[] title={"Rate","Particulars","HSN Code","Qt. (Dozen)","Amount Rs."};
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
		
		dtm.setRowCount(ROWS);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(950);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,1100,242);
		
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.setFont(new Font(date.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
			
		Table_panel.add(scroll);
		
		btnStore = new JButton("Store");
		btnStore.setBounds(350, 299, 89, 28);
		btnStore.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_S, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnStore);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(620, 299, 89, 28);
		btnClear.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_D, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnClear);
		
		
		btnPrint = new JButton("Print");
		btnPrint.setBounds(483, 299, 89, 28);
		btnPrint.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_P, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		Table_panel.add(btnPrint);
		
		
		total1 = new JTextField();
		total1.setBounds(945, 253, 155, 28);
		Table_panel.add(total1);
		total1.setFont(new Font(total1.getFont().getName(),Font.PLAIN,14));
		total1.setColumns(10);
		total1.setEditable(false);
		
		
		JLabel lblTotal = new JLabel("Total :");
		
		
		lblTotal.setFont(new Font(date.getFont().getName(),Font.BOLD,date.getFont().getSize()));
		
		
		lblTotal.setBounds(895, 261, 40, 14);
		Table_panel.add(lblTotal);
		
		name_field= new JTextField();
		
		address_field= new JTextField();
		gstin_field= new JTextField();
		state_field= new JTextField();
		state_field.setText("Tamil Nadu");
		state_code_field= new JTextField("33");
		vehicle_info_field= new JTextField();
		
		name_field.setBounds(75, 88, 250, 18);
		address_field.setBounds(400, 88, 675, 18);
		gstin_field.setBounds(75, 119, 250, 18);
		state_field.setBounds(377, 119, 255, 18);
		state_code_field.setBounds(710, 119, 60, 18);
		vehicle_info_field.setBounds(110, 150, 655, 18);
		
		/*******************Auto complete code*********************/
		//name_field.setFocusTraversalKeysEnabled(false);
		Autocomplete.keywords=Autocomplete.getCompanyNames();
		
		//state_field.setVisible(true);
        
		
		Autocomplete autoComplete = new Autocomplete(name_field);
		
        name_field.getDocument().addDocumentListener(autoComplete);
        
        name_field.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
        name_field.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
        name_field.addFocusListener(this);
        
        /*******************End of Auto complete code*********************/
        
        add(name_field);
		add(address_field);
		add(gstin_field);
		add(state_field);
		add(state_code_field);
		add(vehicle_info_field);
		EmptyBorder empty_border=new EmptyBorder(0,0,0,0);
		
		name_field.setColumns(10);
		name_field.setBorder(empty_border);
		Font font1 = new Font(name_field.getFont().getName(),Font.BOLD,SystemProperty.TABLE_FONT_SIZE);
		name_field.setFont(font1);
        
		address_field.setColumns(10);
		address_field.setBorder(empty_border);
		address_field.setFont(font1);
       
		gstin_field.setColumns(10);
		gstin_field.setBorder(empty_border);
		gstin_field.setFont(font1);
       	
		state_field.setColumns(10);
		state_field.setBorder(empty_border);
		state_field.setFont(font1);
		state_field.setFocusable(false);
		state_field.addMouseListener(this);
		
		state_code_field.setColumns(10);
		state_code_field.setBorder(empty_border);
		state_code_field.setFont(font1);
		state_code_field.setFocusable(false);
		state_code_field.addMouseListener(this);
		
		vehicle_info_field.setColumns(10);
		vehicle_info_field.setBorder(empty_border);
		vehicle_info_field.setFont(font1);
    
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
		
	   /*
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(this);
	    */
	    tmp_dtm=dtm;
	    tmp=table;
	    
	    grand_total_field = new JTextField();
	    grand_total_field.setEditable(false);
	    grand_total_field.setBounds(945, 299, 155, 28);
	    Table_panel.add(grand_total_field);
	    grand_total_field.setColumns(10);
	    
	    JLabel lblGrandTotal = new JLabel("Grand Total :");
	    lblGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
	    lblGrandTotal.setBounds(860, 306, 72, 14);
	    Table_panel.add(lblGrandTotal);
	    
	    JLabel label = new JLabel("SGST :");
	    label.setFont(new Font("Tahoma", Font.BOLD, 11));
	    label.setBounds(748, 279, 46, 14);
	    Table_panel.add(label);
	    
	    JLabel label_1 = new JLabel("CGST :");
	    label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
	    label_1.setBounds(748, 310, 46, 14);
	    Table_panel.add(label_1);
	    
	    sgst_field = new JTextField();
	    sgst_field.setText("2.5");
	    sgst_field.setColumns(10);
	    sgst_field.setBounds(788, 276, 26, 20);
	    Table_panel.add(sgst_field);
	    
	    cgst_field = new JTextField();
	    cgst_field.setText("2.5");
	    cgst_field.setColumns(10);
	    cgst_field.setBounds(788, 307, 26, 20);
	    Table_panel.add(cgst_field);
	    
	    JLabel label_2 = new JLabel("%");
	    label_2.setBounds(824, 279, 26, 14);
	    Table_panel.add(label_2);
	    
	    JLabel label_3 = new JLabel("%");
	    label_3.setBounds(824, 310, 18, 14);
	    Table_panel.add(label_3);
	    
	    label1.setFocusable(false);
		name_field.requestFocusInWindow();
		table.changeSelection(0, 0, false, false);
	    
	    
	}
	float doz,amt,grand_total;
	int selectedCol;
	@Override
	public void tableChanged(TableModelEvent e) 
	{
		doz=0;
		amt=0;
		grand_total=0;
		if(e.getType()==e.UPDATE)
		{
			try
			{
				selectedCol=e.getColumn();
				
				if(selectedCol==4)
				{
					for (int i=0; i<ROWS; i++)
					{
						if(dtm.getValueAt(i, selectedCol)!=null)
						{
							float intValue = Float.parseFloat(dtm.getValueAt(i, selectedCol).toString());
							amt += intValue;
							
							float tmp=Float.valueOf(sgst_field.getText())+Float.valueOf(cgst_field.getText());
							grand_total=((amt/100)*tmp)+amt;
						}
					}
					total1.setText(String.valueOf(amt));
					grand_total_field.setText(String.valueOf(grand_total));
					
					
				}
			}catch(StackOverflowError | Exception exc)
			{
				
			}
			
				float j=0;
				float k=0;
			
			if(e.getColumn()==0 ||e.getColumn()==3)
			{
				try
				{
					if(table.getValueAt(table.getSelectedRow(), 0)!=null && table.getValueAt(table.getSelectedRow(), 3)!=null
							&& (!table.getValueAt(table.getSelectedRow(), 0).toString().equals("")) && (!table.getValueAt(table.getSelectedRow(), 3).toString().equals("")))
					{
						j=Float.parseFloat(table.getValueAt(table.getSelectedRow(), 3).toString());
						k=Float.parseFloat(table.getValueAt(table.getSelectedRow(), 0).toString());
						System.out.println(j+"\t"+k);
						table.setValueAt(j*k, table.getSelectedRow(), 4);
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
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==btnClear)
		{
			clearFields();
		}
		else if(arg0.getSource()==btnStore)
		{
			if(getDataRows()!=0)
				table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
			String data2[]=new String[14];
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
			data2[3]=address_field.getText();
			data2[4]=gstin_field.getText();
			data2[5]=state_field.getText();
			data2[6]=state_code_field.getText();
			data2[7]=vehicle_info_field.getText();
			data2[8]=total1.getText();
			data2[9]=sgst_field.getText();
			data2[10]=cgst_field.getText();
			data2[11]=grand_total_field.getText();
			
		
			NumberToWord ntw=new NumberToWord();
			if(total1.getText().equals(null) || total1.getText().equals(""))
			{
				data2[12]="";
				data2[13]="";
			}
			else
			{
				int m,n;
				
				m=Math.round(Float.valueOf((total1.getText())));
				data2[12]=ntw.convertNumberToWords(m)[0];
				data2[13]=ntw.convertNumberToWords(m)[1];
			}
			
			
			/******************Validating the data for null or empty ********************/
			
			int e=0;
			
			for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<5;j++)
				{
					
					if(data[i][j]==null )
					{
						data[i][j]="";
					}
				}
			}
			
			for(int i=0;i<12;i++)
			{
				
					if(data2[i].equals(null) ||data2[i].equals(""))
					{
						data2[i]="";
					}
				
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				try
				{				
					String qur="INSERT INTO TaxInvoice VALUES"+ "('"+(i+1)+"',"+data2[0]+",'"+data2[1]+"','"+data2[2]+"','"+data2[3]+"','"+data2[4]+"','"+data2[5]+"','"+data2[6]+"','"+data2[7]+"','"+data[i][0]+"','"+data[i][1]+"','"+data[i][2]+"','"+data[i][3]+"','"+data[i][4]+"','"+data2[8]+"','"+data2[9]+"','"+data2[10]+"','"+data2[11]+"'";
					//System.out.println("in store : "+qur);
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
					DataBase lb=new DataBase(data,data2,3);
					if(lb.RESULT==1)
					{
						JOptionPane.showMessageDialog(this, "Data was Stored Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
						clearFields();
						
						SystemProperty.TAX_INOVOICE_NO++;
						SystemProperty.setNumber();
						
						try
						{
							Thread.sleep(1000);
						} catch (InterruptedException w){w.printStackTrace();}
					
						SystemProperty.getNumber();
						num.setText(String.valueOf(SystemProperty.TAX_INOVOICE_NO));
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
			if(getDataRows()!=0)
				table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
			String data2[]=new String[14];
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
			data2[3]=address_field.getText();
			data2[4]=gstin_field.getText();
			data2[5]=state_field.getText();
			data2[6]=state_code_field.getText();
			data2[7]=vehicle_info_field.getText();
			data2[8]=total1.getText();
			data2[9]=sgst_field.getText();
			data2[10]=cgst_field.getText();
			data2[11]=grand_total_field.getText();
			
		
			NumberToWord ntw=new NumberToWord();
			if(total1.getText().equals(null) || total1.getText().equals(""))
			{
				data2[12]="";
				data2[13]="";
			}
			else
			{
				int m,n;
				
				m=Math.round(Float.valueOf((total1.getText())));
				data2[12]=ntw.convertNumberToWords(m)[0];
				data2[13]=ntw.convertNumberToWords(m)[1];
			}
			
			
			/******************Validating the data for null or empty ********************/
			
			int e=0;
			
			for(int i=0;i<getDataRows();i++)
			{
				for(int j=0;j<5;j++)
				{
					
					if(data[i][j]==null )
					{
						data[i][j]="";
					}
				}
			}
			
			for(int i=0;i<12;i++)
			{
				
					if(data2[i].equals(null) ||data2[i].equals(""))
					{
						data2[i]="";
					}
				
			}
			
			int f=0;
			for(int i=0;i<data.length;i++)
			{
				try
				{				
					String qur="INSERT INTO TaxInvoice VALUES"+ "('"+(i+1)+"',"+data2[0]+",'"+data2[1]+"','"+data2[2]+"','"+data2[3]+"','"+data2[4]+"','"+data2[5]+"','"+data2[6]+"','"+data2[7]+"','"+data[i][0]+"','"+data[i][1]+"','"+data[i][2]+"','"+data[i][3]+"','"+data[i][4]+"','"+data2[8]+"','"+data2[9]+"','"+data2[10]+"','"+data2[11]+"'";
					//System.out.println("in store : "+qur);
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
					if(SystemProperty.PRINTER_PREVIEW_ENABLED)
					{
						PreviewTaxInvoice p=new PreviewTaxInvoice(data, data2, 0);
					}
					else
					{
						
						PrintTaxInvoice p=new PrintTaxInvoice(data, data2, 0);
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
		state_code_field.setText("TamilNadu");
		
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

	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		if(e.getComponent()==date)
		{
			date.setFocusable(true);
			date.setEditable(true);
		}
		else if(e.getComponent()==num)
		{
			num.setFocusable(true);
			num.setEditable(true);
		}
		else if(e.getComponent()==state_code_field)
		{
			state_code_field.setFocusable(true);
		}
		else if(e.getComponent()==state_field)
		{
			state_field.setFocusable(true);
		}
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) 
	{
		if(e.getComponent()==date)
		{
			date.setFocusable(false);
			date.setEditable(false);
		}
		else if(e.getComponent()==num)
		{
			num.setFocusable(false);
			num.setEditable(false);
		}
		else if(e.getComponent()==state_code_field)
		{
			state_code_field.setFocusable(false);
		}
		else if(e.getComponent()==state_field)
		{
			state_field.setFocusable(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		
		dtm.setRowCount(7);
		
		total1.setText("");
    	grand_total_field.setText("");
    	
		name_field.setText("");
		address_field.setText("");
		gstin_field.setText("");
		
		vehicle_info_field.setText("");
		
		table.changeSelection(0, 0, false, false);
		name_field.requestFocus();
	
	}

}
