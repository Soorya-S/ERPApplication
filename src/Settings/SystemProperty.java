package Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class SystemProperty 
{
	public static Boolean DELIVERY_SL_NO_AUTO;
	public static Boolean VOUCHER_SL_NO_AUTO;
	
	public static Boolean PRINTER_PREVIEW_ENABLED;
	public static int PRINTER_NAME;
	public static int PRINTER_COPIES;
	public static String PRINTER_ORIENTATION;
	public static String PRINTER_PAPER_SIZE;
	
	public static int VOUCHER_NO;
	public static int DELIVERY_NO;
	public static int LABOUR_NO;
	public static int TAX_INOVOICE_NO;
	
	public static int TABLE_FONT_SIZE;
	public static int TABLE_FONT_TYPE;
	public static int PRINT_FONT_SIZE;
	
	
  public  SystemProperty()
  {
  }
  
  public static void getProperties()
  {
	  
	  try 
	  {
			File file = new File("src/Resources/configurations/config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fileInput);
			fileInput.close();
			
			DELIVERY_SL_NO_AUTO=Boolean.valueOf(prop.getProperty("DELIVERY_SL_NO_AUTO"));
			VOUCHER_SL_NO_AUTO=Boolean.valueOf(prop.getProperty("VOUCHER_SL_NO_AUTO"));
			
			PRINTER_PREVIEW_ENABLED=Boolean.valueOf(prop.getProperty("PRINTER_PREVIEW_ENABLED"));
			
			PRINTER_NAME=Integer.parseInt(prop.getProperty("PRINTER_NAME"));
			PRINTER_COPIES=Integer.parseInt(prop.getProperty("PRINTER_COPIES"));
			PRINTER_ORIENTATION=prop.getProperty("PRINTER_ORIENTATION");
			PRINTER_PAPER_SIZE=prop.getProperty("PRINTER_PAPER_SIZE");
			PRINT_FONT_SIZE=Integer.parseInt(prop.getProperty("PRINT_FONT_SIZE"));
			TABLE_FONT_SIZE=Integer.parseInt(prop.getProperty("TABLE_FONT_SIZE"));
			TABLE_FONT_TYPE=Integer.parseInt(prop.getProperty("TABLE_FONT_TYPE"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  
  public static void saveProperties()
  {
	  Properties prop = new Properties();
		OutputStream output = null;
		
		try
		{

			output = new FileOutputStream("src/Resources/configurations/config.properties");
			
			
			prop.setProperty("DELIVERY_SL_NO_AUTO", DELIVERY_SL_NO_AUTO.toString());
			
			prop.setProperty("VOUCHER_SL_NO_AUTO", VOUCHER_SL_NO_AUTO.toString());
			
			prop.setProperty("PRINTER_PREVIEW_ENABLED",PRINTER_PREVIEW_ENABLED.toString());
			
			
			prop.setProperty("PRINTER_NAME", String.valueOf(PRINTER_NAME));
			prop.setProperty("PRINTER_COPIES", String.valueOf(PRINTER_COPIES));
			prop.setProperty("PRINTER_ORIENTATION", PRINTER_ORIENTATION);
			prop.setProperty("PRINTER_PAPER_SIZE",PRINTER_PAPER_SIZE);
			
			prop.setProperty("TABLE_FONT_SIZE",String.valueOf(TABLE_FONT_SIZE));
			prop.setProperty("TABLE_FONT_TYPE",String.valueOf(TABLE_FONT_TYPE));
			prop.setProperty("PRINT_FONT_SIZE",String.valueOf(PRINT_FONT_SIZE));
			
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
  }
  
  
  public static void getNumber()
  {
	  try 
	  {
	    	File file = new File("src/Resources/configurations/number.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(fileInput);
			
			
			
			VOUCHER_NO=Integer.parseInt(prop.getProperty("VOUCHER_NO"));
			LABOUR_NO=Integer.parseInt(prop.getProperty("LABOUR_NO"));
			DELIVERY_NO=Integer.parseInt(prop.getProperty("DELIVERY_NO"));
			TAX_INOVOICE_NO=Integer.parseInt(prop.getProperty("TAX_INVOICE_NO"));;
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  
  public static void setNumber()
  {
	  Properties prop = new Properties();
		OutputStream output = null;
		
		try
		{

			output = new FileOutputStream("src/Resources/configurations/number.properties");
			prop.setProperty("VOUCHER_NO",String.valueOf(VOUCHER_NO));
			prop.setProperty("LABOUR_NO",String.valueOf(LABOUR_NO));
			prop.setProperty("DELIVERY_NO",String.valueOf(DELIVERY_NO));
			prop.setProperty("TAX_INVOICE_NO",String.valueOf(TAX_INOVOICE_NO));
			
			prop.store(output, null);
			
		} catch (IOException io) 
		{
			io.printStackTrace();
		} 
		finally
		{
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	  
  }
  
}