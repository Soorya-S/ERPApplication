package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.components.JLocaleChooser;

public class AddEntry extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEntry dialog = new AddEntry();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEntry() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
	}
}
