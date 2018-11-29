package Printer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.Voucher;

public class PreviewVoucher extends JFrame implements ActionListener
{

	static int rows;
	JLabel label1;
	public JLabel tmp[]=new JLabel[5];
	public int LENGTH=65;
	JPanel panel;
	JButton print,cancel;
 	Object tmp_data[][];
 	String tmp_data2[];
 	public static JFrame FRAME=null;

 	public PreviewVoucher(Object data[][],String data2[],int r) 
	{
 		setAlwaysOnTop(true);
		tmp_data=data;
		tmp_data2=data2;
		FRAME=this;
	 
		JLabel label[][]=new JLabel[data.length][3];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(606, 505);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,595 ,420);
	
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/voucher.png"));
		label1.setBounds(0, 0, 592, 418);
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
	
	int y=175;
	
	for(int i=0;i<rows;i++)
	{
		if(label[i][1].getText().length()>LENGTH)
		{
		label[i][0].setBounds(12,y,40,30);
		label[i][1].setText("<html>"+tokenizer(label[i][1].getText(),0)[0] + tokenizer(label[i][1].getText(),0)[1]+"</html>");
		label[i][1].setBounds(55,y,400,30);
		
		label[i][2].setBounds(494,y,60,30);
		
		label[i][0].setVerticalAlignment(SwingConstants.TOP);
		label[i][1].setVerticalAlignment(SwingConstants.TOP);
		label[i][2].setVerticalAlignment(SwingConstants.TOP);
		
		y=y+15;
		}
		else
		{
			label[i][0].setBounds(12,y,40,20);
			label[i][1].setBounds(55,y,400,20);
			label[i][2].setBounds(494,y,60,20);
			
		}
		
		
		label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
		label[i][1].setHorizontalAlignment(SwingConstants.LEFT);
		label[i][2].setHorizontalAlignment(SwingConstants.CENTER);

		y=y+26;
	}
	
	for(int i=0;i<rows;i++)
		for(int j=0;j<3;j++)
			{
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
	
	/*if(data2[5].equals("") || data2[5].equals(null))
	{
		total_text.setText(data2[4]+" Rupees");
	}
	else
	{
		total_text.setText(data2[4]);
		total_text2.setText(data2[5]+" Rupees");
	}
	*/
	
	if(data2[4]==null || data2[4].equals(""))
	{
		total_text.setText("Zero Rupees");
	}
	else if(  data2[5].equals("") || data2[5].equals(null))
	{
		total_text.setText(data2[4]+" Rupees");
	}
	else
	{
		total_text.setText(data2[4]);
		total_text2.setText(data2[5]+" Rupees");
	}

	no.setBounds(503,81,40,20);
	date.setBounds(503,100,70,20);
	name.setBounds(70,117,515,20);
	t1.setBounds(494,335,55,20);
	
	total_text.setBounds(71,360,350,20);
	total_text2.setBounds(25,377,400,20);
	t1.setHorizontalAlignment(SwingConstants.CENTER);
	total_text.setHorizontalAlignment(SwingConstants.LEFT);
	
	panel.add(no);
	panel.add(date);
	panel.add(name);
	panel.add(t1);
	panel.add(total_text);
	panel.add(total_text2);
	panel.add(label1);
	
	
	
	print =new JButton("Print");
	print.setBounds(186,431,89, 28);
	getContentPane().add(print);
	
	cancel=new JButton("close");
	cancel.setBounds(327,431,89, 28);
	getContentPane().add(cancel);
	
	getContentPane().add(panel);
	
	print.addActionListener(this);
	cancel.addActionListener(this);
	
	setVisible(true);
	setLocationRelativeTo(null);
	
}
	
	
@Override
public void actionPerformed(ActionEvent arg0) 
{
	if(arg0.getSource()==print)	
	{
		PrintVoucher plb=new PrintVoucher(tmp_data,tmp_data2,rows);
	}
	else
	{
		this.dispose();
	}
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
          
          if(((c+a).length())>200)
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
