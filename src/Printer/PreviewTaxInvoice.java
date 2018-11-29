package Printer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.TaxInvoice;

public class PreviewTaxInvoice extends JFrame implements ActionListener
{

	
static int rows;

	/**
	 * Create the frame.
	 */
 JLabel label1;
// public JLabel label[][];
 public JLabel tmp[]=new JLabel[5];
 public int LENGTH=35;
 JPanel panel;
 JButton print,cancel;
 Object tmp_data[][];
 String tmp_data2[];
public static JFrame FRAME=null;

 	public PreviewTaxInvoice(Object data[][],String data2[],int r) 
	{
 		setAlwaysOnTop(true);
 		tmp_data=data;
		tmp_data2=data2;
		FRAME=this;
		 
 		JLabel label[][]=new JLabel[data.length][5];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(434, 670);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,420 ,595);
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/tax_invoice.png"));
		label1.setBounds(0, 0, 418, 592);
		rows=TaxInvoice.getDataRows();


		if(rows==0)
			rows=r;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<5;j++)
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
		
/*************************************data1 contents************************************************/
		int y=235;		
		for(int i=0;i<rows;i++)
		{
			if(label[i][1].getText().length()>LENGTH)
			{
			label[i][0].setBounds(20,y,40,30);
			label[i][1].setBounds(72,y,170,30);
			label[i][2].setBounds(235,y,60,30);
			label[i][3].setBounds(290,y,60,30);
			label[i][4].setBounds(345,y,60,30);
			
				
			label[i][1].setText("<html>"+tokenizer(label[i][1].getText())[0]+tokenizer(label[i][1].getText())[1]+"</html>");
			
			label[i][0].setVerticalAlignment(SwingConstants.TOP);
			label[i][1].setVerticalAlignment(SwingConstants.TOP);
			label[i][2].setVerticalAlignment(SwingConstants.TOP);
			label[i][3].setVerticalAlignment(SwingConstants.TOP);
			label[i][4].setVerticalAlignment(SwingConstants.TOP);
			
			y=y+15;
			}
			else
			{
				label[i][0].setBounds(20,y,40,20);
				label[i][1].setBounds(72,y,170,20);
				label[i][2].setBounds(235,y,60,20);
				label[i][3].setBounds(290,y,60,20);
				label[i][4].setBounds(345,y,60,20);
			}
			
			
			label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][1].setHorizontalAlignment(SwingConstants.LEFT);
			label[i][2].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][3].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][4].setHorizontalAlignment(SwingConstants.CENTER);
			y=y+28;
		}
		
		for(int i=0;i<rows;i++)
			for(int j=0;j<5;j++)
				{
				panel.add(label[i][j]);
				}
	
/*************************************data2 contents************************************************/
		
		JLabel sgst_in,cgst_in,sgst,cgst,grand_total,no,name,address,gstin,state_code,state,total,date,vehicle_info,total_text1,total_text2;
		
		no=new JLabel(data2[0]);
		name=new JLabel(data2[2]);
		address=new JLabel(data2[3]);
		
		gstin=new JLabel(data2[4]);
		state=new JLabel(data2[5]);
		state_code=new JLabel(data2[6]);
		total=new JLabel(data2[8]);
		date=new JLabel(data2[1]);
		vehicle_info=new JLabel(data2[7]);
		sgst=new JLabel(data2[9]);
		cgst=new JLabel(data2[10]);
		grand_total=new JLabel(data2[11]);
		
		float tmp=((Float.valueOf(data2[8])/100)*Float.valueOf(data2[9]));
		sgst_in=new JLabel(String.valueOf(tmp));
		
		float tmp2=((Float.valueOf(data2[8])/100)*Float.valueOf(data2[10]));
		cgst_in=new JLabel(String.valueOf(tmp));
		
		total_text1=new JLabel();
		total_text2=new JLabel();
		
		
		NumberToWord ntw=new NumberToWord();
		if(!data2[8].equals(""))
		{
			int m=Math.round(Float.valueOf(data2[11].toString()));
			data2[12]=tokenizer(ntw.convertNumberToWords(m, 0))[0];
			data2[13]=tokenizer(ntw.convertNumberToWords(m, 0))[1];
			total_text1.setText(data2[12]);
			total_text2.setText(data2[13]);
			
		}

		 no.setBounds(335,123,40,30);
		 date.setBounds(335,140,150,30);
		 name.setBounds(70,136,200,30);

			 JTextArea add=new JTextArea();
			 add.setBounds(70,155,190,20);
			 add.setWrapStyleWord(false);
			 add.setLineWrap(true);
			 
			 add.setEditable(false);
			 add.setBorder(new EmptyBorder(0,0,0,0));
			 add.setMargin( new Insets(0,0,0,0) );
			 add.setText(address.getText());
			 panel.add(add);
		 
		 state.setBounds(70,176,100,30);
		 state_code.setBounds(220,176,50,30);
		 gstin.setBounds(70,191,210,30);
		 
		 total.setBounds(345,434,60,20);
		 total.setHorizontalAlignment(SwingConstants.CENTER);
		 
		 cgst.setBounds(312,452,60,20);
		 sgst.setBounds(312,468,60,20);
		 
		 cgst_in.setBounds(345,452,60,20);
		 cgst_in.setHorizontalAlignment(SwingConstants.CENTER);
		 
		 sgst_in.setBounds(345,468,60,20);
		 sgst_in.setHorizontalAlignment(SwingConstants.CENTER);
		 
		
		 grand_total.setBounds(345,484,60,20);
		 grand_total.setHorizontalAlignment(SwingConstants.CENTER);
		 
		 
		 total_text1.setBounds(30,450,210,20);
		 total_text2.setBounds(30,470,210,20);
		 
		 
		 
			 JTextArea v_in=new JTextArea();
			 v_in.setBounds(270,177,130,33);
			 v_in.setWrapStyleWord(false);
			 v_in.setLineWrap(true);
			 v_in.setEditable(false);
			 v_in.setBorder(new EmptyBorder(0,0,0,0));
			 v_in.setMargin( new Insets(0,0,0,0) );
			 v_in.setText(vehicle_info.getText());
			 panel.add(v_in);
		 
		 
		 panel.add(no);
		 panel.add(name);
		 panel.add(address);
		 panel.add(state);
		 panel.add(state_code);
		 panel.add(gstin);
		 panel.add(date);
		 panel.add(total);
		 panel.add(vehicle_info);
		 panel.add(cgst);
		 panel.add(sgst);
		 panel.add(sgst_in);
		 panel.add(cgst_in);
		 panel.add(grand_total);
		 panel.add(total_text1);
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
			PrintTaxInvoice plb=new PrintTaxInvoice(tmp_data,tmp_data2,rows);
			this.dispose();
		}
		else
		{
			this.dispose();
		}
	}
}


