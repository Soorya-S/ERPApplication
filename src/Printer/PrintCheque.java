package Printer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import GUI.DeliveryNote;
import GUI.LabourBill;
import GUI.Voucher;
import Settings.SystemProperty;

import java.awt.font.*;
import java.io.FileInputStream;

public class PrintCheque extends JFrame 
{

JPanel panel;
JLabel name,date,rupees,rs;

	public PrintCheque(String data[],int bank) 
	{
		panel=new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel.setBounds(0, 0, 771,351);
		setBounds(100, 100,  771,351);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255));
		//panel.setOpaque(false);
		
		JLabel label[]=new JLabel[5];
	
		for(int i=0;i<5;i++)
		{
			label[i]=new JLabel();
			label[i].setText(data[i]);
			//System.out.println(label[i].getText());
			label[i].setFont(new Font(label[i].getFont().getName(),Font.PLAIN,17));
		}
		label[2].setText(label[2].getText()+"/-");
		if(bank==0)
		{
			label[0].setFont(new Font(label[0].getFont().getName(),Font.PLAIN,14));
			label[0].setAlignmentX(SwingConstants.CENTER);
			label[0].setBounds(597,10,180,15);
			label[1].setBounds(75,48,500,22);
			label[2].setBounds(605,114,150,22);
			label[3].setBounds(150,85,397,22);
			label[4].setBounds(60,115,485,22);
			System.out.println("Printing SBI");
		}
		else if(bank==1)
		{
			label[0].setFont(new Font(label[0].getFont().getName(),Font.PLAIN,14));
			label[0].setAlignmentX(SwingConstants.CENTER);
			label[0].setBounds(590,14,180,15);
			label[1].setBounds(75,47,500,22);
			label[2].setBounds(605,118,150,22);
			label[3].setBounds(108,84,397,22);
			label[4].setBounds(37,113,485,22);
			System.out.println("Printing HDFC");
		}
		else if(bank==2)
		{
			label[0].setFont(new Font(label[0].getFont().getName(),Font.PLAIN,14));
			label[0].setAlignmentX(SwingConstants.TOP);
			label[0].setBounds(548,0,180,11);
			label[1].setBounds(72,32,500,22);
			label[2].setBounds(600,85,150,22);
			label[3].setBounds(108,71,397,22);
			label[4].setBounds(37,98,485,22);
			System.out.println("Printing KOTAK");
		}
		
		for(int i=0;i<5;i++)
		{
			panel.add(label[i]);
		}
		PrintImage.CHEQUE=1;
		ComponentSnapShot s=new ComponentSnapShot(panel);
		//printJavaComponent() ;
		}
	
	
	
	
		public void printJavaComponent() 
		{
		    PrinterJob job = PrinterJob.getPrinterJob();
		    job.setJobName("Print Java Component");
		 
		    job.setPrintable (new Printable() {    
		        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
		            if (pageIndex > 0) {
		                return(NO_SUCH_PAGE);
		            } else {
		                Graphics2D g2d = (Graphics2D)g;
		                g2d.translate(pageFormat.getImageableX(), 
		                pageFormat.getImageableY());
		              
		                Paper card = pageFormat.getPaper();
		                card.setImageableArea(0, 0, 800,400);
		               
		                card.setSize(800,400);
		                 
		                pageFormat.setPaper(card);
		                pageFormat.setOrientation(PageFormat.PORTRAIT);
		                panel.paint(g2d);
		                return(PAGE_EXISTS); 
		            }
		        }
		    });
		         
		    if (job.printDialog()) {
		        try {
		        	
		            job.print();
		        } catch (PrinterException e) {
		            System.err.println(e.getMessage()); 
		        }
		    }
		}

	
	
	public void printPanel(JPanel panel)
	{
		  PrintRequestAttributeSet pras=null;
		  PrintService pss[];
		  PrintService ps=null;
		  DocAttributeSet das =null;
		  try
		  {
			  das= new HashDocAttributeSet();
			  pras = new HashPrintRequestAttributeSet();
			  pras.add(new Copies(SystemProperty.PRINTER_COPIES));
			  	
			  		pras.add(OrientationRequested.PORTRAIT);
			  	  		das.add(MediaSizeName.ISO_A4);	
				 das.add(new MediaPrintableArea(0, 0, 203,93,
			                MediaPrintableArea.MM));			  		  
		  }
		  catch(ClassCastException e){System.out.println(e);}
	    
		  pss= PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras);
	    
	    try
	    {
	    	ps = pss[SystemProperty.PRINTER_NAME];
	    	
	    }
	    catch(ArrayIndexOutOfBoundsException e)
	    {
	    	final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
			if (runnable != null) runnable.run();
	    	JOptionPane.showMessageDialog(null,"                            Oops! There was a problem with printer\nPlease Try reconnect your Printer and Restart the application to solve this issue","Error",JOptionPane.ERROR_MESSAGE);
	    }
	    
	    
	    System.out.println("Printing to " + ps);
	    DocPrintJob job = ps.createPrintJob();
	    FileInputStream fin;
	    try 
		{
		//	fin = new FileInputStream(img);
			//Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, das);
		//	job.print(panel, pras);
		//	fin.close();

			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	   
	  }
	  
	}