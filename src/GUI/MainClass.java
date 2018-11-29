package GUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Settings.SystemProperty;


public class MainClass
{
	public static MainFrame frame=null;
	public MainFrame f=frame;
	public static void main(String[] args)
	{
		
		String pass=JOptionPane.showInputDialog(null, "Enter Password");
		String or_pass="";
		
			try
			{
			
				Path file = Paths.get("src/Resources/security/encrypt.bill");
				InputStream in = Files.newInputStream(file);
			    BufferedReader reader =new BufferedReader(new InputStreamReader(in));
			    String line = null;
			    line = reader.readLine();
			    or_pass=line;
			    
			}catch(IOException e)
			{
				JOptionPane.showMessageDialog(null,e);
				System.exit(0);
			}
			
			
				if(pass.equals(or_pass))
				{
					frame=new MainFrame();
					frame.setVisible(true);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Wrong Password","Error",JOptionPane.ERROR_MESSAGE);
					reStart();
				}
			
			
	}
	
	public static void reStart()
	{
		try
		{
			Runtime.getRuntime().exec("java -jar app.jar");	
		} catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null,"Error while restart!. Please force Stop the Application!","Message",JOptionPane.ERROR_MESSAGE);
			
		}
		System.exit(0);
	}

}
