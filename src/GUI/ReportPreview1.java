package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Settings.SystemProperty;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class ReportPreview1 extends JFrame {
	Connection c,c1,c2;
	Statement s,s1,s2;
	ResultSet r,r1,r2;
	String qur="",qur1="",qur2="";
	protected int flag = 0;
	    protected String d= " ";
	    public  Object data3[][];
	    public int n;
	    public float u;
	    private JTable table;
		public static DefaultTableModel dtm;
		private JPanel panel_1;
	//	private final JButton btnNewButton_4 = new JButton("New button");
		 
		public ReportPreview1(){
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(10,10,1366,744);
			setExtendedState(JFrame.MAXIMIZED_BOTH); 
			
			setTitle("Preview");
			
			getContentPane().setLayout(null);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("src/Resources/images/title.png"));
			label.setBounds(308, 0, 751, 118);
			JPanel Table_panel = new JPanel();
			Table_panel.setBounds(215, 161, 1135, 513);
			getContentPane().add(Table_panel);
			
			panel_1=new JPanel();
			panel_1.setBounds(0, 0, 1366, 120);
			panel_1.setBackground(Color.WHITE);
			panel_1.add(label);
			
			getContentPane().add(panel_1);
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon("src/Resources/images/dailysheet_header.png"));
			lblNewLabel.setBounds(0, 120, 1366, 30);
			getContentPane().add(lblNewLabel);
			  
			
			
			
			
			Object[] title={"S.No","Date","Bill / Voucher.no.","Name","Particulars","Total","Amount"};
			dtm = new DefaultTableModel();
			dtm.setColumnIdentifiers(title);
			//sub_panel.setLayout(null);
			table = new JTable(dtm);
			table.setEnabled(false);
			
			dtm.setRowCount(18);
			
			table.getColumnModel().getColumn(0).setPreferredWidth(90);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setPreferredWidth(500);
			table.getColumnModel().getColumn(4).setPreferredWidth(500);
			
			table.getColumnModel().getColumn(5).setPreferredWidth(200);
			table.getColumnModel().getColumn(6).setPreferredWidth(200);
			
			Table_panel.setLayout(null);
			JScrollPane scroll = new JScrollPane(table);
			scroll.setBounds(0,0,1135,443);
			
			table.setFillsViewportHeight(true);
			table.setRowHeight(25);
			table.setFont(new Font(table.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE));
			
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

		
			JButton btnClose = new JButton("Close");
			btnClose.setBounds(530, 464, 101, 23);
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
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Document document = new Document(PageSize.A3.rotate());
				    try {
				      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("e:\\jTable.pdf"));

				      document.open();
				      PdfContentByte cb = writer.getDirectContent();

				      cb.saveState();
				      Graphics2D g2 = cb.createGraphicsShapes(4500, 500);

				      Shape oldClip = g2.getClip();
				      g2.clipRect(0, 0, 1200, 500);

				      table.print(g2);
				      g2.setClip(oldClip);

				      g2.dispose();
				      cb.restoreState();
				    } catch (Exception e1) {
				      System.err.println(e1.getMessage());
				    }
				    document.close();
				}
			});
			
			
			pack();
			
			
			setVisible(true);
			
		}
		

		public Object[][] Lb(String from,String to,String type)
		{

		try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Reportpreview1 declaration : "+e);
			}
			
			String from1="",to1="",type1="";
			from1=from;
			to1=to;
			type1=type;
			
			
			int j=1;
			 
			 try
			{
				qur= "SELECT * FROM "+type1+" WHERE SysDate BETWEEN '"+from1+"' AND '"+to1+"'";  
			
			    r=s.executeQuery(qur);
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    data3=new Object[size+1][20];
			    r=s.executeQuery(qur);
			    System.out.println("size1:" +size);
	             while(r.next())
				{
					
	            	 data3[j][0]=j;
	            
						data3[j][1]=r.getString("SysDate");
						data3[j][2]=r.getString("Bill_No");
						data3[j][3]=r.getString("Customer_Name");

						data3[j][4]=r.getString("Particulars"); 
						
						data3[j][5]=r.getString("Amount");
						data3[j][6]=r.getString("Amount");
						j++;
						
				}
				
				n=j;
		      // u=p;//amt
		      // System.out.println("n :"+n);
				//System.out.println(u);
	             dtm.setRowCount(data3.length);
	             dtm.setValueAt("FROM", 0, 1);
	             dtm.setValueAt(from1, 0, 2);
	             dtm.setValueAt("TO", 0, 3);
	             dtm.setValueAt(to1, 0, 4);
	             dtm.setValueAt("BILL_TYPE", 0, 5);
	             dtm.setValueAt(type1, 0, 6);
	             
	             for(int j1=1;j1<n;j1++){
	            	 dtm.setValueAt(data3[j1][0], j1, 0);
	 		 		dtm.setValueAt(data3[j1][1], j1, 1);
	 		 		dtm.setValueAt(data3[j1][2], j1, 2);
	 		 		dtm.setValueAt(data3[j1][3], j1, 3);
	 		 		dtm.setValueAt(data3[j1][4], j1, 4);
	 		 		dtm.setValueAt(data3[j1][5], j1, 5);
	 		 		dtm.setValueAt(data3[j1][6], j1, 6);
	 		 	
	             
	             }
				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in reportpreview inside lb : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return(data3);
		}	
		
		public Object[][] Dn(String from,String to,String type)
		{

			try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Reportpreview1 declaration : "+e);
			}
			
			String from1="",to1="",type1="";
			from1=from;
			to1=to;
			type1=type;
		
			
		
			int j=1;
			 
			 try
			{
				qur= "SELECT * FROM "+type1+" WHERE Date BETWEEN '"+from1+"' AND '"+to1+"'";  
			
			    r=s.executeQuery(qur);
			    int size=0;
			    
			    while(r.next())
			    	size++;
			    data3=new Object[size+1][20];
			    r=s.executeQuery(qur);
			    System.out.println("size1:" +size);
	             while(r.next())
				{
					
	            	 data3[j][0]=j;
	            
						data3[j][1]=r.getString("Date");
						data3[j][2]=r.getString("Bill_No");
						data3[j][3]=r.getString("Name");

						data3[j][4]=r.getString("Particulars"); 
						
						data3[j][5]=r.getString("Total2");
						data3[j][6]=r.getString("Total2");
						j++;
						
				}
				
				n=j;
		      // u=p;//amt
		      // System.out.println("n :"+n);
				//System.out.println(u);
	             dtm.setRowCount(data3.length);
	             dtm.setValueAt("FROM", 0, 1);
	             dtm.setValueAt(from1, 0, 2);
	             dtm.setValueAt("TO", 0, 3);
	             dtm.setValueAt(to1, 0, 4);
	             dtm.setValueAt("BILL_TYPE", 0, 5);
	             dtm.setValueAt(type1, 0, 6);
	             
	             for(int j1=1;j1<n;j1++){
	            	 dtm.setValueAt(data3[j1][0], j1, 0);
	 		 		dtm.setValueAt(data3[j1][1], j1, 1);
	 		 		dtm.setValueAt(data3[j1][2], j1, 2);
	 		 		dtm.setValueAt(data3[j1][3], j1, 3);
	 		 		dtm.setValueAt(data3[j1][4], j1, 4);
	 		 		dtm.setValueAt(data3[j1][5], j1, 5);
	 		 		dtm.setValueAt(data3[j1][6], j1, 6);
	 		 	
	             
	             }
				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in reportpreview inside lb : "+e);
			}
		
		
		
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return(data3);
		}	
		
		
		
		public Object[][] Cr(String from,String to,String type)
		{

		try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				c1 = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s1 =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				c2 = DriverManager.getConnection("jdbc:odbc:Billingsystem");
				s2 =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}catch(Exception e)
			{
				System.err.println("Error in Reportpreview1 declaration : "+e);
			}
			
			String from1="",to1="",type1="";
			from1=from;
			to1=to;
			type1=type;
		
			
		
			int j=0;
			 
			 try
			{
				qur= "SELECT * FROM Labourbill WHERE SysDate BETWEEN '"+from1+"' AND '"+to1+"'"; 
				qur1= "SELECT * FROM Voucher WHERE SysDate BETWEEN '"+from1+"' AND '"+to1+"'"; 
				qur2= "SELECT * FROM DeliveryNote WHERE Date BETWEEN '"+from1+"' AND '"+to1+"'"; 
			
			    r=s.executeQuery(qur);
			    r1=s1.executeQuery(qur1);
			    		r2=s2.executeQuery(qur2);
			    int size=0,size1=0,size2=0,Size=0;
			    
			    while(r.next())
			    	size++;
			    while(r1.next())
			    	size1++;
			    while(r2.next())
			    	size2++;
			    Size=size+size1+size2;
			    data3=new Object[Size][20];
			    r=s.executeQuery(qur);
			    r1=s1.executeQuery(qur1);
			    r2=s2.executeQuery(qur2);
			    System.out.println("size1:" +Size);
	             while(r.next())
				{
					
	            	 data3[j][0]=j+1;
	            
						data3[j][1]=r.getString("SysDate");
						data3[j][2]=r.getString("Bill_No");
						data3[j][3]=r.getString("Customer_Name");

						data3[j][4]=r.getString("Particulars"); 
						
						data3[j][5]=r.getString("Amount");
						data3[j][6]=r.getString("Amount");
						j++;
						
				}
	             while(r1.next())
					{
						
		            	 data3[j][0]=j;
		            
							data3[j][1]=r.getString("SysDate");
							data3[j][2]=r.getString("Bill_No");
							data3[j][3]=r.getString("Customer_Name");

							data3[j][4]=r.getString("Particulars"); 
							
							data3[j][5]=r.getString("Amount");
							data3[j][6]=r.getString("Amount");
							j++;
							
					}
	             while(r2.next())
					{
						
		            	 data3[j][0]=j;
		            
							data3[j][1]=r.getString("Date");
							data3[j][2]=r.getString("Bill_No");
							data3[j][3]=r.getString("Name");

							data3[j][4]=r.getString("Particulars"); 
							
							data3[j][5]=r.getString("Total2");
							data3[j][6]=r.getString("Total2");
							j++;
							
					}
					
				
				n=j;
		      // u=p;//amt
		      // System.out.println("n :"+n);
				//System.out.println(u);
	             dtm.setRowCount(data3.length);
	             
	             
	             for(int j1=0;j1<n;j1++)
	             {
	            	 dtm.setValueAt(data3[j1][0], j1, 0);
	 		 		dtm.setValueAt(data3[j1][1], j1, 1);
	 		 		dtm.setValueAt(data3[j1][2], j1, 2);
	 		 		dtm.setValueAt(data3[j1][3], j1, 3);
	 		 		dtm.setValueAt(data3[j1][4], j1, 4);
	 		 		dtm.setValueAt(data3[j1][5], j1, 5);
	 		 		dtm.setValueAt(data3[j1][6], j1, 6);
	 		 
	             }
				
			}catch(SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error in reportpreview inside lb : "+e);
			}
		
		
		
		try {
			c.close();
			c1.close();
			c2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return(data3);
		}	
	}
	


