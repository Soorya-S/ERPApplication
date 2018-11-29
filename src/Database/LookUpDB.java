package Database;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import GUI.SearchResult;
import Printer.NumberToWord;
import Printer.PreviewDeliveryNote;
import Printer.PreviewLabourBill;
import Printer.PreviewTaxInvoice;
import Printer.PreviewVoucher;
import Printer.PrintDeliveryNote;
import Printer.PrintLabourBill;

public class LookUpDB {
	
	Connection c;
	Statement s;
	String qur=" ";
	ResultSet r ;
	
	public LookUpDB(String L,Object O,int flag)
	{
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
			s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e)
		{
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
			System.err.println("Error in Lookup at db declaration : "+e);
		}
		
		if(flag==0)  /*LabourBILL*/
		{
			int i=0;
			String data[]=new String[7];
			Object data2[][]=new Object[20][20];
			try
			{
				qur= "SELECT * FROM Labourbill WHERE Bill_No="+Integer.parseInt(L)+" AND IS_LABOUR_BILL <>0 ORDER BY Si_No ASC";
				r=s.executeQuery(qur);
				r.next();
				data[0]=r.getString("Bill_No");
				data[1]=r.getString("SysDate");
				data[2]=r.getString("Customer_Name");
				data[3]=r.getString("Total1");
				data[4]=r.getString("Total2");	
				r.previous();
	
				while(r.next())
				{
						data2[i][0]=r.getString("Rate");
						data2[i][1] = r.getString("Particulars");
						data2[i][2]=r.getString("Dozens");
						data2[i][3]=r.getString("Amount");
						
						i++;
					
					
					
					
				}
				
				NumberToWord ntw=new NumberToWord();
				int m;
				m=Math.round(Float.parseFloat(data[4]));
				
				data[5]=ntw.convertNumberToWords(m)[0];
				data[6]=ntw.convertNumberToWords(m)[1];
				PreviewLabourBill pb = new PreviewLabourBill(data2,data,i);
				
			}
				catch(SQLException e)
			{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(null,"No data found in  Labour Bill");
			
			}
			
			
		}
		else if(flag==1)
		{
			
			int j=0;
			String data_dn[]=new String[6];
			Object data2_dn[][]=new Object[20][20];
			try
			{
				qur= "SELECT * FROM DeliveryNote WHERE Bill_No="+Integer.parseInt(L)+" ORDER BY Si_No ASC";
				r=s.executeQuery(qur);
				r.next();
				data_dn[0]=r.getString("Bill_No");
			
					data_dn[1]=r.getString("Date");
					data_dn[2]=r.getString("Name");
					data_dn[3]=r.getString("Total1");
					data_dn[4]=r.getString("Total2");	
					
					
					j=0;
					r=s.executeQuery(qur);
					while(r.next())
					{
						data2_dn[j][0]=r.getString("Si_No");
						data2_dn[j][1]=r.getString("Dc_NO");
						data2_dn[j][2]=r.getString("Particulars");
						data2_dn[j][3]=r.getString("CM");
						data2_dn[j][4]=r.getString("Size");
						data2_dn[j][5]=r.getString("Dozens");			
						j++;
						
					}
					
					//System.out.println(j);
					PreviewDeliveryNote pdn = new PreviewDeliveryNote(data2_dn, data_dn,j);					
			}
			catch(SQLException e)
			{
				System.out.println(e);
				final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
				JOptionPane.showMessageDialog(null,"No data found in   delivnote");
			}
		}
			else if(flag==2)
			{
				
				int j=0;
				String data_dn[]=new String[7];
				Object data2_dn[][]=new Object[20][20];
				try
				{
					qur= "SELECT * FROM Voucher WHERE Bill_No="+Integer.parseInt(L) +"AND IS_VOUCHER <>0 ORDER BY S_No ASC";
					r=s.executeQuery(qur);
					r.next();
					data_dn[0]=r.getString("Bill_No");
				
						data_dn[1]=r.getString("SysDate");
						data_dn[2]=r.getString("Customer_Name");
						data_dn[3]=r.getString("TotalAmount");
						
						r.previous();
						

						while(r.next())
						{
	                     
							data2_dn[j][0]=r.getString("S_No");
							data2_dn[j][1] = r.getString("Particulars");
							data2_dn[j][2]=r.getString("Amount");
							j++;
						
						}
						
						
						PreviewVoucher pdn = new PreviewVoucher(data2_dn, data_dn,j);					
				}
				catch(Exception e)
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(null,"No data found in Voucher");
				}

		}
		else if(flag==3)  /*TaxInvoice*/
		{
			int i=0;
			String data[]=new String[14];
			Object data2[][]=new Object[20][20];
			try
			{
				qur= "SELECT * FROM TaxInvoice WHERE Bill_No="+Integer.parseInt(L)+" ORDER BY Si_No ASC";
				r=s.executeQuery(qur);
				r.next();
				
				data[0]=r.getString("Bill_No");
				data[1]=r.getString("date");
				data[2]=r.getString("Customer_Name");
				data[3]=r.getString("Address");
				data[4]=r.getString("gstin");
				data[5]=r.getString("State");
				data[6]=r.getString("State_code");
				data[7]=r.getString("Vehicle_info");
				data[8]=r.getString("Total");
				data[9]=r.getString("sgst");
				data[10]=r.getString("cgst");
				data[11]=r.getString("Grand_Total");
				
				
				

				
				r.previous();
	
				while(r.next())
				{
						data2[i][0]=r.getString("Rate");
						data2[i][1] = r.getString("Particulars");
						data2[i][2]=r.getString("HSN_code");
						data2[i][3]=r.getString("Dozens");
						data2[i][4]=r.getString("Amount");
						
						i++;			
				}
				
				NumberToWord ntw=new NumberToWord();
				int m;
				m=Math.round(Float.parseFloat(data[11]));
				
				data[12]=ntw.convertNumberToWords(m)[0];
				data[13]=ntw.convertNumberToWords(m)[1];
				PreviewTaxInvoice pb = new PreviewTaxInvoice(data2,data,i);
				
			}
				catch(SQLException e)
			{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(SearchResult.getFrames()[0],"No data found in  Tax Invoice");
			
			}
			
			
		}

		
	try {
		c.close();
	} catch (SQLException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
	}//const
	
	
	
	
}//classs
