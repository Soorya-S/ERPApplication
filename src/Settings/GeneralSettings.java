package Settings;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.omg.CORBA.portable.InputStream;

public class GeneralSettings extends JPanel implements ActionListener
{
	JPasswordField old_pass;
	JPasswordField new_pass;
	JButton btnChangePassword ;
	public GeneralSettings()
	{
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(379,390);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Change Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(8, 16, 361, 133);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Old Password");
		lblNewLabel.setBounds(26, 33, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(26, 67, 70, 14);
		panel.add(lblNewPassword);
		
		old_pass = new JPasswordField();
		old_pass.setBounds(149, 30, 177, 20);
		panel.add(old_pass);
		
		new_pass = new JPasswordField();
		new_pass.setBounds(149, 64, 177, 20);
		panel.add(new_pass);
		
		btnChangePassword = new JButton("Change");
		btnChangePassword.setBounds(203, 99, 70, 23);
		panel.add(btnChangePassword);
		btnChangePassword.addActionListener(this);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnChangePassword)
		{
			

			 
			BufferedWriter bw = null;
			FileWriter fw = null;

			try 
			{
				String p=new String(Files.readAllBytes(Paths.get("src/Resources/security/encrypt.bill")));				
				if(p.equals(old_pass.getText()))
				{
					if(!(new_pass.getText().equals("")) || new_pass.getText()==null )
					{
						fw = new FileWriter("src/Resources/security/encrypt.bill");
						bw = new BufferedWriter(fw);
						bw.write(new_pass.getText());
						JOptionPane.showMessageDialog(this, "Password Changed!");
						new_pass.setText("");
						old_pass.setText("");
					}
					else
					{
						final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
						if (runnable != null) runnable.run();
						JOptionPane.showMessageDialog(this, "Password should not be Empty!");
					}
				}
				else
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(this, "Your Old password was wrong!");
				}
				  
			} catch (IOException ex1)
			{
				final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
				JOptionPane.showMessageDialog(this, ex1);
			}
			finally
			{

				try
				{
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} catch (IOException ex)
				{
					final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
					JOptionPane.showMessageDialog(this, ex);
				}

		}
	}
	}
	}

