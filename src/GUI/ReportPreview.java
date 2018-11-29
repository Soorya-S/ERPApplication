package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;

import Table.AttributiveCellTableModel;
import Table.CellSpan;
import Table.MultiSpanCellTable;

import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReportPreview extends JFrame
{
 
	//JTable table;
	DefaultTableModel dtm;
	JPanel table_panel;
	private JButton btnExport;
	private JButton btnCancel;
	private JLabel Heading;
	
	AttributiveCellTableModel ml=null;
	CellSpan cellAtt=null; 
	MultiSpanCellTable table=null ;
	
	public ReportPreview()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setSize(777, 619);
		setLocationRelativeTo(null);	
		table_panel=new JPanel();
		table_panel.setLayout(null);
		table_panel.setBounds(10,30,750,500);
		
		
		
		getContentPane().add(table_panel);
		
		btnExport = new JButton("Export..");
		btnExport.setBounds(280, 540, 89, 23);
		getContentPane().add(btnExport);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(407, 540, 89, 23);
		getContentPane().add(btnCancel);
		
		Heading = new JLabel("");
		Heading.setForeground(new Color(0, 0, 139));
		Heading.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Heading.setBounds(10, 6, 741, 17);
		Heading.setHorizontalAlignment(SwingUtilities.CENTER);
		getContentPane().add(Heading);
	}
	
	
	public void generateLabourReport(String from,String to)
	{
		int s_num=0;
		Connection c=null;
		Statement s=null;
		ResultSet rs=null;
		int size=0;
		
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
			s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
	
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error in DataBaseConnection : "+e);
		}
		
		try
		{
			
		String qur="select * from Labourbill";
		
		rs=s.executeQuery(qur);
	
		while(rs.next())
		{
			size++;			
		}
		
		rs.first();
		
		
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		
			Object data[][]=new Object[size][7];
		 Object column[]={"S.no","Voucher Number","Date","Customer name","Particulars","Amount","Total"};
	     ml=  new AttributiveCellTableModel(column, size) ;
	     
	     cellAtt =(CellSpan)ml.getCellAttribute();
	     
	     table = new MultiSpanCellTable( ml );
	     table.setRowHeight(25);
	     table.setBounds(0,0,750,500);
	     table.setIntercellSpacing(new Dimension(0,1));
	 		JScrollPane pane = new JScrollPane(table);
	 		table_panel.add(pane);
		pane.setBounds(0,0,740,500);
		
	     table.getColumnModel().getColumn(0).setPreferredWidth(50);
	     table.getColumnModel().getColumn(1).setPreferredWidth(100);
	     table.getColumnModel().getColumn(2).setPreferredWidth(100);
	     table.getColumnModel().getColumn(3).setPreferredWidth(200);
	     table.getColumnModel().getColumn(4).setPreferredWidth(300);
	     table.getColumnModel().getColumn(5).setPreferredWidth(100);
	     table.getColumnModel().getColumn(6).setPreferredWidth(100);
	    
	     try
	     {
	    	 int i=0;
	    	while(rs.next())
	    	{
	    		s_num++;
	    		data[i][0]=s_num;
	    		data[i][1]=rs.getString("Bill_No");
	    		data[i][2]=rs.getString("SysDate");
	    		data[i][3]=rs.getString("Customer_Name");
	    		data[i][4]=rs.getString("Particulars");
	    		data[i][5]=rs.getString("Amount");
	    		data[i][6]=rs.getString("Total2");
	    		i++;
			}
	     }catch(SQLException e)
	     {
	    	 System.out.println(e);
	     }
	     
	     for(int i=0;i<size;i++)
	     {
	    	 for(int j=0;j<7;j++)
		     {
	    		 ml.setValueAt(data[i][j], i,j);   	    		
		     }
	     }
	     
	     int combine[]=new int[size];
	     int k=0;
	     System.out.println("size :"+size);
	     for(int i=0;i<size-1;i++)
	     {
	    	// System.out.println(data[i][1]);
	    	 for(int j=i+1;j<size-1;j++)
		     {
	    		 	
	    		  	if(data[i][1].toString().equals(data[j][1].toString()))
	    		  	{
	    		  		combine[k]=i;
	    		  		combine[k+1]=j;
	    		  		k+=2;
	    		  	}
	    		 	
		     }
	     }
	     
	     int []rows=new int[size];
	     
	     int l=0;
	     for(k=0;k<size-1;k++)
	    	 {
	    	 	if(combine[k]!=0)
	    	 	{
	    	 		rows[l]=combine[k];
	    	 		l++;
	    	 	}
	    	 }
	    		 
	     int[] columns = {1};
	    
	     cellAtt.combine(rows,columns);
	    
}
}



