package Printer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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

import GUI.DeliveryNote;
import GUI.LabourBill;

public class PreviewDeliveryNote extends JFrame implements ActionListener
{

	static int rows;
	static JLabel label1;
	JPanel panel;
	JButton print,cancel;
	Object tmp_data[][];
	String tmp_data2[];
	/**
	 * @wbp.parser.entryPoint
	 */
	public int LENGTH=30;
	public static JFrame FRAME=null;
	public PreviewDeliveryNote(Object data[][],String data2[],int r) 
	{
		setAlwaysOnTop(true);
		tmp_data=data;
		tmp_data2=data2;
		FRAME=this;
		
		JLabel label[][]=new JLabel[data.length][6];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(434, 670);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,420 ,595);
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/Delivery_bill.png"));
		label1.setBounds(0, 0, 420, 595);
		rows=DeliveryNote.getDataRows();
		int y=220;
		int k=0;

		if(rows==0)
		{
			rows=r;
		}
		
		if(r!=0)
		{
			rows=r;
		}
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<6;j++)
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
			
			if(label[i][2].getText().length()>LENGTH)
			{
				label[i][0].setBounds(10,y,20,30);
				label[i][1].setBounds(34,y,45,30);
				label[i][2].setText("<html>"+tokenizer(label[i][2].getText())[0]+tokenizer(label[i][2].getText())[1]+"</html>");
				label[i][2].setBounds(79,y,195,30);
				
				label[i][3].setBounds(263,y,50,30);
				label[i][4].setBounds(311,y,50,30);
				label[i][5].setBounds(358,y,50,0);
				y=y+15;
				
				label[i][0].setVerticalAlignment(SwingConstants.TOP);
				label[i][1].setVerticalAlignment(SwingConstants.TOP);
				label[i][2].setVerticalAlignment(SwingConstants.TOP);
				label[i][2].setVerticalAlignment(SwingConstants.TOP);
				label[i][3].setVerticalAlignment(SwingConstants.TOP);
				label[i][4].setVerticalAlignment(SwingConstants.TOP);
				label[i][5].setVerticalAlignment(SwingConstants.TOP);
			}
			else
			{
				label[i][0].setBounds(10,y,20,20);
				label[i][1].setBounds(34,y,45,20);
				label[i][2].setBounds(79,y,195,20);
				label[i][3].setBounds(263,y,50,20);
				label[i][4].setBounds(311,y,50,20);
				label[i][5].setBounds(358,y,50,20);
			}
			
			label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][1].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][2].setHorizontalAlignment(SwingConstants.LEFT);
			label[i][3].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][4].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][5].setHorizontalAlignment(SwingConstants.CENTER);
			
			
		
			y=y+26;
		}
		
		for(int i=0;i<rows;i++)
			for(int j=0;j<6;j++)
				{
				panel.add(label[i][j]);
				}
	
		JLabel label2[]=new JLabel[5];
		
		label2[0]=new JLabel(data2[0]);
		label2[1]=new JLabel(data2[1]);
		label2[2]=new JLabel(data2[2]);
		label2[3]=new JLabel(data2[3]);
		label2[4]=new JLabel(data2[4]);
		
		
		label2[0].setBounds(42,121,40,20);
		label2[1].setBounds(321,121,70,20);
		label2[2].setBounds(62,139,300,20);
		label2[3].setBounds(316,504,40,20);
		label2[4].setBounds(363,505,40,20);
		
		label2[4].setHorizontalAlignment(SwingConstants.CENTER);
		label2[3].setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(label2[0]);
		panel.add(label2[1]);
		panel.add(label2[2]);
		//panel.add(label2[3]);
		panel.add(label2[4]);
		
	
		
		
		panel.add(label1);	
		getContentPane().add(panel);
		
		print =new JButton("Print");
		print.setBounds(130,597,89, 28);
		getContentPane().add(print);
		
		cancel=new JButton("close");
		cancel.setBounds(240,597,89, 28);
		getContentPane().add(cancel);
		
		print.addActionListener(this);
		cancel.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
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
			PrintDeliveryNote p=new PrintDeliveryNote(tmp_data,tmp_data2,rows);
		}
		else
		{
			this.dispose();
		}
	}

}
