package Printer;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;

import GUI.Cheque;
import GUI.DeliveryNote;
import GUI.LabourBill;
import GUI.Voucher;
import Settings.SystemProperty;

public class PrintImage 
{
  public static int REF;
  public static int OR=0;
  public static int CHEQUE;
  
  public PrintImage(String img) throws ClassCastException
  {
	 
	  PrintRequestAttributeSet pras=null;
	  PrintService pss[]=new PrintService[10];
	  PrintService ps=null;
	  DocAttributeSet das =null;
	  try
	  {
		  das= new HashDocAttributeSet();
		  pras = new HashPrintRequestAttributeSet();
		  pras.add(new Copies(SystemProperty.PRINTER_COPIES));
		  	System.out.println(SystemProperty.PRINTER_COPIES);
		  	if(SystemProperty.PRINTER_ORIENTATION.equals("Landscape"))
		  	{
		  		pras.add(OrientationRequested.LANDSCAPE);
		  	}
		  	else
		  	{
		  		pras.add(OrientationRequested.PORTRAIT);
		  	}
		  	
		  	if(OR!=0)
		  	{
		  		pras.add(OrientationRequested.LANDSCAPE);
		  		OR=0;
		  	}
		 
		  	if(SystemProperty.PRINTER_PAPER_SIZE.equals("A5"))
			{
		  		das.add(MediaSizeName.ISO_A5);	 
			}
		  	else if(SystemProperty.PRINTER_PAPER_SIZE.equals("A3"))
			{
		  		das.add(MediaSizeName.ISO_A3);	
			}
		  	else
			{
		  		das.add(MediaSizeName.ISO_A4);	
			}
		  	
		  	if(CHEQUE==1)
		  	{
		  	das.add(new MediaPrintableArea(0, 0, 203,93,
		                MediaPrintableArea.MM));
		  	das.add(MediaSizeName.ISO_A4);	
		  	pras.add(OrientationRequested.PORTRAIT);
		  	 CHEQUE=0;
		  	}
		  		  
	  }
	  catch(ClassCastException e){System.out.println(e);}

	  pss= PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.GIF, pras);
	  
    
	try
    {
    	ps = pss[SystemProperty.PRINTER_NAME];
    	System.out.println("Printing to 1" + ps);
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
    	System.out.println(e);
    	if(pss.length==1)
    	{
    		ps = pss[0];
    	}
    	else
    	{
    	final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
		if (runnable != null) runnable.run();
    	JOptionPane.showMessageDialog(null,"                            Oops! There was a problem with printer\nPlease Try reconnect your Printer and Restart the application to solve this issue","Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    DocPrintJob job=null;
    
    job = ps.createPrintJob();
    
  
    FileInputStream fin;
    
    try 
	{
		fin = new FileInputStream(img);
		Doc doc = new SimpleDoc(fin, DocFlavor.INPUT_STREAM.GIF, das);
		job.print(doc, pras);
		fin.close();
		if(REF==0)
		{
			LabourBill.clearFields();
			if(PreviewLabourBill.FRAME!=null)
				PreviewLabourBill.FRAME.dispose();
		}
		else if(REF==1)
		{
			DeliveryNote.clearFields();
			if(PreviewDeliveryNote.FRAME!=null)
				PreviewDeliveryNote.FRAME.dispose();
		}
		else if(REF==2)
		{
			Voucher.clearFields();
			if(PreviewVoucher.FRAME!=null)
				PreviewVoucher.FRAME.dispose();
		}
		else if(REF==3)
		{
			Cheque.clearFields();
		}
		
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
   
  }
  
  
  
  
}         
    
    
    