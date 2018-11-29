package Printer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import GUI.LabourBill;

public class PreviewLabourBill extends JFrame implements ActionListener
{
	
static int rows;

	/**
	 * Create the frame.
	 */
 JLabel label1;
// public JLabel label[][];
 public JLabel tmp[]=new JLabel[5];
 public int LENGTH=45;
 JPanel panel;
 JButton print,cancel;
 Object tmp_data[][];
 String tmp_data2[];
public static JFrame FRAME=null;

 	public PreviewLabourBill(Object data[][],String data2[],int r) 
	{
 		setAlwaysOnTop(true);
 		tmp_data=data;
		tmp_data2=data2;
		FRAME=this;
		 
 		JLabel label[][]=new JLabel[data.length][4];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(434, 670);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,420 ,595);
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/LABOUR_BILL.png"));
		label1.setBounds(0, 0, 418, 592);
		rows=LabourBill.getDataRows();
		int y=218;

		if(rows==0)
			rows=r;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<4;j++)
			{
				if(data[i][j]!=null)
				{
					label[i][j]=new JLabel();
					label[i][j].setText(data[i][j].toString());					
				}
				else
				{
					label[i][j]=new JLabel();
					label[i][j].setText("");
				}
			}
			
		}
		
		
		for(int i=0;i<rows;i++)
		{
			if(label[i][1].getText().length()>LENGTH)
			{
			label[i][0].setBounds(10,y,40,30);
			label[i][1].setText("<html>"+tokenizer(label[i][1].getText())[0]+tokenizer(label[i][1].getText())[1]+"</html>");
			label[i][1].setBounds(55,y,230,30);
			
			label[i][2].setBounds(290,y,60,30);
			label[i][3].setBounds(348,y,60,30);
			
			label[i][0].setVerticalAlignment(SwingConstants.TOP);
			label[i][1].setVerticalAlignment(SwingConstants.TOP);
			label[i][2].setVerticalAlignment(SwingConstants.TOP);
			label[i][3].setVerticalAlignment(SwingConstants.TOP);
			y=y+15;
			}
			else
			{
				label[i][0].setBounds(10,y,40,20);
				label[i][1].setBounds(55,y,230,20);
				label[i][2].setBounds(290,y,60,20);
				label[i][3].setBounds(348,y,60,20);
			}
			
			
			label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][1].setHorizontalAlignment(SwingConstants.LEFT);
			label[i][2].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][3].setHorizontalAlignment(SwingConstants.CENTER);
			y=y+28;
		}
		
		for(int i=0;i<rows;i++)
			for(int j=0;j<4;j++)
				{
				panel.add(label[i][j]);
				}
	
		
		
		JLabel date,name,t1,t2,no,total_text,total_text2;
		no=new JLabel(data2[0]);
		date=new JLabel(data2[1]);
		name=new JLabel(data2[2]);
		t1=new JLabel(data2[3]);
		t2=new JLabel(data2[4]);
		total_text=new JLabel("");
		total_text2=new JLabel("");
		
		if(data2[5]==null || data2[5].equals(""))
		{
			total_text.setText("Zero Rupees");
		}
		else if(  data2[6].equals("") || data2[6].equals(null))
		{	
			total_text.setText(data2[5]+" Rupees");
		}
		else
		{
			total_text.setText(data2[5]);
			total_text2.setText(data2[6]+" Rupees");
		}
		no.setBounds(42,119,40,20);
		date.setBounds(321,119,70,20);
		name.setBounds(62,137,330,20);
		t1.setBounds(292,503,55,20);
		t2.setBounds(352,503,55,20);
		total_text.setBounds(60,534,300,20);
		total_text2.setBounds(25,553,300,20);
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		total_text.setHorizontalAlignment(SwingConstants.LEFT);
		
		panel.add(no);
		panel.add(date);
		panel.add(name);
		panel.add(t1);
		panel.add(t2);
		panel.add(total_text);
		panel.add(total_text2);
		panel.add(label1);
		
		
		
		print =new JButton("Print");
		print.setBounds(130,597,89, 28);
		getContentPane().add(print);
		
		cancel=new JButton("close");
		cancel.setBounds(240,597,89, 28);
		getContentPane().add(cancel);
		
		getContentPane().add(panel);
		
		print.addActionListener(this);
		cancel.addActionListener(this);
		
		setVisible(true);
		setLocationRelativeTo(null);
		
	}
 	
 	
	
	private String[] tokenizer(String m)
    {
		int flag=1;
    	String a,c="",d="";
    	StringTokenizer obj = new StringTokenizer(m," ");
    	while(obj.hasMoreTokens())
    	{
              a= obj.nextToken()+" ";
              
              if(((c+a).length())>LENGTH)
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
	System.out.println(c+"\n"+d);
	return tokens;
    }
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==print)	
		{
			PrintLabourBill plb=new PrintLabourBill(tmp_data,tmp_data2,rows);
		}
		else
		{
			this.dispose();
		}
	}
}
