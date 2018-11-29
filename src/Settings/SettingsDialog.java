package Settings;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import GUI.MainClass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog implements ActionListener
{

	JTabbedPane t_pane;
	JButton btnOk,btnCancel;
	public SettingsDialog()
	{
		SystemProperty.getProperties();
		
		setTitle("Settings");
		setAlwaysOnTop(true);
		setResizable(false);
		setType(Type.POPUP);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(400, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		t_pane=new JTabbedPane();
		
		getContentPane().setLayout(null);
		t_pane.setBounds(8,8,380,390);
	
		PrinterSettings ps=new PrinterSettings();
		TableSettings ts=new TableSettings();
		t_pane.add(ps,"  Printer  ");
		t_pane.add(ts,"  Table Options  ");
		t_pane.add(new GeneralSettings(),"  General Settings  ");
		t_pane.add(new Backup(),"  Backup and Restore  ");
		getContentPane().add(t_pane);
		
		btnOk = new JButton("Ok");
		btnOk.setToolTipText("Save changes and close");
		btnOk.setBounds(68, 417, 106, 23);
		getContentPane().add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setToolTipText("Calcel and close");
		btnCancel.setBounds(224, 417, 106, 23);
		getContentPane().add(btnCancel);
		setLocationRelativeTo(null);

		btnCancel.addActionListener(this);
		btnOk.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource()==btnCancel)
		{
			this.dispose();
		}
		if(ae.getSource()==btnOk)
		{
			
			TableSettings.saveMyproperties();
			PrinterSettings.saveMyProperties();
			final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
			int i=JOptionPane.showConfirmDialog(this, "Application need to Restart to apply changes.\n              Are you want to Continue?","Confirm",JOptionPane.YES_NO_OPTION);
			if(i==0)
			{
				SystemProperty.saveProperties();
				new MainClass().reStart();
				this.dispose();
			}
		}
		
	}
}
