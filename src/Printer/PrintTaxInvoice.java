package Printer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Database.DataBase;
import GUI.TaxInvoice;
import Settings.SystemProperty;

public class PrintTaxInvoice extends JFrame
{
	static int rows;
	JLabel label1;
	public JLabel tmp[]=new JLabel[5];
	public int LENGTH=27;
	JPanel panel;
	public static int LOOK_UP;
	

 Object tmp_data[][];
 String tmp_data2[];

	public PrintTaxInvoice(Object data[][],String data2[],int r)
	{
 		tmp_data=data;
		tmp_data2=data2;
		
 		JLabel label[][]=new JLabel[data.length][5];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(874,1240);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,874,1240);
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/tax_invoice_print.png"));
		label1.setBounds(0, 0, 874,1240);
		rows=TaxInvoice.getDataRows();
		
			if(rows==0)
			{
				rows=r;
			}
		
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
	int y=500;
	for(int i=0;i<rows;i++)
	{
		if(label[i][1].getText().length()>LENGTH)
		{
			label[i][0].setBounds(30,y,80,60);
			label[i][1].setBounds(150,y,360,60);
			label[i][2].setBounds(495,y,120,60);
			label[i][3].setBounds(600,y,120,60);
			label[i][4].setBounds(720,y,120,60);
			
			label[i][1].setText("<html>"+tokenizer(label[i][1].getText(),0)[0]+tokenizer(label[i][1].getText(),0)[1]+"</html>");
			
		label[i][0].setVerticalAlignment(SwingConstants.TOP);
		label[i][1].setVerticalAlignment(SwingConstants.TOP);
		label[i][2].setVerticalAlignment(SwingConstants.TOP);
		label[i][3].setVerticalAlignment(SwingConstants.TOP);
		label[i][4].setVerticalAlignment(SwingConstants.TOP);
		y=y+30;
		}
		else
		{
			label[i][0].setBounds(30,y,80,30);
			label[i][1].setBounds(150,y,360,30);
			label[i][2].setBounds(495,y,120,30);
			label[i][3].setBounds(600,y,120,30);
			label[i][4].setBounds(720,y,120,30);
		}
		
		label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
		label[i][1].setHorizontalAlignment(SwingConstants.LEFT);
		label[i][2].setHorizontalAlignment(SwingConstants.CENTER);
		label[i][3].setHorizontalAlignment(SwingConstants.CENTER);
		label[i][4].setHorizontalAlignment(SwingConstants.CENTER);
		y=y+45;
	}
	System.out.println("y :" +y);
	for(int i=0;i<rows;i++)
		for(int j=0;j<5;j++)
			{
			label[i][j].setFont(new Font(label[i][j].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
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

	 sgst.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 cgst.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 grand_total.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 no.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 name.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 address.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 gstin.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 state_code.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 state.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 total.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 date.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 vehicle_info.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-2));
	 total_text1.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 total_text2.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
		
	 cgst_in.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 sgst_in.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-1));
	 total.setHorizontalAlignment(SwingConstants.CENTER);
	 cgst_in.setHorizontalAlignment(SwingConstants.CENTER);
	 sgst_in.setHorizontalAlignment(SwingConstants.CENTER);
	 grand_total.setHorizontalAlignment(SwingConstants.CENTER);
		
	 no.setBounds(695,275,40,30);
	 name.setBounds(145,300,400,30);
	 
		 JTextArea add=new JTextArea();
		 add.setBounds(147,336,400,45);
		 add.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-3));
		 add.setWrapStyleWord(false);
		 add.setLineWrap(true);
		 add.setEditable(false);
		 add.setBorder(new EmptyBorder(0,0,0,0));
		 add.setMargin( new Insets(0,0,0,0) );
		 add.setText(address.getText());
		 panel.add(add);
	
	 state.setBounds(145,385,200,30);
	 state_code.setBounds(460,385,200,30);
	 gstin.setBounds(145,416,200,30);
	 date.setBounds(695,310,150,30);
	 total.setBounds(720,895,120,60);
	 cgst.setBounds(650,932,120,60);
	 sgst.setBounds(650,968,120,60);
	 cgst_in.setBounds(725,932,120,60);
	 sgst_in.setBounds(720,967,120,60);
	 grand_total.setBounds(720,1000,120,60);
	 total_text1.setBounds(65,932,415,60);
	 total_text2.setBounds(65,969,415,60);
	 
	 
	 if(vehicle_info.getText().length()<=13)
	 {
		 vehicle_info.setBounds(695,350,150,30);
	 }
	 else
	 {
		 JTextArea v_in=new JTextArea();
		 v_in.setBounds(560,372,260,73);
		 v_in.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE-3));
		 v_in.setWrapStyleWord(false);
		 v_in.setLineWrap(true);
		 v_in.setEditable(false);
		 v_in.setBorder(new EmptyBorder(0,0,0,0));
		 v_in.setMargin( new Insets(0,0,0,0) );
		 v_in.setText(vehicle_info.getText());
		 panel.add(v_in);
	 }
	 
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
	 
	 add(panel);

	 setLocationRelativeTo(null);
	 PrintImage.REF=0;
	 DataBase db=null;
		
		if(y>920)
		{
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
			JOptionPane.showMessageDialog(this, "Oops! The Size of the Bill exceeds! Please reduce some data", "Error!", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
		else
		{
			if(LOOK_UP!=1)
				db=new DataBase(data,data2,3);	
			if(LOOK_UP!=1)
				if(db.RESULT==1)
				{	
					ComponentSnapShot s=new ComponentSnapShot(panel);
					SystemProperty.TAX_INOVOICE_NO++;
					TaxInvoice.clearFields();
					SystemProperty.setNumber();
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e){e.printStackTrace();}
					
					SystemProperty.getNumber();
					TaxInvoice.num.setText(String.valueOf(SystemProperty.TAX_INOVOICE_NO));
				}
				else
				{
					if(LOOK_UP!=1)
					{
						final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
						if (runnable != null) runnable.run();
						JOptionPane.showMessageDialog(this, "Error while storing data to DataBase!", "Message", JOptionPane.ERROR_MESSAGE);
					}
					}
		}
		
		if(LOOK_UP==1)
		{
			ComponentSnapShot s=new ComponentSnapShot(panel);
			LOOK_UP=0;
		}

		//setVisible(true);
	}
	




private String[] tokenizer(String m)
{
	int flag=1;
	String a,c="",d="";
	StringTokenizer obj = new StringTokenizer(m+" Rupees"," ");
	while(obj.hasMoreTokens())
	{
          a= obj.nextToken()+" ";
          
          if(((c+a).length())>40)
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



private String[] tokenizer(String m,int ff)
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

return tokens;
}

}