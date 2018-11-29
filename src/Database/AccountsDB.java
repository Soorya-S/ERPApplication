package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GUI.DailySheet;

import javax.swing.JOptionPane;

public class AccountsDB
{
	
	Connection c;
	Statement s;
	ResultSet r;
	String qur="";
	protected int flag = 0;
	    protected String d= " ",z=" ",d1=" ",z1=" ",b2="",y1="",b3="",y2="",b4="",y3="",d3="",b5="",y4="",d4="";
	    public  Object data[][],data1[][],data11[][],data2[][],data3[][],data4[][],data5[][],data6[][],data7[][],data8[][],data9[][];
	    public int n,n1,m,v,m1,m2,n2,m3,n3,m4;
	    public float u,z2,u1,u2,u3;
	

		public Object[][] funn1(String d)
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
			this.d=d;
			int i=0,p=0,q=0;
			 
			 try
			 {
				qur= "SELECT * FROM Voucher WHERE SysDate ='"+d+"' ORDER BY Bill_No DESC";  
			    r=s.executeQuery(qur);
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    data=new Object[size][20];
			    r=s.executeQuery(qur);
			    System.out.println("size1:" +size);
	            while(r.next())
				{
					    data[i][0]=i+1;
						data[i][1]=r.getString("SysDate");
						data[i][2]=r.getString("Bill_No");
						data[i][3]=r.getString("Customer_Name");
						data[i][4]=r.getString("Particulars"); 
						data[i][5]=r.getString("Amount");
						data[i][6]="0";
						if(!(data[i][5]==null||(data[i][5].toString().equals(""))))
						{
						q = Integer.parseInt(data[i][5].toString());
						data[i][7]=p-q;
						 p = Integer.parseInt(data[i][7].toString());
						//q = Integer.parseInt(data[i][4].toString());
						//p-=q;
					i++;
				}
				}
				
				n=i;
		       u=p;//amt
		     				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun1 : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data);
	
		
	}
		public Object[][] funn2(String d)
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration at fun2: "+e);
			}
			
			this.d=d;
			int j=0;
			float b;
			
			 try
			{
					qur= "SELECT * FROM Labourbill WHERE SysDate ='"+d+"' ORDER BY Bill_No DESC";  
					r=s.executeQuery(qur);
				    float f=amt();
				    int size=0;
				    
				    while(r.next())
				    	size++;
				    
				    qur= "SELECT * FROM TaxInvoice WHERE date ='"+d+"' ORDER BY Bill_No DESC";  
					r=s.executeQuery(qur);
					while(r.next())
					    	size++;
					
				    data1=new Object[size][20];
				    
				    
				    qur= "SELECT * FROM Labourbill WHERE SysDate ='"+d+"' ORDER BY Bill_No DESC";  
				    r=s.executeQuery(qur);
				   
				    
		            while(r.next())
					{
						
			                data1[j][0]=n+j+1;
							data1[j][1]=r.getString("SysDate");
							data1[j][2]=r.getString("Bill_No");
							data1[j][3]=r.getString("Customer_Name");
							data1[j][4]=r.getString("Particulars"); 
							data1[j][5]="0";
							data1[j][6]=r.getString("Amount");
							if(!(data1[j][6]==null||(data1[j][6].toString().equals(""))))
							{
							data1[j][7]=f+Float.parseFloat(data1[j][6].toString());
							b = Float.parseFloat(data1[j][7].toString());
							f=b;	
							j++;
							}
					}
		            
		            qur= "SELECT * FROM TaxInvoice WHERE date ='"+d+"' ORDER BY Bill_No DESC";  
					r=s.executeQuery(qur);
					while(r.next())
					{
						data1[j][0]=n+j+1;
						data1[j][1]=r.getString("date");
						data1[j][2]=r.getString("Bill_No");
						data1[j][3]=r.getString("Customer_Name");
						data1[j][4]=r.getString("Particulars"); 
						data1[j][5]="0";
						data1[j][6]=r.getString("Amount");
						if(!(data1[j][6]==null||(data1[j][6].toString().equals(""))))
						{
						data1[j][7]=f+Float.parseFloat(data1[j][6].toString())+((Float.valueOf(r.getString("Amount"))/100)*5.0);
						b = Float.parseFloat(data1[j][7].toString());
						f=b;	
						j++;
						}
					}

				m=j;
				
			
				
				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun2 : "+e);
			}
		
	
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data1);
	
		
	}
		
		public Object[][] funn3(String[] d1) //This week voucher
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
			int i=0,p=0,q=0;
			try
			{
				 int size=0;
				 for(int j=0;j<6;j++)
				 {
				 qur="SELECT * FROM Voucher WHERE SysDate LIKE '"+d1[j]+"' ORDER BY Bill_No DESC";
				 r=s.executeQuery(qur);
				 while(r.next())
				    	size++;
				  
				 }
				 
				 data2=new Object[size][10];
				 for(int j=0;j<6;j++)
				 {

				qur="SELECT * FROM Voucher WHERE SysDate LIKE '"+d1[j]+"'";
			     r=s.executeQuery(qur);
			   while(r.next())
				{
					    data2[i][0]=i+1;
						data2[i][1]=r.getString("SysDate");
						data2[i][2]=r.getString("Bill_No");
						data2[i][3]=r.getString("Customer_Name");

						data2[i][4]=r.getString("Particulars"); 
						data2[i][5]=r.getString("Amount");
						if(!(data2[i][5]==null||(data2[i][5].toString().equals(""))))
						{
						q = Integer.parseInt(data2[i][5].toString());
						data2[i][6]="0";
						data2[i][7]=p-q;
						p = Integer.parseInt(data2[i][7].toString());
						
						//p-=q;
					i++;
				}
				}
				}
				v=i;
		   
				z2=p;

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun3 : "+e);
				System.err.println(e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data2);
	
		
	}	
		public Object[][] funn4(String[] d2)//This week
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
			
			
		
			int j=0,j2=0,b2=0;
			float b;
			  float f=amt5();
			  int size=0;
			  
			  
			 try
			 {
				 for(int i=0;i<6;i++)
				 {
				qur= "SELECT * FROM Labourbill WHERE [SysDate] LIKE '"+d2[i]+"' ORDER BY Bill_No DESC";  
			    r=s.executeQuery(qur);
			    while(r.next())
			    	size++;
				 }
			  data3=new Object[size][10];
			 
				 for(int i=0;i<6;i++)
				 {
				qur= "SELECT * FROM Labourbill WHERE [SysDate] LIKE '"+d2[i]+"' ORDER BY Bill_No DESC";  
			    r=s.executeQuery(qur);
			 
			
			  
			   
			   while(r.next())
				{
					
	                 	 data3[j][0]=v+j+1;
						data3[j][1]=r.getString("SysDate");
						data3[j][2]=r.getString("Bill_No");
						data3[j][3]=r.getString("Customer_Name");

						data3[j][4]=r.getString("Particulars"); 
						data3[j][5]="0";
						data3[j][6]=r.getString("Amount");
						if(!(data3[j][6]==null||(data3[j][6].toString().equals(""))))
						{
						data3[j][7]=f+Float.parseFloat(data3[j][6].toString());
						 b = Float.parseFloat(data3[j][7].toString());
						  f=b;	
					j++;
				}
				}
				 }
				m1=j;
			
		    

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun4 : "+e);
			}
			 
		try {
			c.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data3);
	
		
	}
		
		
		public Object[][] funn5(String d)//This year voucher
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
	        this.d=d;
			int i=0,p=0,q=0;
			 try
			{
				 qur="SELECT * FROM Voucher WHERE SysDate LIKE '%-"+d+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
	           
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    data4=new Object[size][20];
			    r=s.executeQuery(qur);
			    
			    while(r.next())
				{
					
		                data4[i][0]=i+1;
						data4[i][1]=r.getString("SysDate");
						data4[i][2]=r.getString("Bill_No");
						data4[i][3]=r.getString("Customer_Name");
						data4[i][4]=r.getString("Particulars"); 
						data4[i][5]=r.getString("Amount");
						if(!(data4[i][5]==null||(data4[i][5].toString().equals(""))))
						{
						q = Integer.parseInt(data4[i][5].toString());

						data4[i][6]="0";
						data4[i][7]=p-q;
						p = Integer.parseInt(data4[i][7].toString());
					
					
					i++;
				}
				}
				
				n1=i;
		       
				u1=p;

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun5 : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data4);
	
		
	}

		public Object[][] funn6(String d1)//This year labourbill
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
			this.d1=d1;
		
			int j=0;
			float b=0;
		
			 try
			{
				qur="SELECT * FROM Labourbill WHERE SysDate LIKE '%-"+d1+"%' ORDER BY Bill_No DESC"; 
			    r=s.executeQuery(qur);
			    float f=amt2();
			   
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    qur="SELECT * FROM TaxInvoice WHERE date LIKE '%-"+d1+"%' ORDER BY Bill_No DESC"; 
			    r=s.executeQuery(qur);
			    while(r.next())
			    	size++;
			    
			    data5=new Object[size][20];
			    qur="SELECT * FROM Labourbill WHERE SysDate LIKE '%-"+d1+"%' ORDER BY Bill_No DESC"; 
			    r=s.executeQuery(qur);
			    
	            while(r.next())
				{
					
	                 	data5[j][0]=n1+j+1;
						data5[j][1]=r.getString("SysDate");
						data5[j][2]=r.getString("Bill_No");
						data5[j][3]=r.getString("Customer_Name");
						data5[j][4]=r.getString("Particulars"); 
						data5[j][5]="0";
						data5[j][6]=r.getString("Amount");
						if(!(data5[j][6]==null||(data5[j][6].toString().equals(""))))
						{
						data5[j][7]=f+Float.parseFloat(data5[j][6].toString());
						b = Float.parseFloat(data5[j][7].toString());
						f=b;	
						j++;
						}
				}
	             
	             qur="SELECT * FROM TaxInvoice WHERE date LIKE '%-"+d1+"%' ORDER BY Bill_No DESC"; 
				    r=s.executeQuery(qur);
				    while(r.next())
					{
	                 	data5[j][0]=n1+j+1;
						data5[j][1]=r.getString("date");
						data5[j][2]=r.getString("Bill_No");
						data5[j][3]=r.getString("Customer_Name");
						data5[j][4]=r.getString("Particulars"); 
						data5[j][5]="0";
						data5[j][6]=r.getString("Amount");
						if(!(data5[j][6]==null||(data5[j][6].toString().equals(""))))
						{
						data5[j][7]=f+Float.parseFloat(data5[j][6].toString())+((Float.valueOf(r.getString("Amount"))/100)*5.0);
						b = Float.parseFloat(data5[j][7].toString());
						f=b;	
						j++;
						}
					}
				m2=j;
		      

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun6 : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data5);
	
		
	}
		
		public Object[][] funn7(String b2,String y1)//this month voucher
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
			this.b2=b2;
			this.y1=y1;
		
			int i=0,p=0,q=0;
			
			 try
			{
				
			    qur="SELECT * FROM Voucher WHERE [SysDate] LIKE '%-"+b2+"-"+y1+"%' ORDER BY Bill_No DESC";
				 r=s.executeQuery(qur);
				    int size=0;
				    
				    while(r.next())
				    	size++;
				    data6=new Object[size][20];
				    r=s.executeQuery(qur);

	             while(r.next())
				{
					
		                data6[i][0]=i+1;
						data6[i][1]=r.getString("SysDate");
						data6[i][2]=r.getString("Bill_No");
						data6[i][3]=r.getString("Customer_Name");
						data6[i][4]=r.getString("Particulars"); 
						data6[i][5]=r.getString("Amount");
						if(!(data6[i][5]==null||(data6[i][5].toString().equals(""))))
						{
						q = Integer.parseInt(data6[i][5].toString());

						data6[i][6]="0";
						data6[i][7]=p-q;
						p = Integer.parseInt(data6[i][7].toString());
						
					
					i++;
				}
				}
				
				n2=i;
		       
				u2=p;

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun5 : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data6);
	
		
	}
		
		
		public Object[][] funn8(String b3,String y2)//this month labourbill
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			
			this.b3=b3;
			this.y2=y2;
		
			int j=0;
			float b;
			
			 try
			{
				 
				qur="SELECT * FROM Labourbill WHERE [SysDate] LIKE '%-"+b3+"-"+y2+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    float f=amt3();
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    qur="SELECT * FROM TaxInvoice WHERE [date] LIKE '%-"+b3+"-"+y2+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    while(r.next())
			    	size++;
			    
			    data7=new Object[size][20];
			    qur="SELECT * FROM Labourbill WHERE [SysDate] LIKE '%-"+b3+"-"+y2+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    
			    
	            while(r.next())
				{
					
	                 	data7[j][0]=n2+j+1;
						data7[j][1]=r.getString("SysDate");
						data7[j][2]=r.getString("Bill_No");
						data7[j][3]=r.getString("Customer_Name");
						data7[j][4]=r.getString("Particulars"); 
						data7[j][5]="0";
						data7[j][6]=r.getString("Amount");
						if(!(data7[j][6]==null||(data7[j][6].toString().equals(""))))
						{
						data7[j][7]=f+Float.parseFloat(data7[j][6].toString());
						b = Float.parseFloat(data7[j][7].toString());
						f=b;	
						j++;
						}
				}
	            qur="SELECT * FROM TaxInvoice WHERE [date] LIKE '%-"+b3+"-"+y2+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    
			    
	            while(r.next())
				{
	            	data7[j][0]=n2+j+1;
					data7[j][1]=r.getString("date");
					data7[j][2]=r.getString("Bill_No");
					data7[j][3]=r.getString("Customer_Name");
					data7[j][4]=r.getString("Particulars"); 
					data7[j][5]="0";
					data7[j][6]=r.getString("Amount");
					if(!(data7[j][6]==null||(data7[j][6].toString().equals(""))))
					{
					data7[j][7]=f+Float.parseFloat(data7[j][6].toString())+((Float.valueOf(r.getString("Amount"))/100)*5.0);;
					b = Float.parseFloat(data7[j][7].toString());
					f=b;	
					j++;
					}
				}
				m3=j;
		       

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun6 : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data7);
	
		
	}
		
		public Object[][] funn9(String d3,String b4,String y3)//particular date
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			this.d3=d3;
			this.b4=b4;
			this.y3=y3;
		
			int i=0,p=0,q=0;
			
			 try
			 {
				qur="SELECT * FROM Voucher WHERE [SysDate] LIKE '%"+d3+"-"+b4+"-"+y3+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    data8=new Object[size][20];
			    r=s.executeQuery(qur);

	             while(r.next())
				{
					
		                data8[i][0]=i+1;
						data8[i][1]=r.getString("SysDate");
						data8[i][2]=r.getString("Bill_No");
						data8[i][3]=r.getString("Customer_Name");
						data8[i][4]=r.getString("Particulars"); 
						data8[i][5]=r.getString("Amount");
						if(!(data8[i][5]==null||(data8[i][5].toString().equals(""))))
						{
						q = Integer.parseInt(data8[i][5].toString());

						data8[i][6]="0";
						data8[i][7]=p-q;
						p = Integer.parseInt(data8[i][7].toString());
					
					
					
					i++;
				}
				}
				
				n3=i;
		       
				u3=p;

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun5 : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data8);
	
		
	}	
		public Object[][] funn10(String d4,String b5,String y4)
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Lookup at Accountdb declaration : "+e);
			}
			this.d4=d4;
			this.b5=b5;
			this.y4=y4;
		
			int j=0;
			float b;
			
			 
			try
			{
				qur="SELECT * FROM Labourbill WHERE [SysDate] LIKE '%"+d4+"-"+b5+"-"+y4+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    float f=amt4();
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    qur="SELECT * FROM TaxInvoice WHERE [date] LIKE '%"+d4+"-"+b5+"-"+y4+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			    while(r.next())
			    	size++;
			    
			    data9=new Object[size][20];
			    qur="SELECT * FROM Labourbill WHERE [SysDate] LIKE '%"+d4+"-"+b5+"-"+y4+"%' ORDER BY Bill_No DESC";
			    r=s.executeQuery(qur);
			   
			    
	            while(r.next())
				{
					
	                 	data9[j][0]=n3+j+1;
						data9[j][1]=r.getString("SysDate");
						data9[j][2]=r.getString("Bill_No");
						data9[j][3]=r.getString("Customer_Name");
						data9[j][4]=r.getString("Particulars"); 
						data9[j][5]="0";
						data9[j][6]=r.getString("Amount");
						if(!(data9[j][6]==null||(data9[j][6].toString().equals(""))))
						{
						data9[j][7]=f+Float.parseFloat(data9[j][6].toString());
					 b = Float.parseFloat(data9[j][7].toString());
					  f=b;	
					j++;
				}
				}
	            qur="SELECT * FROM TaxInvoice WHERE [date] LIKE '%"+d4+"-"+b5+"-"+y4+"%' ORDER BY Bill_No DESC";
				    r=s.executeQuery(qur);
				    while(r.next())
					{
				    	data9[j][0]=n3+j+1;
						data9[j][1]=r.getString("date");
						data9[j][2]=r.getString("Bill_No");
						data9[j][3]=r.getString("Customer_Name");
						data9[j][4]=r.getString("Particulars"); 
						data9[j][5]="0";
						data9[j][6]=r.getString("Amount");
						if(!(data9[j][6]==null||(data9[j][6].toString().equals(""))))
						{
						data9[j][7]=f+Float.parseFloat(data9[j][6].toString())+((Float.valueOf(r.getString("Amount"))/100)*5.0);
						b = Float.parseFloat(data9[j][7].toString());
						f=b;	
						j++;
						}
					}
				m4=j;
		       

				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in AccountsDB inside fun10: "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(data9);
	
		
	}
		

	
		public int count1()
		{
			
			return n;
			
		}

		public int count2()
		{
			
			return m;
			
		}      

       public float amt()
       {
    	   return u;
       }
       public int count3()
       {
    	   return v;
       }
		public float amt1()
		{
			return z2;
		}
		public int count4()
		{
			return m1;
		}
		public int count5()
		{
			return n1;
		}
		public int count6()
		{
			return m2;
		}
	public float amt2()
	{
		return u1;
	}
	public float amt3()
	{
		return u2;
	}
	public int count7()
	{
		return n2;
	}
	public int count8()
	{
		return m3;
	}
	public int count9()
	{
		return n3;
	}
	public float amt4()
	{
		return u3;
	}
	public int count10()
	{
		return m4;
	}
	public float amt5()
	{
		return z2;
	}
	public String mont(String m)
	{
		String a=" ";
		if(m=="January")
    	{
    		a="01";
    	}
    	else if(m=="February")
    	{
    		a="02";
    	}
    	else if(m=="March")
    	{
    		a="03";
    	}
    	else if(m=="April")
    	{
    		a="04";
    	}
    	else if(m=="May")
    	{
    		a="05";
    	}
    	else if(m=="June")
    	{
    		a="06";
    	}
    	else if(m=="July")
    	{
    		a="07";
    	}
    	else if(m=="August")
    	{
    		a="08";
    	}
    	else if(m=="September")
    	{
    		a="09";
    	}
    	else if(m=="October")
    	{
    		a="10";
    	}
    	else if(m=="November")
    	{
    		a="11";
    	}
    	
    	else
    	{
    		a="12";
    	}
		return a;
	}
}
