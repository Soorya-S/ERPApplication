package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Database.LookUpDB;
import Settings.SystemProperty;

public class SearchResult extends JFrame implements MouseListener
{
	Connection c;
	Statement s;
	String qur="";
	ResultSet rs=null;
	public static JTable table;
	public static DefaultTableModel dtm;
	public int ROWS=10;
	public SearchResult()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setAlwaysOnTop(true);
		setSize(780,400);
		setLocationRelativeTo(null);
		setTitle("Search Result");
		try
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
		s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException | ClassNotFoundException e)
		{
		JOptionPane.showMessageDialog(null,"Error in DataBaseConnection : "+e);
		}
		
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(0, 0,765,400);
		getContentPane().add(Table_panel);
		
		Object[] title={"Bill no.","Date","Customer Name","Total"};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(title);
		Table_panel.setLayout(null);
		table = new JTable(dtm);
		
		JTextField textField = new JTextField();
	
		textField.setFont(new Font(table.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE-2));
		textField.setBorder(new LineBorder(Color.BLACK));
		
		
		DefaultCellEditor dce = new DefaultCellEditor( textField );
		table.getColumnModel().getColumn(0).setCellEditor(dce);
		table.getColumnModel().getColumn(1).setCellEditor(dce);
		table.getColumnModel().getColumn(2).setCellEditor(dce);
		table.getColumnModel().getColumn(3).setCellEditor(dce);


		dtm.setRowCount(ROWS);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(800);
		table.getColumnModel().getColumn(3).setPreferredWidth(400);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,765,400);
		
		table.setFillsViewportHeight(true);
		table.setFont(new Font(table.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE-2));
		table.setRowHeight(30);
		
        	
		Border border = BorderFactory.createLineBorder(Color.green); 
		table.getTableHeader().setBackground(new Color(150,255,150));
		JTableHeader hed = table.getTableHeader();
		hed.setBorder(border);
		hed.setFont(new Font("Helvetica",Font.BOLD,12));
		hed.setForeground(new Color(33,50,118));
		hed.setReorderingAllowed(false);
		
		
		hed.setDefaultRenderer(new HeaderRenderer(table));
		
		
		table.setSelectionForeground(Color.BLACK);
		table.setSelectionBackground(Color.WHITE);
		table.addMouseListener(this);
		Table_panel.add(scroll);

		setVisible(true);
		
	}

	
	int sel;
	public void searchByName(String name, int selection)
	{
		sel=selection;
		StringTokenizer st=new StringTokenizer(name," ");
		String tokens[]=new String[st.countTokens()];
		int i=0;
		String table_name="";
		String col_name="";
		String si_no="";
		if(selection==0)
		{
			table_name="Labourbill";
			col_name="Customer_Name";
			si_no="Si_No";
		}
		else if(selection==1)
		{
			table_name="DeliveryNote";
			col_name="Name";
			si_no="Si_No";
		}
		else if(selection==2)
		{
			table_name="Voucher";
			col_name="Customer_Name";
			si_no="S_No";
		}
		else if(selection==3)
		{
			table_name="TaxInvoice";
			col_name="Customer_Name";
			si_no="Si_No";
		}
		
		while(st.hasMoreTokens())
		{
			tokens[i]=st.nextToken();
			i++;
		}
		
		String qur_tok="LIKE '%";
		
		for(i=0;i<tokens.length-1;i++)
		{
			qur_tok+=tokens[i]+"%' OR "+col_name+" LIKE '%";
		}
		qur_tok+=tokens[tokens.length-1]+"%'";
		
		System.out.println("qur tok : "+qur_tok);
		
		String qur="select * from "+table_name+" where "+col_name+" "+qur_tok+" ORDER BY Bill_No ASC";
		System.out.println(qur);
		int count=0;
		try 
		{
			rs=s.executeQuery(qur);
			
			while(rs.next())
				count++;
			
			dtm.setRowCount(count);
			rs=s.executeQuery(qur);
				
			if(selection==0)
			{
				while(rs.next())
				{
					if(rs.getString(si_no).equals("1"))
					{
						dtm.setValueAt(rs.getString(2).toString(), i, 0);
						dtm.setValueAt(rs.getString(8).toString(), i, 1);
						dtm.setValueAt(rs.getString(3).toString(), i,2);
						dtm.setValueAt(rs.getString(9).toString(), i, 3);
						i++;
					}
				}
			}
			else if(selection==1)
			{
				while(rs.next())
				{
					if(rs.getString(si_no).equals("1"))
					{
					dtm.setValueAt(rs.getString(1).toString(), i, 0);
					dtm.setValueAt(rs.getString(5).toString(), i, 1);
					dtm.setValueAt(rs.getString(3).toString(), i,2);
					dtm.setValueAt(rs.getString(11).toString(), i, 3);
					i++;
					}
				}
			}
			else if(selection==2)
			{
				while(rs.next())
				{
					if(rs.getString(si_no).equals("1"))
					{
						dtm.setValueAt(rs.getString(2).toString(), i, 0);
						dtm.setValueAt(rs.getString(4).toString(), i, 1);
						dtm.setValueAt(rs.getString(3).toString(), i,2);
						dtm.setValueAt(rs.getString(7).toString(), i, 3);
						i++;
					}
				}
			}
			else if(selection==3)
			{
				while(rs.next())
				{
					if(rs.getString(si_no).equals("1"))
					{
						dtm.setValueAt(rs.getString(2).toString(), i, 0);
						dtm.setValueAt(rs.getString(3).toString(), i, 1);
						dtm.setValueAt(rs.getString(4).toString(), i,2);
						dtm.setValueAt(rs.getString(18).toString(), i, 3);
						i++;
					}
				}
			}
		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//Object[] title={"Bill no.","Date","Customer Name","Total"};
		
	}
	
	@Override
	public void mouseClicked(MouseEvent me)
	{
		
		 
		
			// int row = table.rowAtPoint(arg0.getPoint());
			//    int col = table.columnAtPoint(arg0.getPoint());
			    
			    
		
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
		 
	        if (me.getClickCount() == 2) 
	        {
	        	//System.out.println("Double clicked on row:"+table.getSelectedRow());
	        	
	        	LookUpDB lb=new LookUpDB( table.getValueAt(table.getSelectedRow(), 0).toString(), null,sel );       	
	    		
	        }	
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
