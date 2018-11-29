package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class About extends JDialog {

	/**
	 * Launch the application.
	 */
	
	public About() 
	{
		setType(Type.POPUP);
		setResizable(false);
		setTitle("About");
		setSize( 458, 375);
		getContentPane().setLayout(null);
		setVisible(true);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "About the Developer..", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 191, 424, 114);
		getContentPane().add(panel);
		panel.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblIcoders = new JLabel("iCoders ");
		lblIcoders.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIcoders.setBounds(188, 22, 58, 14);
		panel.add(lblIcoders);
		
		JLabel lblInfoicodersgmailcom = new JLabel("<html><a href=>info.iCoders@gmail.com</html>");
		lblInfoicodersgmailcom.setBounds(149, 47, 135, 14);
		panel.add(lblInfoicodersgmailcom);
		
		JLabel lblCopyright = new JLabel("Copyright \u00A9 2017- All rights reserved");
		lblCopyright.setBounds(127, 89, 194, 14);
		panel.add(lblCopyright);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "About the application..", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 424, 169);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblVersion = new JLabel("Version :");
		lblVersion.setBounds(102, 49, 67, 14);
		panel_1.add(lblVersion);
		
		JLabel lblbit = new JLabel("1.3    (32-bit)");
		lblbit.setBounds(186, 49, 89, 14);
		panel_1.add(lblbit);
		
		JLabel lblVendor = new JLabel("Vendor :");
		lblVendor.setBounds(102, 80, 46, 14);
		panel_1.add(lblVendor);
		
		JLabel lblIcoders_1 = new JLabel("iCoders");
		lblIcoders_1.setBounds(189, 80, 46, 14);
		panel_1.add(lblIcoders_1);
		
		JLabel lblBuildTime = new JLabel("Build Time :");
		lblBuildTime.setBounds(102, 109, 67, 14);
		panel_1.add(lblBuildTime);
		
		JLabel lblAm = new JLabel("27-March-2017, 12:58 AM");
		lblAm.setBounds(186, 109, 180, 14);
		panel_1.add(lblAm);
		
		JLabel lblBuildNumber = new JLabel("Build number :");
		lblBuildNumber.setBounds(102, 144, 77, 14);
		panel_1.add(lblBuildNumber);
		
		JLabel lblNewLabel = new JLabel("00.00.00");
		lblNewLabel.setBounds(189, 144, 46, 14);
		panel_1.add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		final JDialog d=this;
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
						d.dispose();	
			}
		});
		btnOk.setBounds(203, 313, 52, 23);
		getContentPane().add(btnOk);
	
	}
}
