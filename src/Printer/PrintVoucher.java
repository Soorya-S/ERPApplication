package Printer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;








import Database.DataBase;
import GUI.Voucher;
import Settings.SystemProperty;

public class PrintVoucher extends JFrame
{

	static int rows;
	JLabel label1;
	public JLabel tmp[]=new JLabel[5];
	public int LENGTH=80;
	JPanel panel;
 	Object tmp_data[][];
 	String tmp_data2[];
 	public static JFrame FRAME=null;

 	public static int LOOK_UP;
 	
 	public PrintVoucher(Object data[][],String data2[],int r) 
	{
		tmp_data=data;
		tmp_data2=data2;
		FRAME=this;
	 

		
		JLabel label[][]=new JLabel[data.length][3];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1240,874);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,1240,874);
	
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/voucher_print.png"));
		label1.setBounds(0, 0,1240,874);
		rows=Voucher.getDataRows();
		
		if(rows==0)
		rows=r;
	
		if(r!=0)
			rows=r;

	for(int i=0;i<rows;i++)
	{
		for(int j=0;j<3;j++)
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
	
	int y=368;
	
	for(int i=0;i<rows;i++)
	{
		if(label[i][1].getText().length()>LENGTH)
		{
		label[i][0].setBounds(45,y,40,60);
		label[i][1].setText("<html>"+tokenizer(label[i][1].getText(),0)[0] +tokenizer(label[i][1].getText(),0)[1]+"</html>");
		label[i][1].setBounds(115,y,855,60);
		
		label[i][2].setBounds(1030,y,130,60);
		
		label[i][0].setVerticalAlignment(SwingConstants.TOP);
		label[i][1].setVerticalAlignment(SwingConstants.TOP);
		label[i][2].setVerticalAlignment(SwingConstants.TOP);
		
		y=y+23;
		}
		else
		{
			label[i][0].setBounds(45,y,40,30);
			label[i][1].setBounds(115,y,855,30);
			label[i][2].setBounds(1030,y,130,30);
		}
		
		
		label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
		label[i][1].setHorizontalAlignment(SwingConstants.LEFT);
		label[i][2].setHorizontalAlignment(SwingConstants.CENTER);

		y=y+45;
		System.out.println("y :"+y);
	}
	
	for(int i=0;i<rows;i++)
		for(int j=0;j<3;j++)
			{
			label[i][j].setFont(new Font(label[i][j].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
			panel.add(label[i][j]);
			}

	
	
	JLabel date,name,t1,no,total_text,total_text2;
	no=new JLabel(data2[0]);
	date=new JLabel(data2[1]);
	name=new JLabel(data2[2]);
	t1=new JLabel(data2[3]);
	total_text=new JLabel("");
	total_text2=new JLabel("");
	NumberToWord ntw=new NumberToWord();
	
	if(!data2[3].equals(""))
	{
		data2[4]=tokenizer(ntw.convertNumberToWords(Integer.parseInt(data2[3].toString()), 0))[0];
		data2[5]=tokenizer(ntw.convertNumberToWords(Integer.parseInt(data2[3].toString()), 0))[1];
	}
	
	
		total_text.setText(data2[4]);
		total_text2.setText(data2[5]);
	
	if(data2[4]==null || data2[4].equals(""))
	{
		total_text.setText("Zero Rupees");
	}
	else
	{
		total_text.setText(data2[4]);
		total_text2.setText(data2[5]);
	}

	no.setBounds(1053,174,82,30);
	date.setBounds(1053,214,125,30);
	name.setBounds(138,253,1061,30);
	t1.setBounds(1030,706,130,30);
	
	total_text.setBounds(148,758,790,30);
	total_text2.setBounds(60,795,840,30);
	
	t1.setHorizontalAlignment(SwingConstants.CENTER);
	total_text.setHorizontalAlignment(SwingConstants.LEFT);
	
	date.setFont(new Font(date.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
	name.setFont(new Font(date.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE+1));
	t1.setFont(new Font(date.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
	no.setFont(new Font(date.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
	total_text.setFont(new Font(date.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
	total_text2.setFont(new Font(date.getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
	
	panel.add(no);
	panel.add(date);
	panel.add(name);
	panel.add(t1);
	panel.add(total_text);
	panel.add(total_text2);
	panel.add(label1);
	getContentPane().add(panel);
	
	PrintImage.REF=2;
	PrintImage.OR=1;
	DataBase db=null;
	
	
	if(y>708)
	{
		final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		if (runnable != null) runnable.run();
		JOptionPane.showMessageDialog(this, "Oops! The Size of the Bill exceeds! Please reduce some data", "Error!", JOptionPane.ERROR_MESSAGE);
		this.dispose();
	}
	else
	{
		if(LOOK_UP!=1)
		{
			db=new DataBase(data,data2,2);	
			this.dispose();
		}
	
		if(LOOK_UP!=1)
		{
			if(db.RESULT==1)
			{
				ComponentSnapShot s=new ComponentSnapShot(panel);
				SystemProperty.VOUCHER_NO++;
				SystemProperty.setNumber();
				try
				{
					Thread.sleep(1000);
				} catch (InterruptedException w){w.printStackTrace();}
			
				SystemProperty.getNumber();
				Voucher.num.setText(String.valueOf(SystemProperty.VOUCHER_NO));
				Voucher.clearFields();
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


private String[] tokenizer(String m,int f)
{
	int flag=1;
	String a,c="",d="";
	StringTokenizer obj = new StringTokenizer(m," ");
	while(obj.hasMoreTokens())
	{
          a= obj.nextToken()+" ";
          
          if(((c+a).length())>65)
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
