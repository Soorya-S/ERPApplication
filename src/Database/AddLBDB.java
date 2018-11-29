package Database;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.toedter.calendar.JDateChooser;

public class AddLBDB extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField particulars;
	private JTextField rate;
	private JTextField Dozens;
	private JTextField amt;
	JDateChooser datechoose;
	JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public AddLBDB() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblAddEntriesTo = new JLabel("Add Entries to Labour Bill");
		lblAddEntriesTo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		lblAddEntriesTo.setBounds(110, 11, 250, 14);
		contentPane.add(lblAddEntriesTo);
		
		JLabel lblNewLabel_1 = new JLabel("Customer_Name");
		lblNewLabel_1.setBounds(10, 52, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(110, 49, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Particulars");
		lblNewLabel_2.setBounds(10, 92, 57, 14);
		contentPane.add(lblNewLabel_2);
		
		particulars = new JTextField();
		particulars.setBounds(110, 89, 86, 20);
		contentPane.add(particulars);
		particulars.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Rate");
		lblNewLabel_3.setBounds(10, 172, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Dozens");
		lblNewLabel_4.setBounds(10, 208, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Amount");
		lblNewLabel_5.setBounds(10, 237, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		rate = new JTextField();
		rate.setBounds(110, 172, 86, 20);
		contentPane.add(rate);
		rate.setColumns(10);
		
		Dozens = new JTextField();
		Dozens.setBounds(110, 203, 86, 20);
		contentPane.add(Dozens);
		Dozens.setColumns(10); 
		
		amt = new JTextField();
		amt.setBounds(110, 234, 86, 20);
		contentPane.add(amt);
		amt.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(219, 36, -8, 215);
		contentPane.add(separator);
		
		JLabel lblNewLabel_6 = new JLabel("Date");
		lblNewLabel_6.setBounds(10, 141, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JPanel panel = new JPanel();
		panel.setBounds(219, 172, 205, 79);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setBounds(10, 30, 71, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				String a2=name.getText().toString();
				String a3=particulars.getText().toString();
				String a4=Dozens.getText().toString();
			   String a5=amt.getText().toString();
			  String a6=rate.getText().toString();
			    String a7=((JTextField)datechoose.getDateEditor().getUiComponent()).getText();
			    if((a2==null||a2.equals(""))||(a3==null||a3.equals(""))||(a4==null||a4.equals(""))||(a5==null||a5.equals(""))||(a6==null||a6.equals("")||(a7==null||a7.equals(""))))
				{
					JOptionPane.showMessageDialog(null,"All Fields are Required","Fill the values",JOptionPane.ERROR_MESSAGE);
					
				}
			    else
			    {
				//System.out.println("\n"+a1+"\n"+a2+"\n"+a3+"\n"+a4+"\n"+a5+"\n"+a6+"\n"+a7);
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
					qur="INSERT INTO ManualLB VALUES("+1+",'"+a2+"','"+a3+"','"+a7+"','"+(Integer.parseInt(a6))+"','"+(Integer.parseInt(a4))+"','"+(Integer.parseInt(a5))+"')";
					//System.out.println(qur);
					int rb=s.executeUpdate(qur);
					if(rb==1)
					{
						lblNewLabel.setText("<html>Entry added to the<br>LabourBill Successfully!<html>");
						name.setText("");
						particulars.setText("");
						Dozens.setText("");
						amt.setText("");
						rate.setText("");
						((JTextField)datechoose.getDateEditor().getUiComponent()).setText("");
					}
					else
					{
						lblNewLabel.setText("<html>Check the fields are<br>in proper manner<html>");
					}
				}catch(NumberFormatException e1){
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
		panel.setLayout(null);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CLOSE");
		btnNewButton_1.setBounds(106, 30, 89, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ff.dispose();
			}
		}
		);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(219, 50, 205, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		 lblNewLabel = new JLabel("       Confirmation");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(10, 11, 185, 95);
		panel_1.add(lblNewLabel);
		
		datechoose = new JDateChooser();
		datechoose.setDateFormatString("dd-MM-yyyy");
		datechoose.setBounds(110, 141, 91, 20);
		contentPane.add(datechoose);
		setLocationRelativeTo(null);
	}
}
