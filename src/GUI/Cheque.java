package GUI;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Font;

import javax.swing.UIManager;

import Printer.NumberToWord;
import Printer.PrintCheque;
import Printer.PrintImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;

public class Cheque extends JPanel implements KeyListener,ActionListener
{
	static JTextField date_text;
	static JTextField rs_text;
	static JTextField name_text;
	static JLabel rupees_1 ,rupees_2;
    JComboBox comboBox ;
    static Date dNow;
    static SimpleDateFormat ft;

    static JButton btnClear,btnPrint;
	public Cheque() 
	{
		setSize(1106,566);
		setLayout(null);
		JLabel label1 = new JLabel("");
		label1.setBounds(0, 0, 1106, 76);
		label1.setIcon(new ImageIcon("src/Resources/images/Cheque.png"));
		add(label1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(168, 108, 750, 246);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblBankName = new JLabel("Bank Name :");
		lblBankName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBankName.setBounds(35, 35, 100, 14);
		panel.add(lblBankName);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"State Bank of India", "HDFC Bank", "Kotack Mahindra Bank"}));
		comboBox.setBounds(145, 33, 218, 20);
		panel.add(comboBox);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDate.setBounds(492, 35, 46, 14);
		panel.add(lblDate);
		
		dNow = new Date();
	    ft = new SimpleDateFormat ("dd-MM-yyyy");

		date_text = new JTextField();
		date_text.setBounds(535, 32, 99, 20);
		date_text.setText(ft.format(dNow));
		panel.add(date_text);
		date_text.setColumns(10);
		
		JLabel lblddmmyyyy = new JLabel("(dd-mm-yyyy)");
		lblddmmyyyy.setBounds(644, 35, 79, 14);
		panel.add(lblddmmyyyy);
		
		JLabel lblPay = new JLabel("PAY :");
		lblPay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPay.setBounds(85, 94, 31, 14);
		panel.add(lblPay);
		
		JLabel lblNewLabel = new JLabel("------------------------------------------------------------------------------------------------------------------------------");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(85, 108, 648, 14);
		panel.add(lblNewLabel);
		
		JLabel lblRupees = new JLabel("Rupees :");
		lblRupees.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRupees.setBounds(84, 150, 51, 14);
		panel.add(lblRupees);
		
		JLabel label = new JLabel("--------------------------------------------------------------------------------------");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(85, 161, 453, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("------------------------------------------------------------------------------------------------------------------------------");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(85, 196, 638, 14);
		panel.add(label_1);
		
		JLabel lblRs = new JLabel("Rs.");
		lblRs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRs.setBounds(535, 150, 31, 14);
		panel.add(lblRs);
		
		rs_text = new JTextField();
		rs_text.setBounds(566, 148, 110, 20);
		panel.add(rs_text);
		rs_text.setColumns(10);
		
		name_text = new JTextField();
		name_text.setFont(new Font("Tahoma", Font.BOLD, 13));
		name_text.setBackground(UIManager.getColor("Button.background"));
		name_text.setBounds(126, 92, 586, 20);
		name_text.setBorder(new EmptyBorder(0,0,0,0));
		panel.add(name_text);
		name_text.setColumns(10);
		
		rupees_1 = new JLabel("");
		rupees_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		rupees_1.setBounds(145, 151, 367, 14);
		panel.add(rupees_1);
		
		rupees_2 = new JLabel("");
		rupees_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		rupees_2.setBounds(85, 186, 627, 14);
		panel.add(rupees_2);
		
		name_text.requestFocusInWindow();
		btnClear = new JButton("Clear");
		
		btnClear.setBounds(565, 393, 89, 23);
		btnClear.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_D, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		
		add(btnClear);
		
		
		btnPrint = new JButton("Print..");
		btnPrint.registerKeyboardAction( this, KeyStroke.getKeyStroke( KeyEvent.VK_P, KeyEvent.CTRL_MASK ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		
		btnClear.addActionListener(this);
		btnPrint.addActionListener(this);
		
		btnPrint.setBounds(427, 393, 89, 23);
		add(btnPrint);
		rs_text.addKeyListener(this);
	}
	
	public static void clearFields()
	{
		name_text.setText("");
		date_text.setText(ft.format(dNow));
		rupees_1.setText("");
		rupees_2.setText("");
		rs_text.setText("");
		name_text.requestFocus();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		if(arg0.getKeyChar()==KeyEvent.VK_ENTER)
		{
			NumberToWord ntw=new NumberToWord();
			String text=ntw.convertNumberToWords(Integer.parseInt(rs_text.getText()), 0);
			rupees_1.setText(tokenizer(text)[0]);
			rupees_2.setText(tokenizer(text)[1]);
			
		}
	}
	

    public String[] tokenizer(String m)
    {
    	int flag=1;
    	String a,c="",d="";
    	StringTokenizer obj = new StringTokenizer(m+" Rupees Only"," ");
    	while(obj.hasMoreTokens())
    	{
              a= obj.nextToken()+" ";
              if(((c+a).length())>45)
              {
            	  flag=0;
            	  d+= a;
              }
              else
              {
            	  if(flag==1)
            	  {
            		  c+= a;
            	  }
            	  else
            	  {
            		  d+=a;
            	  }
              }
        }
	String tokens[]=new String[2];
	tokens[0]=c;
	tokens[1]=d;
	return tokens;
	
	
    }

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		if(arg0.getSource()==btnClear)
		{
			name_text.setText("");
			date_text.setText(ft.format(dNow));
			rupees_1.setText("");
			rupees_2.setText("");
			rs_text.setText("");
			name_text.requestFocus();
		}
		
		else if(arg0.getSource()==btnPrint)
			{
				String data[]=new String[5];
				
				StringBuilder sb=null;
				
			
				if(date_text.getText()!=null && (!date_text.getText().equals("")))
				{
					sb=new StringBuilder(date_text.getText());
				}
				
				if(sb!=null)
				{
				sb.deleteCharAt(2);
				sb.deleteCharAt(4);
				String space="";
				
				if(comboBox.getSelectedIndex()==0)
				{
					sb.insert(1,"   ");
					sb.insert(5,"   ");
					sb.insert(9,"   ");
					sb.insert(13,"   ");
					sb.insert(17,"   ");
					sb.insert(21,"   ");
					sb.insert(25,"   ");
					sb.trimToSize();
				}
				else if(comboBox.getSelectedIndex()==1)
				{
					sb.insert(1,"   ");
					sb.insert(5,"   ");
					sb.insert(9,"   ");
					sb.insert(13,"   ");
					sb.insert(17,"   ");
					sb.insert(21,"   ");
					sb.insert(25,"   ");
					sb.trimToSize();
				}
				else if(comboBox.getSelectedIndex()==2)
				{
					sb.insert(1,"    ");
					sb.insert(6,"    ");
					sb.insert(11,"    ");
					sb.insert(16,"    ");
					sb.insert(21,"    ");
					sb.insert(26,"    ");
					sb.insert(31,"    ");
					sb.trimToSize();
				}
				}
				System.out.println("date : "+sb);
				if(sb!=null)
					data[0]=sb.toString();
				else
					data[0]="";
				data[1]=name_text.getText();
				data[2]=rs_text.getText();
				data[3]=rupees_1.getText();
				data[4]=rupees_2.getText();
				PrintImage.REF=3;
				PrintCheque p=new PrintCheque(data,comboBox.getSelectedIndex()) ;
			}
		
	}
 
}
