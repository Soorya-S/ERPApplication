package Printer;


import java.awt.Font;
import java.awt.Toolkit;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Database.DataBase;
import GUI.LabourBill;
import Settings.SystemProperty;

public class PrintLabourBill extends JFrame 
{
	static int rows;
	JLabel label1;
	public JLabel tmp[]=new JLabel[5];
	public int LENGTH=45;
	JPanel panel;
	public static int LOOK_UP;
	

 Object tmp_data[][];
 String tmp_data2[];

 	public PrintLabourBill(Object data[][],String data2[],int r) 
	{
 		tmp_data=data;
		tmp_data2=data2;
		
 		JLabel label[][]=new JLabel[data.length][4];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(874,1240);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,874,1240);
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/labour_bill_print.png"));
		label1.setBounds(0, 0, 874,1240);
		rows=LabourBill.getDataRows();
		
			if(rows==0)
			{
				rows=r;
			}
		
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

/*************************************data1 contents************************************************/
		
		int y=455;
		
		for(int i=0;i<rows;i++)
		{
			if(label[i][1].getText().length()>LENGTH)
			{
				label[i][0].setBounds(20,y,80,60);
				label[i][1].setBounds(105,y,490,60);
				label[i][2].setBounds(605,y,120,60);
				label[i][3].setBounds(728,y,120,60);
				
				label[i][1].setText("<html>"+tokenizer(label[i][1].getText(),0)[0]+tokenizer(label[i][1].getText(),0)[1]+"</html>");
				
			label[i][0].setVerticalAlignment(SwingConstants.TOP);
			label[i][1].setVerticalAlignment(SwingConstants.TOP);
			label[i][2].setVerticalAlignment(SwingConstants.TOP);
			label[i][3].setVerticalAlignment(SwingConstants.TOP);
			y=y+30;
			}
			else
			{
				label[i][0].setBounds(20,y,80,30);
				label[i][1].setBounds(105,y,490,30);
				label[i][2].setBounds(605,y,120,30);
				label[i][3].setBounds(728,y,120,30);
			}
			
			label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][1].setHorizontalAlignment(SwingConstants.LEFT);
			label[i][2].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][3].setHorizontalAlignment(SwingConstants.CENTER);
			
			y=y+45;
		}
		System.out.println("y :" +y);
		for(int i=0;i<rows;i++)
			for(int j=0;j<4;j++)
				{
				label[i][j].setFont(new Font(label[i][j].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
				panel.add(label[i][j]);
				}
	
		
		
		JLabel date,name,name2,t1,t2,no,total_text,total_text2;
		
		no=new JLabel(data2[0]);
		date=new JLabel(data2[1]);
		
		name=new JLabel(tokenizer(data2[2],0)[0]);
		name2=new JLabel(tokenizer(data2[2],0)[1]);
		name.setAlignmentX(SwingConstants.LEFT);
		t1=new JLabel(data2[3]);
		t2=new JLabel(data2[4]);
		total_text=new JLabel("");
		total_text2=new JLabel("");
		NumberToWord ntw=new NumberToWord();
		
		if(!data2[4].equals(""))
		{
			int m=Math.round(Float.valueOf(data2[4].toString()));
			data2[5]=tokenizer(ntw.convertNumberToWords(m, 0))[0];
			data2[6]=tokenizer(ntw.convertNumberToWords(m, 0))[1];
		}
		
		
			total_text.setText(data2[5]);
			total_text2.setText(data2[6]);
		
		/*************************************data2 contents************************************************/
		
		
		no.setBounds(88,255,40,30);
		date.setBounds(665,255,130,30);
		name.setBounds(125,297,720,30);
		name2.setBounds(35,340,820,30);
		t1.setBounds(605,1055,120,30);
		t2.setBounds(728,1055,120,30);
		
		total_text.setBounds(120,1122,520,30);
		total_text2.setBounds(45,1162,500,30);
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		
		no.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		date.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		name.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		name2.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		t1.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		t2.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		total_text.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		total_text2.setFont(new Font(no.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		
		panel.add(no);
		panel.add(date);
		panel.add(name);
		panel.add(name2);
		
		panel.add(t1);
		panel.add(t2);
		panel.add(total_text);
		panel.add(total_text2);
		panel.add(label1);
		
		add(panel);
		
		
		setLocationRelativeTo(null);
		PrintImage.REF=0;

		DataBase db=null;
		
		if(y>1055)
		{
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
			JOptionPane.showMessageDialog(this, "Oops! The Size of the Bill exceeds! Please reduce some data", "Error!", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
		else
		{
			if(LOOK_UP!=1)
				db=new DataBase(data,data2,0);	
		
			if(LOOK_UP!=1)
				if(db.RESULT==1)
				{	
					ComponentSnapShot s=new ComponentSnapShot(panel);
					SystemProperty.LABOUR_NO++;
					SystemProperty.setNumber();
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e){e.printStackTrace();}
					
					SystemProperty.getNumber();
					LabourBill.num.setText(String.valueOf(SystemProperty.LABOUR_NO));
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
	}
 	
 	
	
	private String[] tokenizer(String m)
    {
		int flag=1;
    	String a,c="",d="";
    	StringTokenizer obj = new StringTokenizer(m+" Rupees"," ");
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

	private String[] tokenizer(String m,int ff)
    {
		int flag=1;
    	String a,c="",d="";
    	StringTokenizer obj = new StringTokenizer(m," ");
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

}