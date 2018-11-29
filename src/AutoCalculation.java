
import java.awt.Color;
import java.awt.Component;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import javax.swing.event.*;







@SuppressWarnings("serial")
public class AutoCalculation extends JFrame {
	public static JLabel out;
	
	        @SuppressWarnings("unused")
			public static void main(String[] args)
		    {
		        JFrame frame = new JFrame("Amount");
		         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		         
		         frame.setSize(100, 300);
		         frame.setVisible(true);
		        String colHdrA = "AMOUNT";               
		        TableofSums tableofSums = new TableofSums(10, colHdrA);
		        int sumValue = tableofSums.getSumValues();
		        String sumValueS = String.valueOf(sumValue);
		        JPanel thePanel = new JPanel();
		       
		        
		        thePanel.add(new JScrollPane(tableofSums));
		         out=new JLabel("OUTPUT");
		        frame.getContentPane().add(out);
		        frame.getContentPane().add(thePanel);
		        thePanel.add(out);
		        frame.pack();
		        frame.setVisible(true);
		      
		  
		    }
	      
	}

	

@SuppressWarnings("serial")
  class TableofSums extends JPanel implements TableModelListener
{
    public static void main(String[] args)
     {      
         SwingUtilities.invokeLater(new Runnable() 
            {
                public void run() 
                  {           
        JFrame frame = new JFrame("Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(208, 230);
           String colHdrA = "AMOUNT";                
           TableofSums tableofSums = new TableofSums(10, colHdrA);
            frame.setContentPane(tableofSums);
            JPanel myp=new JPanel();
            frame.setContentPane(myp);
          frame.pack();
          frame.setVisible(true);
                   
              }
           });
    }
     
    private int noOfRows = 0;
    private JTable table;
    private String colName = null;
    private int sumValues = 0;
     
    public TableofSums(int noOfRows, String colName)
    {
        this.noOfRows = noOfRows;
        this.colName = colName;
        makeTable();        
    }
     
    private void makeTable()
    {
        Object columns[] = new Object[] { this.colName };
         
        Object[][] data = new Object[noOfRows][1];
         
        for (int i = 0; i<noOfRows; i++)
           {              
              data[i][0] = new Integer(0);            
           }
        DefaultTableModel model = new DefaultTableModel(data, columns);
           model.addTableModelListener(this);
           table = new JTable(model)
           {
              @Override
              public boolean isCellEditable(int row, int column) 
              {
                  switch (column) 
                  {
                    case 0: return true;
                  }
                return false;
              }
               
              @Override
           public Class<?> getColumnClass(int columnIndex) 
              {
                 switch (columnIndex) 
                 {
                    case 0: return Integer.class;
                 }
                 return null;
              }
           };
           table.setRowHeight(25);
            this.add(new JScrollPane(table));
    }
     public void tableChanged(TableModelEvent e)
                    {
     // System.out.println(e.getSource());
      if(e.getType() == TableModelEvent.UPDATE)
      {
          int column = e.getColumn();
          TableModel model = table.getModel();
          sumValues = 0;
          for (int i=0; i<noOfRows; i++)
          {
            int intValue = (Integer)model.getValueAt(i, column);
            sumValues += intValue;
          }
         //System.out.println (sumValues);
      AutoCalculation.out.setText(Integer.toString(sumValues));
        
        
      }
       
     }
      
     
     public int getSumValues()
     {
         return sumValues;
     }
}

