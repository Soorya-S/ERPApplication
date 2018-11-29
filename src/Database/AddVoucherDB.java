package Database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

public class AddVoucherDB extends JFrame {

	private JPanel contentPane;
	private JTextField nametextField;
	private JTextField itemtextField_1;
	private JTextField amt;
	JDateChooser dateChooser;


	public AddVoucherDB() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 614, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(189, 11, 46, 14);
		contentPane.add(label);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 578, 33);
		contentPane.add(panel1);
		
		JLabel lblNewLabel = new JLabel("Add Entry to the Voucher Bill");
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		panel1.add(lblNewLabel);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 55, 284, 323);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel name = new JLabel("Customer Name");
		name.setBounds(10, 35, 96, 14);
		panel2.add(name);
		
		nametextField = new JTextField();
		nametextField.setBounds(140, 32, 86, 20);
		panel2.add(nametextField);
		nametextField.setColumns(10);
		
		JLabel items = new JLabel("Particulars");
		items.setBounds(10, 102, 86, 14);
		panel2.add(items);
		
		itemtextField_1 = new JTextField();
		itemtextField_1.setBounds(140, 99, 86, 20);
		panel2.add(itemtextField_1);
		itemtextField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setBounds(10, 165, 46, 14);
		panel2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setBounds(10, 234, 46, 14);
		panel2.add(lblNewLabel_2);
		
		amt = new JTextField();
		amt.setBounds(140, 228, 86, 20);
		panel2.add(amt);
		amt.setColumns(10);
		
		JButton add = new JButton("Add");
		add.setBounds(140, 289, 89, 23);
		panel2.add(add);
		
	  dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(140, 165, 91, 20);
		panel2.add(dateChooser);
		
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(304, 55, 284, 323);
		contentPane.add(panel3);
		panel3.setLayout(null);
		
		final JLabel showmsg = new JLabel("        Confirmation");
		showmsg.setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 13));
		showmsg.setBounds(34, 11, 203, 67);
		panel3.add(showmsg);
		
		
		
		JButton closebtn = new JButton("close");
		closebtn.setBounds(158, 289, 89, 23);
		panel3.add(closebtn);
		closebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String b1=nametextField.getText().toString();
				String b2=itemtextField_1.getText().toString();
				String b3=((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				String b4=amt.getText().toString();
			if((b1==null||b1.equals(""))||(b2==null||b2.equals(""))||(b3==null||b3.equals(""))||(b4==null||b4.equals("")))
			{
				JOptionPane.showMessageDialog(null,"All Fields are Required","Fill the values",JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				
				Connection c;
				Statement s = null;
				ResultSet r;
				String qur="";
				try
				{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					c = DriverManager.getConnection("jdbc:odbc:Billingsystem");
					s =c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				}catch(Exception e1)
				{
					System.err.println("Error in AddDB database declaration : "+e1);
				}
			        
					try
					{
					qur="INSERT INTO Voucher VALUES("+1+","+0+",'"+b1+"','"+b3+"','"+b2+"','"+(Integer.parseInt(b4))+"','"+0+"')";
					int rb=s.executeUpdate(qur);
					if(rb==1)
					{
						
						showmsg.setText("<html>Entry added to the<br>Voucher Successfully!<html>");
						nametextField.setText("");
						itemtextField_1.setText("");
						amt.setText("");
						((JTextField)dateChooser.getDateEditor().getUiComponent()).setText("");
					}
					else
					{
						showmsg.setText("<html>Check the fields are<br>in proper manner<html>");
					}
				}
				
				catch(NumberFormatException e1){
						JOptionPane.showMessageDialog(null,"Invalid Data","Insertion",JOptionPane.ERROR_MESSAGE);
						
					}  
					catch(Exception a)
					{
						JOptionPane.showMessageDialog(null,"error: "+a,"Insertion",JOptionPane.ERROR_MESSAGE);
					}
			        
				}	
				
			}
		});
		
		
		final JFrame ff=this;
			closebtn.addActionListener(new ActionListener() 
			{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ff.dispose();
			}
			}
			);
		
setLocationRelativeTo(null);	
	}
	

}
