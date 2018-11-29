package Database;

import java.sql.*;

import javax.swing.JOptionPane;

import GUI.LabourBill;

public class DataBase 
{
	Connection c;
	Statement s;
	String qur="";
	public int RESULT=0;
	
	
	 public DataBase(Object data1[][],String data2[],int flag)
	 {
		 RESULT=0;
		  try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement();
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"Error in DataBaseConnection : "+e);
			}

		if(flag==0)
		{
	
			for(int i=0;i<data1.length;i++)
			{
				try
				{
				qur="INSERT INTO Labourbill VALUES("+(i+1)+",'"+data2[0]+"','"+data2[2]+"','"+((data1[i][1]).toString())+"','"+(data1[i][0].toString())+"','"+(data1[i][2].toString())+"','"+(data1[i][3].toString())+"','"+data2[1]+"','"+data2[4]+"','"+data2[3]+"',1)";
				System.out.println(qur);
				RESULT=s.executeUpdate(qur);
				}
				catch(SQLException c)
				{
					JOptionPane.showMessageDialog(null, "Error While Storing data to the database - "+c, "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception d)
				{
					JOptionPane.showMessageDialog(null, "Some fields Need Corrections. Please Enter only valid data! - "+d, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
		else if (flag==1) 
		{
			for(int i=0;i<data1.length;i++)
			{
				try
				{
				qur="INSERT INTO DeliveryNote VALUES('"+data2[0]+"','"+data1[i][0].toString()+"','"+data2[2]+"','"+data1[i][1].toString()+"','"+data2[1]+"','"+(data1[i][2].toString())+"','"+data1[i][3].toString()+"','"+data1[i][4].toString()+"','"+data1[i][5].toString()+"','"+data2[3]+"','"+data2[4]+"')";
				RESULT=s.executeUpdate(qur);
				}
				catch(SQLException c)
				{
					JOptionPane.showMessageDialog(null, "Error While Storing data to the database - "+c, "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception d)
				{
					JOptionPane.showMessageDialog(null, "All Fields are Required! - "+d, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		else if(flag==2)
		{
			for(int i=0;i<data1.length;i++)
			{
				try
				{
					qur="INSERT INTO Voucher VALUES('"+data1[i][0].toString()+"','"+data2[0]+"','"+data2[2]+"','"+data2[1]+"','"+(data1[i][1].toString())+"','"+data1[i][2].toString()+"','"+data2[3]+"',1)";
					RESULT=s.executeUpdate(qur);
					System.out.println("RESULT: "+RESULT);
				}
				catch(SQLException c)
				{
					JOptionPane.showMessageDialog(null, "Error While Storing data to the database - "+ c, "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception d)
				{
					JOptionPane.showMessageDialog(null, "All Fields are Required! - "+d, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		else if(flag==3)
		{
			for(int i=0;i<data1.length;i++)
			{
				try
				{
					qur="INSERT INTO TaxInvoice VALUES('"+(i+1)+"',"+data2[0]+",'"+data2[1]+"','"+data2[2]+"','"+data2[3]+"','"+data2[4]+"','"+data2[5]+"','"+data2[6]+"','"+data2[7]+"','"+data1[i][0]+"','"+data1[i][1]+"','"+data1[i][2]+"','"+data1[i][3]+"','"+data1[i][4]+"','"+data2[8]+"','"+data2[9]+"','"+data2[10]+"','"+data2[11]+"') ";
					System.out.println(qur);
					RESULT=s.executeUpdate(qur);
				}
				catch(SQLException c)
				{
					JOptionPane.showMessageDialog(null, "Error While Storing data to the database - "+ c, "Error", JOptionPane.ERROR_MESSAGE);
				//System.out.println(c);
				}
				catch(Exception d)
				{
				//	System.out.println(d);
					JOptionPane.showMessageDialog(null, "All Fields are Required! - "+d, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
		try
		{
			c.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Exception while colosing Database connection");
		}
	 }
	 
	 
	 
}
