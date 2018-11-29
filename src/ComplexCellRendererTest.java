import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.*;

  public class ComplexCellRendererTest
  {
  private static boolean DEBUG = true;
  private JRadioButton r1 = new JRadioButton("scrollRectToVisible");
  private JRadioButton r2 = new JRadioButton("setViewPosition");

  public JComponent makeUI() 
  {
    String[] columnNames = {"AAA", "BBB"};
    Object[][] data = {
      {new Test("1", "aaaaaaaaaaaaaaaaa\nbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"), "4"},
      {new Test("2", "1234567890\nabcdefghijklmdopqrstuvwxyz"), "5"},
      {new Test("3", "ccccccccccccccccccccccccccccccccccc\ndddddddddddd"), "6"},
    };
    
    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
      @Override public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    
    final JTable table = new JTable(model);
    
    table.getTableHeader().setReorderingAllowed(false);
    table.setRowSelectionAllowed(true);
    table.setFillsViewportHeight(true);
    table.setShowVerticalLines(false);
    table.setIntercellSpacing(new Dimension(0,1));
    table.setRowHeight(56);
    for(int i=0; i<table.getColumnModel().getColumnCount(); i++) {
      TableColumn c = table.getColumnModel().getColumn(i);
      c.setCellRenderer(new ComplexCellRenderer());
      c.setMinWidth(50);
    }

    ActionListener al = new ActionListener() 
    {
      @Override public void actionPerformed(ActionEvent e) {
        DEBUG = (e.getSource()==r1);
        table.repaint();
      }
    };
    Box box = Box.createHorizontalBox();
    ButtonGroup bg = new ButtonGroup();
    box.add(r1); bg.add(r1); r1.addActionListener(al);
    box.add(r2); bg.add(r2); r2.addActionListener(al);
    r1.setSelected(true);

    JPanel p = new JPanel(new BorderLayout());
    p.add(box, BorderLayout.NORTH);
    p.add(new JScrollPane(table));
    return p;
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override public void run() {
        createAndShowGUI();
      }
    });
  }
  
  public static void createAndShowGUI() 
  {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.getContentPane().add(new ComplexCellRendererTest().makeUI());
    f.setSize(320, 240);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }

  static class ComplexCellRenderer extends JPanel implements TableCellRenderer {
    private final JTextArea textArea = new JTextArea(2, 999999);
    private final JLabel label = new JLabel();
    private final JScrollPane scroll = new JScrollPane();
    public ComplexCellRenderer() 
    {
      super(new BorderLayout(0,0));

      scroll.setViewportView(textArea);
      scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scroll.setBorder(BorderFactory.createEmptyBorder());
      scroll.setViewportBorder(BorderFactory.createEmptyBorder());
      scroll.setOpaque(false);
      scroll.getViewport().setOpaque(false);
      textArea.setBorder(BorderFactory.createEmptyBorder());
      //textArea.setMargin(new Insets(0,0,0,0));
      textArea.setForeground(Color.RED);
      textArea.setOpaque(false);
      label.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.GRAY));
      setBackground(Color.WHITE);
      setOpaque(true);
      add(label, BorderLayout.NORTH);
      add(scroll);
    }
    @Override public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
      Test test;
      if(value instanceof Test) {
        test = (Test)value;
      } else {
        String title = value.toString();
        Test t = (Test)table.getModel().getValueAt(row, 0);
        test = new Test(title, t.text);
      }
      label.setText(test.title);
      textArea.setText(test.text);
      Rectangle cr = table.getCellRect(row, column, false);
      if(DEBUG) {
        //Unexplained flickering on first row?
        textArea.scrollRectToVisible(cr);
      } else {
        //Work fine for me (JDK 1.7.0_21, Windows 7 64bit):
        scroll.getViewport().setViewPosition(cr.getLocation());
      }
      if(isSelected) {
        setBackground(Color.ORANGE);
      } else {
        setBackground(Color.WHITE);
      }
      return this;
    }
  }


}
  
class Test
{
  public final String title;
  public final String text;
  //public final Icon icon;
  public Test(String title, String text) {
    this.title = title;
    this.text  = text;
  }
}