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

import Database.DataBase;
import GUI.DeliveryNote;
import Settings.SystemProperty;

public class PrintDeliveryNote extends JFrame 
{
	static int rows;
	static JLabel label1;
	JPanel panel;
	JLabel label[][];
	/**
	 * @wbp.parser.entryPoint
	 */
	public static int LOOK_UP;
	public int LENGTH=39;
	public PrintDeliveryNote(Object data[][],String data2[],int r) 
	{
		label=new JLabel[data.length][6];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(874,1240);
		getContentPane().setLayout(null);
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0,874,1240);
		
		label1 = new JLabel();
		label1.setIcon(new ImageIcon("src/Resources/images/Delivery_bill_print.png"));
		label1.setBounds(0, 0, 874,1240);
		rows=DeliveryNote.getDataRows();
		int k=0;
		
		if(rows==0)
			rows=r;
		if(r!=0)
			rows=r;
		
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
		
		int y=465;
		for(int i=0;i<rows;i++)
		{
			
			if(label[i][2].getText().length()>LENGTH)
			{
				System.out.print("\nTrue\n");
				label[i][0].setBounds(30,y,40,60);
				label[i][1].setBounds(58,y,110,60);
				label[i][2].setBounds(160,y,400,60);
				label[i][2].setText("<html>"+tokenizer(label[i][2].getText())[0]+"<br>"+tokenizer(label[i][2].getText())[1]+"</html>");
				label[i][3].setBounds(553,y,105,60);
				label[i][4].setBounds(648,y,105,60);
				label[i][5].setBounds(746,y,105,60);
				
				
				y=y+30;
				
				
				
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
				label[i][0].setBounds(30,y,40,30);
				label[i][1].setBounds(58,y,110,30);
				label[i][2].setBounds(163,y,400,30);
				label[i][3].setBounds(553,y,105,30);
				label[i][4].setBounds(648,y,105,30);
				label[i][5].setBounds(746,y,105,30);
			}
			
				
			label[i][0].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][1].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][2].setHorizontalAlignment(SwingConstants.LEFT);
			label[i][3].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][4].setHorizontalAlignment(SwingConstants.CENTER);
			label[i][5].setHorizontalAlignment(SwingConstants.CENTER);
			
			
		
			y=y+45;
		}
		System.out.println(y);
		for(int i=0;i<rows;i++)
			for(int j=0;j<6;j++)
			{
				label[i][j].setFont(new Font(label[i][j].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
				panel.add(label[i][j]);
			}
	
		
		/*************************************data2 contents************************************************/
		JLabel label2[]=new JLabel[6];
		
		label2[0]=new JLabel(data2[0]);
		label2[1]=new JLabel(data2[1]);
		label2[2]=new JLabel(tokenizer(data2[2])[0]);
		label2[5]=new JLabel(tokenizer(data2[2])[1]);
		label2[3]=new JLabel(data2[3]);
		label2[4]=new JLabel(data2[4]);
		
		
		label2[0].setFont(new Font(label2[0].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		label2[1].setFont(new Font(label2[0].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		label2[2].setFont(new Font(label2[0].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		label2[5].setFont(new Font(label2[0].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		label2[3].setFont(new Font(label2[0].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		label2[4].setFont(new Font(label2[0].getFont().getName(),Font.PLAIN,SystemProperty.PRINT_FONT_SIZE));
		
		label2[0].setBounds(92,254,40,30);
		label2[1].setBounds(666,254,130,30);
		
		label2[2].setBounds(128,257,720,100);
		label2[5].setBounds(30,300,820,100);
		
		label2[3].setBounds(658,1050,80,30);
		label2[4].setBounds(756,1050,92,30);
		
		label2[4].setHorizontalAlignment(SwingConstants.CENTER);
		label2[3].setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(label2[0]);
		panel.add(label2[1]);
		panel.add(label2[2]);
		panel.add(label2[5]);
		//panel.add(label2[3]);
		panel.add(label2[4]);
		
	
		
		
		panel.add(label1);	
		getContentPane().add(panel);
		
		
		PrintImage.REF=1;
		
		/******Storing data to DB*******/
		DataBase db=null;
		
		if(y>1035)
		{
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
			JOptionPane.showMessageDialog(this, "Oops! The Size of the Bill exceeds! Please reduce some data", "Error!", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
		else
		{
			if(LOOK_UP!=1)
				db=new DataBase(data,data2,1);	
		
			if(LOOK_UP!=1)
				if(db.RESULT==1)
				{
					ComponentSnapShot s=new ComponentSnapShot(panel);
			
					SystemProperty.DELIVERY_NO++;
					SystemProperty.setNumber();
					try
					{
						Thread.sleep(1000);
					} catch (InterruptedException w){w.printStackTrace();}
				
					SystemProperty.getNumber();
					DeliveryNote.num.setText(String.valueOf(SystemProperty.DELIVERY_NO));
				}
				else
				{
					if(LOOK_UP!=1)
					{
						final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
						if (runnable != null) runnable.run();
						JOptionPane.showMessageDialog(this, "Error while storing data to DataBase!!", "Message", JOptionPane.ERROR_MESSAGE);
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
