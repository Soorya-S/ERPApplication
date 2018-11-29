package GUI;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

class JDateChooserEditor extends DefaultCellEditor
{
	  public JDateChooserEditor(JCheckBox checkBox)
	  {
	    super(checkBox);
	   
	  }
	  
	  JDateChooser date;
	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) 
	  {
		  	 
			 date = new JDateChooser();
			 date.setDateFormatString("dd-MM-yyyy");
			 return date;
	  }

	  public Object getCellEditorValue() 
	  {
	    return new String(((JTextField)date.getDateEditor().getUiComponent()).getText());
	  }

	  public boolean stopCellEditing()
	  {
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}
