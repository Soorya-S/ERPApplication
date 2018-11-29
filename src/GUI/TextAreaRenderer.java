package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

public class TextAreaRenderer extends JScrollPane implements TableCellRenderer,TextListener
{
   JTextArea textarea;
  
   public TextAreaRenderer() {
      textarea = new JTextArea();
      textarea.setLineWrap(true);
      textarea.setWrapStyleWord(true);
      getViewport().add(textarea);
      
   }
  
   public Component getTableCellRendererComponent(JTable table, Object value,
                                  boolean isSelected, boolean hasFocus,
                                  int row, int column)
   {
	   if (hasFocus) 
	   {
       // this cell is the anchor and the table has the focus
       this.setBackground(Color.blue);
       this.setForeground(Color.green);
      // textarea.addAncestorListener(this); 
	   }
	   else 
	   {
		   this.setForeground(Color.black);
	   }

      return this; 
   }

@Override
public void textValueChanged(TextEvent e) {
	// TODO Auto-generated method stub
	
}
}
 