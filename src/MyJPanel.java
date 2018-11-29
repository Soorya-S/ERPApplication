import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.JPanel;

import Printer.PrintImage;

public class MyJPanel extends JPanel implements Printable {
  
    public static void main(String ar[])
    {
    	new MyJPanel();
    }
	public MyJPanel() 
    {
        initComponents();
    }
  
    private void initComponents() {
        this.setMaximumSize(new Dimension(270,170));
        this.setMinimumSize(new Dimension(270,170));
        this.setPreferredSize(new Dimension(270,170));
        this.setBackground(Color.GRAY);
        
    }
  
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawString("Sample drawstring", 20, 20);
        g2.drawString("Bottom line", 20, 150);
    }
  
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException 
        {
    	
            /* get component width and table height */
            Dimension dimension = this.getSize();
            double compWidth = dimension.width;
            double compHeight = dimension.height;
            System.out.println("Comp width: " + compWidth);
            System.out.println("Comp height: " + compHeight);
             
            Paper card = pageFormat.getPaper();
            card.setImageableArea(0, 0, 153, 243);
            card.setSize(153,243);
             
            pageFormat.setPaper(card);
            pageFormat.setOrientation(PageFormat.LANDSCAPE);
  
            /* get page width and page height */
            //double pageWidth = pageFormat.getImageableWidth();
            //double pageHeight = pageFormat.getImageableHeight();
            //double scale = pageWidth / compWidth;
            //double scale = compWidth / pageWidth;
            //System.out.println("Page width: " + pageWidth);
            //System.out.println("Page height: " + pageHeight);
            //System.out.println("Scale: " + scale);
             
            /* calculate the no. of pages to print */
            //final int totalNumPages= (int)Math.ceil((scale * compHeight) / pageHeight);
            if (pageIndex > 3)
            {
                System.out.println("Total pages: " + pageIndex);
                return(NO_SUCH_PAGE);
            } // end if
            else
            {
                Graphics2D g2d = (Graphics2D)g;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                System.out.println("Coords: " + pageFormat.getImageableX() + ", " + pageFormat.getImageableY());
                g2d.translate( 0f, 0f );
                //g2d.translate( 0f, -pageIndex * pageHeight );
                //g2d.scale( scale, scale );
                this.paint(g2d);
                return(PAGE_EXISTS);
            } // end else
        } // end print()
  
}