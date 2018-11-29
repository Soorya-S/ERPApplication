package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

import javax.swing.UIManager;

import com.toedter.components.JLocaleChooser;

public class GenerateReport extends JDialog implements ActionListener
{
	JComboBox comboBox;
	JButton s_generate_btn,c_generate_btn,btnClose ;
	JDateChooser  from_date,to_date;
	JRadioButton c_yes,c_no;
	int COMPLETE_FLAG=0;
	int DISINTINCT_ROWS=0;
	
	public GenerateReport()
	{
		setTitle("Generate Report");
		
		getContentPane().setBackground(Color.WHITE);
		setSize(370, 411);
		setVisible(true);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Short Report", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 334, 194);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblGenerateReportFrom = new JLabel("Generate Report From..");
		lblGenerateReportFrom.setBounds(10, 33, 116, 14);
		panel.add(lblGenerateReportFrom);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Labour Bill", "Deliver Note", "Voucher", "Other Income Report","Other Expense Report"}));
		comboBox.setBounds(165, 30, 116, 20);
		panel.add(comboBox);
		
		JLabel lblFromeDate = new JLabel("Frome Date");
		lblFromeDate.setBounds(10, 71, 132, 14);
		panel.add(lblFromeDate);
		
		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setBounds(10, 108, 46, 14);
		panel.add(lblToDate);
		 
		ButtonGroup bg = new ButtonGroup();
		
		s_generate_btn = new JButton("Generate");
		s_generate_btn.setBounds(104, 156, 116, 23);
		s_generate_btn.addActionListener(this);
		panel.add(s_generate_btn);
		
		from_date = new JDateChooser();
		from_date.setDateFormatString("dd-MM-yyyy");
		
	
		from_date.setBounds(165, 71, 116, 20);
		panel.add(from_date);
		
		to_date = new JDateChooser();
		to_date.setDateFormatString("dd-MM-yyyy");
		to_date.setBounds(165, 108, 116, 20);
		panel.add(to_date);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Complete Report", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 220, 334, 110);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGenerateAComplete = new JLabel("Generate a complete report from the beginning of the time till now");
		lblGenerateAComplete.setBounds(10, 31, 328, 14);
		panel_1.add(lblGenerateAComplete);
		
		ButtonGroup bg2=new ButtonGroup();
		
		JButton c_generate_btn = new JButton("Generate");
		c_generate_btn.setBounds(104, 69, 116, 23);
		c_generate_btn.addActionListener(this);
		panel_1.add(c_generate_btn);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(131, 341, 89, 23);
		getContentPane().add(btnClose);
		btnClose.addActionListener(this);
	}
	
	String from,to;
	Boolean c_preview=null,s_preview=null;
	@Override
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==s_generate_btn)
		{
			from=((JTextField)from_date.getDateEditor().getUiComponent()).getText();
			to=((JTextField)to_date.getDateEditor().getUiComponent()).getText();
		
			if(from.equals("") || from==null || to==null || to.equals(null))
			{
				JOptionPane.showMessageDialog(this, "Both From and To date are Required to Generate Report", "Message", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(comboBox.getSelectedIndex()==0)
				{
					writePdf(getLabourBillData(from,to),0,from,to);
				}
				else if(comboBox.getSelectedIndex()==1)
				{
					writePdf(getDeliveryNoteData(from,to),1,from,to);
				}
				else if(comboBox.getSelectedIndex()==2)
				{
					writePdf(getVoucherData(from,to),2,from,to);
				}
				else if(comboBox.getSelectedIndex()==3)
				{
					writePdf(getOtherIncomeData(from,to),3,from,to);	
				}
				else if(comboBox.getSelectedIndex()==4)
				{
					writePdf(getOtherExpenseData(from,to),4,from,to);
				}
			}
		}
		else if(e.getSource()==c_generate_btn)
		{
			COMPLETE_FLAG=1;
		}
		else if(e.getSource()==btnClose)
		{
			this.dispose();
		}
	}




