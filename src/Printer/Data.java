package Printer;

import javax.swing.JLabel;

public class Data 
{

	public static JLabel[] prepareToPrint(Object data[][],String data2[],int flag)
	{
		int len=data.length*4;
		JLabel label[]=new JLabel[len];
		
		if(flag==1)
		{
			for(int i=0,k=0;i<data.length;i++)
			{
				for(int j=0;j<4;j++)
				{
					if(data[i][j]!=null)
					{
					label[k]=new JLabel();
					label[k].setText(data[i][j].toString());
					k++;
					}
				}
				
			}
		}
		return label;
	}
}
