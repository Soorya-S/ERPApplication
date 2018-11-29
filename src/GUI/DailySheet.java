package GUI;

import Table.CellSpan;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import Database.AccountsDB;




import Database.LookUpDB;
import Printer.PrintLabourBill;
import Printer.PrintTaxInvoice;
import Printer.PrintVoucher;
import Settings.SystemProperty;
import Table.AttributiveCellTableModel;
import Table.MultiSpanCellTable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DailySheet extends JFrame implements MouseListener
{

	/**
	 * Create the panel.
	 */
	private static JTable table;
	public static DefaultTableModel dtm;
	public static JTextField name_field;
	private JPanel panel_1;
	public Object data1[][],data2[][],data3[][],data4[][],data5[][],data6[][],data7[][],data8[][],data9[][],data10[][],data11[][],data12[][],data13[][],data14[][];
	public JComboBox year_combo,month_combo,date_combo;
	private final JButton add_entry;

	public  DailySheet() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10,10,1366,744);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		setTitle("Account manager");
		
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/Resources/images/title.png"));
		label.setBounds(308, 0, 751, 118);
		
		panel_1=new JPanel();
		panel_1.setBounds(0, 0, 1366, 120);
		panel_1.setBackground(Color.WHITE);
		panel_1.add(label);
		
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("src/Resources/images/dailysheet_header.png"));
		lblNewLabel.setBounds(0, 120, 1366, 30);
		getContentPane().add(lblNewLabel);
		  
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "View By", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 161, 195, 513);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("This day");
		btnNewButton.setBounds(53, 40, 89, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				
				for(int u=0;u<table.getRowCount();u++)
			      {
			    	  	dtm.setValueAt("", u, 0);
				 		dtm.setValueAt("", u, 1);
				 		dtm.setValueAt("", u, 2);
				 		dtm.setValueAt("", u, 3);
				 		dtm.setValueAt("", u, 4);
				 		dtm.setValueAt("", u, 5);
				 		dtm.setValueAt("", u, 6);
				 		dtm.setValueAt("", u, 7);
			      }
				Date dNow = new Date();
				SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
				String date=ft.format(dNow);
		        AccountsDB vo = new AccountsDB();
		          data1= vo.funn1(date);
			      data2=vo.funn2(date);
			      int c=0;
			      dtm.setRowCount(data1.length+data2.length);
			      
                for(int j=0;j<vo.count1();j++)
		        {
                	//System.out.println("inside Voucher");
		        dtm.setValueAt(data1[j][0], j, 0);
		 		dtm.setValueAt(data1[j][1], j, 1);
		 		dtm.setValueAt(data1[j][2], j, 2);
		 		dtm.setValueAt(data1[j][3], j, 3);
		 		dtm.setValueAt(data1[j][4], j, 4);
		 		dtm.setValueAt(data1[j][5], j, 5);
		 		dtm.setValueAt(data1[j][6], j, 6);
		 		dtm.setValueAt(data1[j][7], j, 7);
		         c++;
		         
		        }
		         int z1=c;
		      //  System.out.println("count2 :" +vo.count2());
		        for(int z=0;z<vo.count2();z++)
		        {
	                	System.out.println("Z :"+(z1+z));

		        dtm.setValueAt(data2[z][0], z1+z, 0);
		 		dtm.setValueAt(data2[z][1], z1+z, 1);
		 		dtm.setValueAt(data2[z][2], z1+z, 2);
		 		dtm.setValueAt(data2[z][3], z1+z, 3);
		 		dtm.setValueAt(data2[z][4], z1+z, 4);
		 		dtm.setValueAt(data2[z][5], z1+z, 5);
		 		dtm.setValueAt(data2[z][6], z1+z, 6);
		 		dtm.setValueAt(data2[z][7], z1+z, 7);
		        }
		      //  mergeCols(); 
				}
			
			
		});
		
		
		JButton btnNewButton_1 = new JButton("This week");
		btnNewButton_1.setBounds(53, 90, 89, 23);
		panel.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			     
				for(int u=0;u<table.getRowCount();u++)
			      {
			    	  dtm.setValueAt("", u, 0);
				 		dtm.setValueAt("", u, 1);
				 		dtm.setValueAt("", u, 2);
				 		dtm.setValueAt("", u, 3);
				 		dtm.setValueAt("", u, 4);
				 		dtm.setValueAt("", u, 5);
				 		dtm.setValueAt("", u, 6);
				 		dtm.setValueAt("", u, 7);
			      }
				int c1=0;
				
				 Calendar now = Calendar.getInstance();

				    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				    
				    String[] days = new String[7];
				    int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1; //add 2 if your week start on monday
				    now.add(Calendar.DAY_OF_MONTH, delta );
				    for (int i = 0; i < 7; i++)
				    {
				    	
				        days[i] = format.format(now.getTime());
				      //  System.out.println(days[i]);
				        now.add(Calendar.DAY_OF_MONTH, 1);
				    }
				    AccountsDB tw=new AccountsDB();
				    
	
				    data3=new Object[tw.funn3(days).length][10];
				data3=tw.funn3(days);
				data4=tw.funn4(days);
				
				dtm.setRowCount(data3.length+data4.length);
			
				  for(int j=0;j<tw.count3();j++)
			         {
	                
			         dtm.setValueAt(data3[j][0], j, 0);
			 		dtm.setValueAt(data3[j][1], j, 1);
			 		dtm.setValueAt(data3[j][2], j, 2);
			 		dtm.setValueAt(data3[j][3], j, 3);
			 		dtm.setValueAt(data3[j][4], j, 4);
			 		dtm.setValueAt(data3[j][5], j, 5);
			 		dtm.setValueAt(data3[j][6], j, 6);
			 		dtm.setValueAt(data3[j][7], j, 7);
			         c1++;
			         }
				
				  int z1=c1;
			        
			         for(int z2=0;z2<tw.count4();z2++)
			         {
			        
			        dtm.setValueAt(data4[z2][0], z1+z2, 0);
			 		dtm.setValueAt(data4[z2][1], z1+z2, 1);
			 		dtm.setValueAt(data4[z2][2], z1+z2, 2);
			 		dtm.setValueAt(data4[z2][3], z1+z2, 3);
			 		dtm.setValueAt(data4[z2][4], z1+z2, 4);
			 		dtm.setValueAt(data4[z2][5], z1+z2, 5);
			 		dtm.setValueAt(data4[z2][6], z1+z2, 6);
			 		dtm.setValueAt(data4[z2][7], z1+z2, 7);
			 		
			         }
			         
					
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("This Month");
		btnNewButton_2.setBounds(53, 140, 89, 23);
		panel.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int u=0;u<table.getRowCount();u++)
			      {
			    	  dtm.setValueAt("", u, 0);
				 		dtm.setValueAt("", u, 1);
				 		dtm.setValueAt("", u, 2);
				 		dtm.setValueAt("", u, 3);
				 		dtm.setValueAt("", u, 4);
				 		dtm.setValueAt("", u, 5);
				 		dtm.setValueAt("", u, 6);
				 		dtm.setValueAt("", u, 7);
			      }
				int c2=0;
				
				 Calendar now = Calendar.getInstance();
				    int month=now.get(Calendar.MONTH)+1; 
				    int year= now.get(Calendar.YEAR);
				    String m1="0"+Integer.toString(month);
				    String y1=Integer.toString(year);
                     
			        AccountsDB tm = new AccountsDB();
			        dtm.setRowCount(tm.count4());
			    	data5=tm.funn7(m1,y1);
					data6=tm.funn8(m1,y1);
					
				//	System.out.println("this month :"+data5.length+"\t"+data6.length);
					dtm.setRowCount(data5.length+data6.length);
					  for(int j=0;j<tm.count7();j++)
				      {
				        dtm.setValueAt(data5[j][0], j, 0);
				 		dtm.setValueAt(data5[j][1], j, 1);
				 		dtm.setValueAt(data5[j][2], j, 2);
				 		dtm.setValueAt(data5[j][3], j, 3);
				 		dtm.setValueAt(data5[j][4], j, 4);
				 		dtm.setValueAt(data5[j][5], j, 5);
				 		dtm.setValueAt(data5[j][6], j, 6);
				 		dtm.setValueAt(data5[j][7], j, 7);
				        c2++;
				       }
					
					  int z1=c2;
				         //System.out.println("z1: "+z1);
				         for(int z2=0;z2<tm.count8();z2++)
				         {
			                	//System.out.println("Z :"+(z1+z));

				        dtm.setValueAt(data6[z2][0], z1+z2, 0);
				 		dtm.setValueAt(data6[z2][1], z1+z2, 1);
				 		dtm.setValueAt(data6[z2][2], z1+z2, 2);
				 		dtm.setValueAt(data6[z2][3], z1+z2, 3);
				 		dtm.setValueAt(data6[z2][4], z1+z2, 4);
				 		dtm.setValueAt(data6[z2][5], z1+z2, 5);
				 		dtm.setValueAt(data6[z2][6], z1+z2, 6);
				 		dtm.setValueAt(data6[z2][7], z1+z2, 7);
				         }
				         
				       
			        
			        
				
			}
		});
		
		JButton btnNewButton_3 = new JButton("This year");
		btnNewButton_3.setBounds(53, 190, 89, 23);
		panel.add(btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				for(int u=0;u<table.getRowCount();u++)
			      {
			    	  dtm.setValueAt("", u, 0);
				 		dtm.setValueAt("", u, 1);
				 		dtm.setValueAt("", u, 2);
				 		dtm.setValueAt("", u, 3);
				 		dtm.setValueAt("", u, 4);
				 		dtm.setValueAt("", u, 5);
				 		dtm.setValueAt("", u, 6); 
				 		dtm.setValueAt("", u, 7);
			      }
				int c3=0;
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				String yearInString = String.valueOf(year);
		       AccountsDB ty=new AccountsDB();
		      data7= ty.funn5(yearInString);
		      data8=ty.funn6(yearInString);
		      dtm.setRowCount(data7.length+data8.length);
		      for(int j=0;j<ty.count5();j++)
		         {
             	//System.out.println("inside Voucher");
		         dtm.setValueAt(data7[j][0], j, 0);
		 		dtm.setValueAt(data7[j][1], j, 1);
		 		dtm.setValueAt(data7[j][2], j, 2);
		 		dtm.setValueAt(data7[j][3], j, 3);
		 		dtm.setValueAt(data7[j][4], j, 4);
		 		dtm.setValueAt(data7[j][5], j, 5);
		 		dtm.setValueAt(data7[j][6], j, 6);
		 		dtm.setValueAt(data7[j][7], j, 7);
		         c3++;
		         }
			
			  int z1=c3;
		         //System.out.println("z1: "+z1);
		         for(int z2=0;z2<ty.count6();z2++)
		         {
	                	//System.out.println("Z :"+(z1+z));

		         dtm.setValueAt(data8[z2][0], z1+z2, 0);
		 		dtm.setValueAt(data8[z2][1], z1+z2, 1);
		 		dtm.setValueAt(data8[z2][2], z1+z2, 2);
		 		dtm.setValueAt(data8[z2][3], z1+z2, 3);
		 		dtm.setValueAt(data8[z2][4], z1+z2, 4);
		 		dtm.setValueAt(data8[z2][5], z1+z2, 5);
		 		dtm.setValueAt(data8[z2][6], z1+z2, 6);
		 		dtm.setValueAt(data8[z2][7], z1+z2, 7);
		         }
	        
	        
			    
			    
			}
		});
		
	
		
		JPanel sub_panel = new JPanel();
		sub_panel.setBorder(new TitledBorder(null, "Go to", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sub_panel.setBounds(10, 245, 175, 223);
		panel.add(sub_panel);
		sub_panel.setLayout(null);
		
		JLabel lblMo = new JLabel("Year");
		lblMo.setBounds(10, 30, 46, 14);
		sub_panel.add(lblMo);
		
		JLabel lblNewLabel_1 = new JLabel("Month");
		lblNewLabel_1.setBounds(10, 70, 46, 14);
		sub_panel.add(lblNewLabel_1);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 110, 46, 14);
		sub_panel.add(lblDate);
		
		year_combo = new JComboBox();
		year_combo.setModel(new DefaultComboBoxModel(new String[] {"2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050"}));
		year_combo.setBounds(66, 30, 99, 20);
		sub_panel.add(year_combo);
		
		month_combo = new JComboBox();
		month_combo.setModel(new DefaultComboBoxModel(new String[] {"","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		month_combo.setBounds(66, 70, 99, 20);
		sub_panel.add(month_combo);
		
		date_combo = new JComboBox();
		date_combo.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		date_combo.setBounds(66, 110, 99, 20);
		sub_panel.add(date_combo);
		
		JButton btnG = new JButton("Go");
		btnG.setBounds(45, 163, 89, 23);
		sub_panel.add(btnG);
	
		btnG.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				Object y=year_combo.getSelectedItem();
                 String ye=y.toString();
                Object m=month_combo.getSelectedItem();
                String mo=m.toString();
                Object d=date_combo.getSelectedItem();
                String da=d.toString();
                AccountsDB ty=new AccountsDB();
                if((ye!=null||ye!="")&&(mo==null||mo=="")&&(da==null||da==""))
                {
                	for(int u=0;u<table.getRowCount();u++)
  			      {
  			    	  dtm.setValueAt("", u, 0);
  				 		dtm.setValueAt("", u, 1);
  				 		dtm.setValueAt("", u, 2);
  				 		dtm.setValueAt("", u, 3);
  				 		dtm.setValueAt("", u, 4);
  				 		dtm.setValueAt("", u, 5);
  				 		dtm.setValueAt("", u, 6); 
  				 		dtm.setValueAt("", u, 7);
  			      }
                 int b1=0;
 			
 		      data9= ty.funn5(ye);
 		      data10=ty.funn6(ye);
 		      for(int j=0;j<ty.count5();j++)
 		         {
 		         dtm.setValueAt(data9[j][0], j, 0);
 		 		dtm.setValueAt(data9[j][1], j, 1);
 		 		dtm.setValueAt(data9[j][2], j, 2);
 		 		dtm.setValueAt(data9[j][3], j, 3);
 		 		dtm.setValueAt(data9[j][4], j, 4);
 		 		dtm.setValueAt(data9[j][5], j, 5);
 		 		dtm.setValueAt(data9[j][6], j, 6);
 		 		dtm.setValueAt(data9[j][7], j, 7);
 		         b1++;
 		         }
 			
 			  int z1=b1;
 		         for(int z2=0;z2<ty.count6();z2++)
 		         {

 		         dtm.setValueAt(data10[z2][0], z1+z2, 0);
 		 		dtm.setValueAt(data10[z2][1], z1+z2, 1);
 		 		dtm.setValueAt(data10[z2][2], z1+z2, 2);
 		 		dtm.setValueAt(data10[z2][3], z1+z2, 3);
 		 		dtm.setValueAt(data10[z2][4], z1+z2, 4);
 		 		dtm.setValueAt(data10[z2][5], z1+z2, 5);
 		 		dtm.setValueAt(data10[z2][6], z1+z2, 6);
 		 		dtm.setValueAt(data10[z2][7], z1+z2, 7);

 		         }
 	        
                } 
                else if((ye!=null||ye!="")&&(mo!=null||mo!="")&&(da==null||da==""))
                {
                	for(int u=0;u<table.getRowCount();u++)
  			      {
  			    	  dtm.setValueAt("", u, 0);
  				 		dtm.setValueAt("", u, 1);
  				 		dtm.setValueAt("", u, 2);
  				 		dtm.setValueAt("", u, 3);
  				 		dtm.setValueAt("", u, 4);
  				 		dtm.setValueAt("", u, 5);
  				 		dtm.setValueAt("", u, 6);  
  				 		dtm.setValueAt("", u, 7);
  			      }
                	int b2=0;
                	
                	
                	String b=ty.mont(mo);
                	


                	data11=ty.funn7(b,ye);
                	data12=ty.funn8(b,ye);
                	
                	dtm.setRowCount(data11.length+data12.length);
                	for(int j=0;j<ty.count7();j++)
    		        {
    		        dtm.setValueAt(data11[j][0], j, 0);
    		 		dtm.setValueAt(data11[j][1], j, 1);
    		 		dtm.setValueAt(data11[j][2], j, 2);
    		 		dtm.setValueAt(data11[j][3], j, 3);
    		 		dtm.setValueAt(data11[j][4], j, 4);
    		 		dtm.setValueAt(data11[j][5], j, 5);
    		 		dtm.setValueAt(data11[j][6], j, 6);
    		 		dtm.setValueAt(data11[j][7], j, 7);
    		         b2++;
    		         }
    			  
    			  int z1=b2;
    		        for(int z2=0;z2<ty.count8();z2++)
    		        {
    		        dtm.setValueAt(data12[z2][0], z1+z2, 0);
    		 		dtm.setValueAt(data12[z2][1], z1+z2, 1);
    		 		dtm.setValueAt(data12[z2][2], z1+z2, 2);
    		 		dtm.setValueAt(data12[z2][3], z1+z2, 3);
    		 		dtm.setValueAt(data12[z2][4], z1+z2, 4);
    		 		dtm.setValueAt(data12[z2][5], z1+z2, 5);
    		 		dtm.setValueAt(data12[z2][6], z1+z2, 6);
    		 		dtm.setValueAt(data12[z2][7], z1+z2, 7);
    		        }
    	        
  }
                else
                {
                	for(int u=0;u<table.getRowCount();u++)
  			      {
  			    	  dtm.setValueAt("", u, 0);
  				 		dtm.setValueAt("", u, 1);
  				 		dtm.setValueAt("", u, 2);
  				 		dtm.setValueAt("", u, 3);
  				 		dtm.setValueAt("", u, 4);
  				 		dtm.setValueAt("", u, 5);
  				 		dtm.setValueAt("", u, 6); 
  				 		dtm.setValueAt("", u, 7);
  			      }
                	
                String c2=ty.mont(mo);
                data13=ty.funn9(da,c2,ye);
                data14=ty.funn10(da,c2,ye);
                int e3=0;
                	
                for(int j=0;j<ty.count9();j++)
		         {
		         dtm.setValueAt(data13[j][0], j, 0);
		 		dtm.setValueAt(data13[j][1], j, 1);
		 		dtm.setValueAt(data13[j][2], j, 2);
		 		dtm.setValueAt(data13[j][3], j, 3);
		 		dtm.setValueAt(data13[j][4], j, 4);
		 		dtm.setValueAt(data13[j][5], j, 5);
		 		dtm.setValueAt(data13[j][6], j, 6);
		 		dtm.setValueAt(data13[j][7], j, 7);
		         e3++;
		         }
			
			  int z1=e3;	
			  for(int z2=0;z2<ty.count10();z2++)
		         {

		         dtm.setValueAt(data14[z2][0], z1+z2, 0);
		 		dtm.setValueAt(data14[z2][1], z1+z2, 1);
		 		dtm.setValueAt(data14[z2][2], z1+z2, 2);
		 		dtm.setValueAt(data14[z2][3], z1+z2, 3);
		 		dtm.setValueAt(data14[z2][4], z1+z2, 4);
		 		dtm.setValueAt(data14[z2][5], z1+z2, 5);
		 		dtm.setValueAt(data14[z2][6], z1+z2, 6);
		 		dtm.setValueAt(data14[z2][7], z1+z2, 7);
		         }
                	
                	
                	
                	
                	
                	
                	
                }
                
                
                 
				
			}
		});
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(215, 161, 1135, 513);
		getContentPane().add(Table_panel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExpo = new JMenuItem("Export..");
		mnFile.add(mntmExpo);
		
		JMenuItem mntmEx = new JMenuItem("Exit");
		mnFile.add(mntmEx);
		final JFrame f=this;
		mntmEx.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				f.dispose();
			}
		});
		JMenu mnOpti = new JMenu("More..");
		menuBar.add(mnOpti);
		
		JMenuItem mntmO = new JMenuItem("Options");
		mnOpti.add(mntmO);
		
		
		Object[] title={"S.No","Date","Bill / Voucher no.","Name","Particulars","Debit","Credit","Balance"};
		dtm = new DefaultTableModel();
	
		
		dtm.setColumnIdentifiers(title);
		sub_panel.setLayout(null);
		table = new JTable(dtm);
			
		
		dtm.setRowCount(18);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);
		table.getColumnModel().getColumn(4).setPreferredWidth(800);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		
		Table_panel.setLayout(null);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,1135,443);
		
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);
		table.setFont(new Font(table.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE-2));
		
		Table_panel.add(scroll);
		Border border = BorderFactory.createLineBorder(Color.green); 
		table.getTableHeader().setBackground(new Color(150,255,150));
		JTableHeader hed = table.getTableHeader();
		hed.setBorder(border);
		hed.setFont(new Font("Helvetica",Font.BOLD,12));
		hed.setForeground(new Color(33,50,118));
		hed.setReorderingAllowed(false);
		hed.setResizingAllowed(true);
		hed.setDefaultRenderer(new HeaderRenderer(table));
		
		final JFrame ff=this;
		add_entry = new JButton("Add Entry");
		add_entry.setBounds(310, 464, 101, 23);
		Table_panel.add(add_entry);
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(550, 464, 101, 23);
		Table_panel.add(btnClose);
		btnClose.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ff.dispose();
			}
		}
		);
		
		JButton btnPrint = new JButton("Print..");
		btnPrint.setBounds(300, 464, 101, 23);
		//Table_panel.add(btnPrint);
		
		JButton btnExportAsPdf = new JButton("Export as PDF");
		btnExportAsPdf.setBounds(430, 464, 101, 23);
		Table_panel.add(btnExportAsPdf);
		btnExportAsPdf.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				String file_path=".";
				JFileChooser chooser = new JFileChooser();
			    
			    chooser.setDialogTitle("Select Backup Location");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    chooser.setAcceptAllFileFilterUsed(false);
			    

			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			      file_path=chooser.getSelectedFile().toString();			    
				  FileOutputStream fos=null;
						try
						{
							Date dNow=new Date();
						    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy@hh-mm-aa");
								
						      
						
				           Document doc = new Document();
				           fos=new FileOutputStream(file_path+"\\_Report_"+ft.format(dNow)+"_.pdf");
				           PdfWriter.getInstance(doc, fos);
				           
				           doc.open();
				           PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
				           pdfTable.setWidthPercentage(100);
				           pdfTable.setWidths(new int[]{3,6,5,12,12,5,5,5});
				           
				            for (int i = 0; i < table.getColumnCount(); i++) 
				           {
				               pdfTable.addCell(new Phrase(table.getColumnName(i),  FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11)));
				           }
				           
				           
				           PdfPCell data_cell[] = new PdfPCell[table.getRowCount()];
						      int i=0;
				           for (int rows = 0; rows < table.getRowCount() - 1; rows++,i++) 
				           {
				               for (int cols = 0; cols < table.getColumnCount(); cols++) 
				               {
				            	   data_cell[i]=new PdfPCell(new Phrase(table.getModel().getValueAt(rows, cols).toString(), FontFactory.getFont(FontFactory.HELVETICA, 10)));
				                   pdfTable.addCell(data_cell[i]);
				                 
				               }
				           }
				    
				        
				        PdfPTable datatable = new PdfPTable(10);
			            datatable.setWidthPercentage(100);
			            datatable.getDefaultCell().setPadding(5);
			            	
			            PdfPCell cell = new PdfPCell(new Phrase("Report", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
			            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			            cell.setBorderWidth(2);
			            cell.setColspan(10);
			            cell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			            cell.setUseDescender(true);
			      			           
			            
			            datatable.addCell(cell);
				            
			            doc.add(datatable);
			            doc.add(  new Paragraph( "\n" ) );
			           // pdfTable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			          
			            doc.add(pdfTable);
			            doc.close();
			          
			        } catch (DocumentException ex) {
			           // Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			        } catch (FileNotFoundException ex) {
			          //  Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
			        }
			    
				if(fos!=null)
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
		
			    	JOptionPane.showMessageDialog(null, "Report Exported to the Path : "+file_path,"Info",JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
		
				 	JOptionPane.showMessageDialog(null, "Error!. Please Choose correct path!","Info",JOptionPane.ERROR_MESSAGE);
				}
				
			    }
			
			        	
				  	
			}
		});
		
		
							
		table.addMouseListener(this);
		add_entry.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Object[] options = {"Credit","Debit"};
				int n = JOptionPane.showOptionDialog(null,"Select Credit or Debit","Input",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,
						null,options,options[0]);
				
				if(n==0)
				{
					CreditInputDialog c=new CreditInputDialog();
				}
				else if(n==1)
				{
					DebitInputDialog c=new DebitInputDialog();
				}
				
			}
			});
		
		table.repaint();
		pack();
		
		
		
		setVisible(true);
		
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent me)
	{
		int val=0;
		 if (me.getClickCount() == 2) 
	        { 
			 if(table.getValueAt(table.getSelectedRow(), 5).equals("0") && (!table.getValueAt(table.getSelectedRow(), 6).equals("0")))
			 {
				 val=0;
				 PrintLabourBill.LOOK_UP=1;	
			 }
			 else
			 {
				 val=2;
				 PrintVoucher.LOOK_UP=1;				 
			 }
			 try
			 {
				 LookUpDB lb=new LookUpDB( table.getValueAt(table.getSelectedRow(), 2).toString(), null,val );       	
			 }
			 catch(NumberFormatException e)
			 {
				 PrintTaxInvoice.LOOK_UP=1;
				 LookUpDB lb=new LookUpDB( table.getValueAt(table.getSelectedRow(), 2).toString(), null,3);
			 }
			}		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