/***********************************************getLabourBillData()*************************************************/

	public String[][] getLabourBillData(String from_date, String to_date)
	{
	String data[][]=null;
	List<Date> dates = new ArrayList<Date>();
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Connection c=null;
	Statement s=null;
	ResultSet rs=null;
	int size=0;
	
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
		s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
	} catch (ClassNotFoundException | SQLException e1)
	{
		e1.printStackTrace();
	}
	
	dates=getDaysBetweenDates(from_date,to_date);
	
	for(int i=0;i<dates.size();i++)
	{
	   String qur="SELECT * FROM Labourbill WHERE SysDate LIKE '"+formatter.format((Date)dates.get(i))+"' AND IS_LABOUR_BILL=1 ORDER BY Bill_No ASC";
	  
			try 
			{
				rs=s.executeQuery(qur);
				while(rs.next())
					size++;
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
	}
	
	data=new String[size][8];
	int i=0,j=0; 
	try
	{
		
		
		while(j<dates.size())
		{
			String qur="SELECT * FROM Labourbill WHERE SysDate LIKE '"+formatter.format((Date)dates.get(j))+"' AND IS_LABOUR_BILL=1 ORDER BY Bill_No ASC";
			rs=s.executeQuery(qur);
		
		while(rs.next())
		{
			data[i][0]=rs.getString("Bill_No");
			data[i][1]=rs.getString("SysDate");
			data[i][2]=rs.getString("Customer_Name");
			data[i][3]=rs.getString("Particulars");
			data[i][4]=rs.getString("Rate");
			data[i][5]=rs.getString("Dozens");
			data[i][6]=rs.getString("Amount");
			data[i][7]=rs.getString("Total2");	
			i++;
		}
		j++;
		}
	}
	catch(Exception e)
	{
		System.err.println(e);
	}
	return data;
}


	
	/***********************************************getDeliveryNoteData()*************************************************/
	public String[][] getDeliveryNoteData(String from_date, String to_date)
	{
		String data[][]=null;
		List<Date> dates = new ArrayList<Date>();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Connection c=null;
		Statement s=null;
		ResultSet rs=null;
		int size=0;
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
			s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (ClassNotFoundException | SQLException e1)
		{
			e1.printStackTrace();
		}
		
		dates=getDaysBetweenDates(from_date,to_date);
		
		for(int i=0;i<dates.size();i++)
		{
			String qur="SELECT * FROM DeliveryNote WHERE Date LIKE '"+formatter.format((Date)dates.get(i))+"' ORDER BY Bill_No ASC";
			
				try 
				{
					rs=s.executeQuery(qur);
					
					while(rs.next())
						size++;
					
				} catch (SQLException e)
				{
					System.out.println(e);
				}
		}
		System.out.println("Size : "+size);
		data=new String[size][9];
		int i=0,j=0; 
		try
		{
			while(j<dates.size())
			{
			String qur="SELECT * FROM DeliveryNote WHERE Date LIKE '"+formatter.format((Date)dates.get(j))+"' ORDER BY Bill_No ASC";
			rs=s.executeQuery(qur);
			
				while(rs.next())
				{
					data[i][0]=rs.getString("Bill_No");
					data[i][1]=rs.getString("Date");
					data[i][2]=rs.getString("Dc_NO");
					data[i][3]=rs.getString("Name");
					data[i][4]=rs.getString("Particulars");
					data[i][5]=rs.getString("CM");
					data[i][6]=rs.getString("Size");
					data[i][7]=rs.getString("Dozens");	
					data[i][8]=rs.getString("Total2");
					i++;
				}
			j++;
			}
		}
	catch(Exception e)
	{
		System.err.println(e);
	}
	return data;
}
	

/****************************************public void getVoucherData()*********************************************************/

public String[][] getVoucherData(String from_date, String to_date)
{
	String data[][]=null;
	List<Date> dates = new ArrayList<Date>();
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Connection c=null;
	Statement s=null;
	ResultSet rs=null;
	int size=0;
	
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
		s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	} catch (ClassNotFoundException | SQLException e1)
	{
		e1.printStackTrace();
	}
	
	dates=getDaysBetweenDates(from_date,to_date);
	
	for(int i=0;i<dates.size();i++)
	{
	String qur="SELECT * FROM Voucher WHERE SysDate LIKE '"+formatter.format((Date)dates.get(i))+"' AND IS_VOUCHER=1 ORDER BY Bill_No ASC";
			
			try 
			{
				rs=s.executeQuery(qur);
				while(rs.next())
					size++;
				
			} catch (SQLException e)
			{
				System.out.println(e);
			}
	}
	
	System.out.println("Size : "+size);
	data=new String[size][6];
	int i=0,j=0; 
	try
	{
		while(j<dates.size())
		{
		String qur="SELECT * FROM Voucher WHERE SysDate LIKE '"+formatter.format((Date)dates.get(j))+"' AND IS_VOUCHER=1 ORDER BY Bill_No ASC";
		rs=s.executeQuery(qur);
		
			while(rs.next())
			{
			data[i][0]=rs.getString("Bill_No");
			data[i][1]=rs.getString("SysDate");
			data[i][2]=rs.getString("Customer_Name");
			data[i][3]=rs.getString("Particulars");
			data[i][4]=rs.getString("Amount");
			data[i][5]=rs.getString("TotalAmount");		
			i++;
			}
			j++;
			}
		}
	catch(Exception e)
	{
		System.err.println(e);
	}
	return data;
}



