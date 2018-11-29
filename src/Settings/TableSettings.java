package Settings;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class TableSettings extends JPanel {
	static JComboBox d_combo,v_combo, table_font_combo,font_type_combo;
	/**
	 * Create the panel.
	 */
	public TableSettings()
	{
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);
		setSize(379,390);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Delivery Note Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 359, 68);
		add(panel);
		
		JLabel label = new JLabel("Serial number");
		label.setBounds(49, 31, 111, 14);
		panel.add(label);
		
		d_combo = new JComboBox();
		d_combo.setModel(new DefaultComboBoxModel(new String[] {"Auto", "Manual"}));
		if(SystemProperty.DELIVERY_SL_NO_AUTO)
		{
			d_combo.setSelectedIndex(0);
		}
		else
		{
			d_combo.setSelectedIndex(1);
		}
		d_combo.setBounds(196, 28, 104, 20);
		panel.add(d_combo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Voucher Properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 117, 359, 68);
		add(panel_1);
		
		JLabel label_2 = new JLabel("Serial number");
		label_2.setBounds(49, 31, 111, 14);
		panel_1.add(label_2);
		
		v_combo = new JComboBox();
		v_combo.setModel(new DefaultComboBoxModel(new String[] {"Auto", "Manual"}));
		v_combo.setBounds(196, 28, 104, 20);
		if(SystemProperty.VOUCHER_SL_NO_AUTO)
		{
			v_combo.setSelectedIndex(0);
		}
		else
		{
			v_combo.setSelectedIndex(1);
		}
		
		panel_1.add(v_combo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Common", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 214, 359, 114);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTableFontSize = new JLabel("Table Font size");
		lblTableFontSize.setBounds(43, 30, 116, 14);
		panel_2.add(lblTableFontSize);
		
		table_font_combo = new JComboBox();
		table_font_combo.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium", "Large", "Extra Large"}));
		table_font_combo.setBounds(198, 27, 102, 20);
		if(SystemProperty.TABLE_FONT_SIZE==12)
		{
			table_font_combo.setSelectedIndex(0);
		}
		else if(SystemProperty.TABLE_FONT_SIZE==13)
		{
			table_font_combo.setSelectedIndex(1);
		}
		else if(SystemProperty.TABLE_FONT_SIZE==14)
		{
			table_font_combo.setSelectedIndex(2);
		}
		else if(SystemProperty.TABLE_FONT_SIZE==15)
		{
			table_font_combo.setSelectedIndex(3);
		}
		
		panel_2.add(table_font_combo);
		
		JLabel lblFontType = new JLabel("Font Type");
		lblFontType.setBounds(43, 69, 95, 14);
		panel_2.add(lblFontType);
		
		font_type_combo = new JComboBox();
		font_type_combo.setModel(new DefaultComboBoxModel(new String[] {"Plain", "Bold", "Italic"}));
		font_type_combo.setBounds(198, 66, 102, 20);
		font_type_combo.setSelectedIndex(SystemProperty.TABLE_FONT_TYPE);
		panel_2.add(font_type_combo);
	}




	public static void saveMyproperties()
	{
		if(d_combo.getSelectedIndex()==0)
		{
			SystemProperty.DELIVERY_SL_NO_AUTO=true;
		}
		else
		{
			SystemProperty.DELIVERY_SL_NO_AUTO=false;
		}
		
		if(v_combo.getSelectedIndex()==0)
		{
			SystemProperty.VOUCHER_SL_NO_AUTO=true;
		}
		else
		{
			SystemProperty.VOUCHER_SL_NO_AUTO=false;
		}
		
		if(table_font_combo.getSelectedIndex()==0)
		{
			SystemProperty.TABLE_FONT_SIZE=12;
		}
		else if(table_font_combo.getSelectedIndex()==1)
		{
			SystemProperty.TABLE_FONT_SIZE=13;
		}
		else if(table_font_combo.getSelectedIndex()==2)
		{
			SystemProperty.TABLE_FONT_SIZE=14;
		}
		else if(table_font_combo.getSelectedIndex()==3)
		{
			SystemProperty.TABLE_FONT_SIZE=15;
		}
		
		SystemProperty.TABLE_FONT_TYPE=font_type_combo.getSelectedIndex();
	}
}




