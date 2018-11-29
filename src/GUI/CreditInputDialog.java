package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import Settings.SystemProperty;


public class CreditInputDialog extends JDialog implements ActionListener
{

	public static JTable table;
	public static DefaultTableModel dtm;
	JButton add,clear,cancel;
	JDialog frame;
	public CreditInputDialog() 
	{
		setAlwaysOnTop(true);
		setSize(780,284);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setTitle("Credit Input");
		
		JPanel Table_panel = new JPanel();
		Table_panel.setBounds(0, 0,765,179);
		getContentPane().add(Table_panel);
		
		Object[] title={"Bill no.","Date","Customer Name","Particulars","Total"};
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(title);
		Table_panel.setLayout(null);
		table = new JTable(dtm);
		
		JTextField textField = new JTextField();
	
		textField.setFont(new Font(table.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE-2));
		textField.setBorder(new LineBorder(Color.BLACK));
		
		table.getColumnModel().getColumn(1).setCellEditor(new JDateChooserEditor(new JCheckBox()));
			
		DefaultCellEditor dce = new DefaultCellEditor( textField );
		
		table.getColumnModel().getColumn(0).setCellEditor(dce);
		
		table.getColumnModel().getColumn(2).setCellEditor(dce);
		table.getColumnModel().getColumn(3).setCellEditor(dce);
		table.getColumnModel().getColumn(4).setCellEditor(dce);
		
		dtm.setRowCount(5);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setPreferredWidth(350);
		table.getColumnModel().getColumn(2).setPreferredWidth(900);
		table.getColumnModel().getColumn(3).setPreferredWidth(1400);
		table.getColumnModel().getColumn(4).setPreferredWidth(300);
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(0,0,765,179);
		
		table.setFillsViewportHeight(true);
		table.setFont(new Font(table.getFont().getName(),SystemProperty.TABLE_FONT_TYPE,SystemProperty.TABLE_FONT_SIZE-2));
		table.setRowHeight(30);
		

		
		 JDateChooser date;
		 date = new JDateChooser();
		 date.setDateFormatString("dd-MM-yyyy");
		 date.setBounds(10,100,20,20);
		
		 
		Border border = BorderFactory.createLineBorder(Color.green); 
		table.getTableHeader().setBackground(new Color(150,255,150));
		JTableHeader hed = table.getTableHeader();
		hed.setBorder(border);
		hed.setFont(new Font("Helvetica",Font.BOLD,12));
		hed.setForeground(new Color(33,50,118));
		hed.setReorderingAllowed(false);
		hed.setResizingAllowed(false);
		
		hed.setDefaultRenderer(new HeaderRenderer(table));
		
		
		table.setSelectionForeground(Color.BLACK);
		table.setSelectionBackground(Color.WHITE);
		
		Table_panel.add(scroll);
	
		add = new JButton("Add");
		add.setBounds(208, 199, 89, 30);
		getContentPane().add(add);
		
		clear = new JButton("Clear");
		clear.setBounds(327, 199, 89, 30);
		getContentPane().add(clear);
		
		cancel= new JButton("Close");
		cancel.setBounds(443, 199, 89, 30);
		getContentPane().add(cancel);
		
		frame=this;
		clear.addActionListener(this);
		cancel.addActionListener(this);
		add.addActionListener(this);
		 
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource()==add)
		{
			Connection c=null;
			Statement s=null;
			String qur="";
			int RESULT=0;
			try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
					s =c.createStatement();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Error in DataBaseConnection : "+e);
				}

			
			
			if(getDataRows()!=0)
				table.editCellAt(table.getSelectedRow(), table.getSelectedColumn());
			
			System.out.println(getDataRows());
			Object data[][]=new Object[getDataRows()][table.getColumnCount()];
		
			for (int i = 0; i < getDataRows(); i++)
			{
				for(int j = 0; j < table.getColumnCount(); j++) 
			    {
					if(dtm.getValueAt(i, j)==null || dtm.getValueAt(i, j).equals(""))
					{
						data[i][j]=0;
					}
					else
					{
						data[i][j]=dtm.getValueAt(i, j);
					}
			    }
			}

			//System.out.println(data.length);
			
			for(int i=0;i<data.length;i++)
			{
				System.out.println("in qur!"+i);
				try
				{
				qur="INSERT INTO Labourbill VALUES("+(i+1)+",'"+data[i][0]+"','"+data[i][2].toString()+"','"+((data[i][3]).toString())+"','"+(data[i][4].toString())+"','"+(data[i][4].toString())+"','"+(data[i][4].toString())+"','"+data[i][1]+"','"+data[i][4]+"','"+data[i][4]+"',0)";
				System.out.println(qur);
				RESULT=s.executeUpdate(qur);
				System.out.println("result :"+RESULT);
				}
				catch(SQLException cc)
				{
					JOptionPane.showMessageDialog(frame, "Error While Storing data to the database - "+cc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception d)
				{
					JOptionPane.showMessageDialog(frame, "Some fields Need Corrections. Please Enter only valid data! - "+d, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			System.out.println("before result");
			if(RESULT!=0)
			{
				JOptionPane.showMessageDialog(frame, "Data Stored Successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
				table.editCellAt(10, 10);
				for(int i=0;i<5;i++)
					for(int j=0;j<5;j++)
					{
						dtm.setValueAt("", i, j);
					}
			}
			else
				JOptionPane.showMessageDialog(frame, "Error!", "Info", JOptionPane.ERROR_MESSAGE);
			
			
			try 
			{
				c.close();
				System.out.println("closing connection");
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		else if(arg0.getSource()==clear)
		{
		
			table.editCellAt(10, 10);
			for(int i=0;i<5;i++)
				for(int j=0;j<5;j++)
				{
					dtm.setValueAt("", i, j);
				}
		}
		else if(arg0.getSource()==cancel)
		{
			frame.dispose();
		}
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
	
}