/***********************************************getOtherIncomeData()*************************************************/

public String[][] getOtherIncomeData(String from_date, String to_date)
{
	String data[][]=null;
	List<Date> dates = new ArrayList<Date>();
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Connection c=null;
	Statement s=null;
	ResultSet rs=null;
	int size=0;
	
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
		s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
	} catch (ClassNotFoundException | SQLException e1)
	{
		e1.printStackTrace();
	}
	
	dates=getDaysBetweenDates(from_date,to_date);
	
	for(int i=0;i<dates.size();i++)
	{
	   String qur="SELECT * FROM Labourbill WHERE SysDate LIKE '"+formatter.format((Date)dates.get(i))+"' AND IS_LABOUR_BILL=0 ORDER BY Bill_No ASC";
	  
			try 
			{
				rs=s.executeQuery(qur);
				while(rs.next())
					size++;
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
	}
	
	data=new String[size][5];
	int i=0,j=0; 
	try
	{
		
		
		while(j<dates.size())
		{
			String qur="SELECT * FROM Labourbill WHERE SysDate LIKE '"+formatter.format((Date)dates.get(j))+"' AND IS_LABOUR_BILL=0 ORDER BY Bill_No ASC";
			rs=s.executeQuery(qur);
		
		while(rs.next())
		{
		data[i][0]=rs.getString("Bill_No");
		data[i][1]=rs.getString("SysDate");
		data[i][2]=rs.getString("Customer_Name");
		data[i][3]=rs.getString("Particulars");
		data[i][4]=rs.getString("Total2");		
		i++;
		}
		j++;
		}
	}
	catch(Exception e)
	{
		System.err.println(e);
	}
	return data;
}




/***********************************************getOtherExpenseIncomeData()*************************************************/


public String[][] getOtherExpenseData(String from_date, String to_date)
{
	String data[][]=null;
	List<Date> dates = new ArrayList<Date>();
	DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	Connection c=null;
	Statement s=null;
	ResultSet rs=null;
	int size=0;
	
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
		s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	} catch (ClassNotFoundException | SQLException e1)
	{
		e1.printStackTrace();
	}
	
	dates=getDaysBetweenDates(from_date,to_date);
	
	for(int i=0;i<dates.size();i++)
	{
	String qur="SELECT * FROM Voucher WHERE SysDate LIKE '"+formatter.format((Date)dates.get(i))+"' AND IS_VOUCHER=0 ORDER BY Bill_No ASC";
			
			try 
			{
				rs=s.executeQuery(qur);
				while(rs.next())
					size++;
				
			} catch (SQLException e)
			{
				System.out.println(e);
			}
	}
	
	System.out.println("Size : "+size);
	data=new String[size][6];
	int i=0,j=0; 
	try
	{
		while(j<dates.size())
		{
		String qur="SELECT * FROM Voucher WHERE SysDate LIKE '"+formatter.format((Date)dates.get(j))+"' AND IS_VOUCHER=0 ORDER BY Bill_No ASC";
		rs=s.executeQuery(qur);
		
			while(rs.next())
			{
			data[i][0]=rs.getString("Bill_No");
			data[i][1]=rs.getString("SysDate");
			data[i][2]=rs.getString("Customer_Name");
			data[i][3]=rs.getString("Particulars");
			data[i][4]=rs.getString("TotalAmount");		
			i++;
		}
		j++;
		}
	}
	catch(Exception e)
	{
		System.err.println(e);
	}
	return data;
}


/****************************************public void writePdf()*********************************************************/

public void writePdf(String data[][], int flag, String from_date, String to_date)
{
	String file_path=".";
	String Header="";
	JFileChooser chooser = new JFileChooser();
    
    chooser.setDialogTitle("Select Location");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    chooser.setAcceptAllFileFilterUsed(false);
    

    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
    {
    file_path=chooser.getSelectedFile().toString();			    
	FileOutputStream fos=null;
	PdfPTable header_table = new PdfPTable(1);
    PdfPTable data_table=null;
    String col_hed[]=new String[10];
    String bill_info="";
    int column_count=0;
    int span_cols_count=0;
    	try
		{
			Date dNow=new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy@hh-mm-aa");
			
	        Document doc = new Document();
	        fos=new FileOutputStream(file_path+"\\_Report_"+ft.format(dNow)+"_.pdf");
	        PdfWriter.getInstance(doc, fos);
	        doc.open();
	        
	        if(flag==0)
	        {
	        	data_table = new PdfPTable(8);
	        	data_table.setWidths(new int[]{3,6,12,12,5,5,5,5});
	        	col_hed[0]= "Bill No.";
	        	col_hed[1]= "Date";
   	        	col_hed[2]= "Customer Name";
   	        	col_hed[3]= "Particulars";
	        	col_hed[4]= "Rate";
	        	col_hed[5]= "Dozens";
   	        	col_hed[6]= "Amount";
   	        	col_hed[7]= "Total";
   	        	
   	        	bill_info="Labour Bill";
   	        	column_count=8;
   	        	span_cols_count=4;
   	        	
	        }
	        else if(flag==1)
	        {
	        	data_table = new PdfPTable(9);
	        	data_table.setWidths(new int[]{3,6,4,12,12,4,4,5,5});
	        	col_hed[0]= "Bill No.";
	        	col_hed[1]= "Date";
   	        	col_hed[2]= "DC No.";
   	        	col_hed[3]= "Name";
	        	col_hed[4]= "Particulars";
	        	col_hed[5]= "CM";
   	        	col_hed[6]= "Size";
   	        	col_hed[7]= "Dozens";
   	        	col_hed[8]= "Total";	    
  				
   	        	bill_info="Delivery Note";
   	        	column_count=9;
	        }
	        else if(flag==2)
	        {
	        	data_table = new PdfPTable(6);
	        	data_table.setWidths(new int[]{3,5,12,12,5,5});
		        
	        	col_hed[0]= "Bill No.";
	        	col_hed[1]= "Date";
   	        	col_hed[2]= "Customer Name";
   	        	col_hed[3]= "Particulars";
	        	col_hed[4]= "Amount";
	        	col_hed[5]= "Total";
	        	
	        	bill_info="Voucher";
   	        	column_count=6;      	
	        }
	        else if(flag==3)
	        {
	        	data_table = new PdfPTable(5);
	        	data_table.setWidths(new int[]{3,6,12,12,6});
	        	col_hed[0]= "Bill No.";
	        	col_hed[1]= "Date";
   	        	col_hed[2]= "Customer Name";
   	        	col_hed[3]= "Particulars";
   	        	col_hed[4]= "Total";
   	        	
   	        	bill_info="Other Income";
   	        	column_count=5;

	        }
	        else if(flag==4)
	        {
	        	data_table = new PdfPTable(5);
	        	data_table.setWidths(new int[]{3,6,12,12,6});
	        	col_hed[0]= "Bill No.";
	        	col_hed[1]= "Date";
   	        	col_hed[2]= "Customer Name";
   	        	col_hed[3]= "Particulars";
   	        	col_hed[4]= "Total";
   	        	
   	        	bill_info="Other Expense";
   	        	column_count=5;
	        }
            header_table.setWidthPercentage(100);
            data_table.setWidthPercentage(100);
            header_table.getDefaultCell().setPadding(1);
            	
            
            PdfPCell cell1 = new PdfPCell(new Phrase("Bill Info  : "+bill_info, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
            PdfPCell cell3 = new PdfPCell(new Phrase("From      : "+from_date, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
            PdfPCell cell5 = new PdfPCell(new Phrase("To           : "+to_date, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
            
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setBorderWidth(0);
            cell1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
            cell1.setUseDescender(true);
      	
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setBorderWidth(0);
            cell3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
            cell3.setUseDescender(true);
            
            
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setBorderWidth(0);
            cell5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
            cell5.setUseDescender(true);
            
            header_table.addCell(cell1);
            header_table.addCell(cell3);
            header_table.addCell(cell5);
            
       	      
            
            PdfPCell hed[];
            for(int i=0;i<column_count;i++)
            {
                data_table.addCell(new Phrase(col_hed[i],  FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
            }
            
            int size=0;
            List<Integer>[] span_info= getSpanInfo(data);
            size=(column_count*data.length)-((span_cols_count*data.length)-span_cols_count*DISINTINCT_ROWS);
            
            PdfPCell data_cell[] = new PdfPCell[size];
            
            String modified_date[]=modifyData(data,size ,0, span_info);
            
            
            for(int i=0;i<size;i++)
        	{
            	data_cell[i]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	data_cell[i+1]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	data_cell[i+2]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	
            	data_cell[i].setRowspan(span_info[i].size());
            	data_cell[i+1].setRowspan(span_info[i].size());
            	data_cell[i+2].setRowspan(span_info[i].size());
            	
            	for(int j=i+3;j<(span_info[i].size()+i+3);j++)
            	{
            	data_cell[j]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	data_table.addCell(data_cell[j]);
            	data_cell[j+1]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	data_cell[j+2]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	data_cell[j+3]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	}
            	
            	data_cell[i+7]=new PdfPCell(new Phrase(modified_date[i],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            	data_cell[i+7].setRowspan(span_info[i].size());
            	
        	}
            
         /*  for(int i=0;i<data.length;i++)
             {
            	for(int j=0;j<column_count;j++)
            	{
            		data_cell[i]=new PdfPCell(new Phrase(data[i][j],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            		//data_cell[i].addElement(new Phrase(data[i][j],  FontFactory.getFont(FontFactory.HELVETICA, 10)));
            		data_table.addCell(data_cell[i]);
            	}
            }
           */
            doc.add(header_table);
            doc.add(  new Paragraph("\n" ) );
            doc.add(data_table);
            doc.close();
            
            if(fos!=null)
			{
				final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
	
		    	JOptionPane.showMessageDialog(null, "Report Exported to the Path : "+file_path,"Info",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (DocumentException ex) 
		{
			System.out.println(ex);
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
		 		JOptionPane.showMessageDialog(null, "Error!"+ex,"Info",JOptionPane.ERROR_MESSAGE);
	
		}
		catch (FileNotFoundException ex) 
		{
			System.out.println(ex);
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
		 		JOptionPane.showMessageDialog(null, "Error!"+ex,"Info",JOptionPane.ERROR_MESSAGE);
	
		}
    this.dispose();
    }

    
}





/*********************************************************getDatesBetweenDates()***********************************************************/

public static List<Date> getDaysBetweenDates(String startdate, String enddate)
{
	List<Date> dates = new ArrayList<Date>();

	
	DateFormat formatter=null ; 
	Date  endDate=null;
	formatter = new SimpleDateFormat("dd-MM-yyyy");
	Date startDate=null;
	try
	{
		startDate = (Date)formatter.parse(startdate);
		endDate = (Date)formatter.parse(enddate);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	long interval = 24*1000 * 60 * 60; // 1 hour in millis
	long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
	long curTime = startDate.getTime();
	while (curTime <= endTime) {
	    dates.add(new Date(curTime));
	    curTime += interval;
	}
	for(int i=0;i<dates.size();i++){
	    Date lDate =(Date)dates.get(i);
	    String ds = formatter.format(lDate);    
	   
	}
	return dates;
}



/*********************************************************getSpanInfo()***********************************************************/
public List<Integer>[] getSpanInfo(String data[][])
{
	int i=0,j=0,length=data.length;
	int x=0,y=0;

	List<Integer>[] cell = new List[length];
	cell[0]=new ArrayList<Integer>();
	
	lj:for(j=0;;)
	{
		if(j==length)
			break lj;
	
		
	
		if(data[i][0].equals(data[j][0]))
		{
			
			cell[x].add(j);
			y++;
			j++;
		}
		else
		{
			i=j;
			x++;
			cell[x]=new ArrayList<Integer>();			
		}
		
	}
DISINTINCT_ROWS=x+1;
/*
for(i=0;i<x+1;i++)
{	
	for(j=0;j<cell[i].size();j++)
	{
		System.out.print(cell[i].get(j)+" ");
	}
System.out.println();
}
*/
return cell;	
}



/*****************************************************modifyData()**********************************************************/

public String[] modifyData(String data[][],int size,int flag, List<Integer> cell[])
{
	String modified_data[]=new String[size];
	int cell_count=0;

	int data_len=data[0].length;
	System.out.println("CELL LEN :"+cell.length);
	
	for(int i=0;i<DISINTINCT_ROWS;i++)
	{		
		
		for(int j=0;j<cell[i].size();j++) 
		{
			if(j==0)
			{
				for(int k=0;k<data_len;k++)
				{
					modified_data[cell_count]=data[cell[i].get(0)][k];
					cell_count++;
				}
			}
			else
			{
				for(int k=0;k<data_len;k++)
				{
					switch(k)
					{
						case 3:
						case 4:
						case 5:
						case 6:
							modified_data[cell_count]=data[cell[i].get(j)][k];
							cell_count++;
							break;
					}
				}
			}
		}
	}
	
	//System.out.println("size of modified dta :"+modified_data.length +"\nSize :"+size);
	//for(int i=0;i<size;i++)
	//	System.out.println(modified_data[i]);
	return modified_data;
}
}