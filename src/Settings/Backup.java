package Settings;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Backup extends JPanel implements ActionListener
{
	private JTextField textField;
	JFileChooser chooser;
	JButton btnBrowse,btnRestoreData,btnBackupNow;
	private JTextField textField_1;
	private JButton btnBrowseFile;
	private JLabel lblSelectBackupFile;
	public Backup()
	{
		setBackground(Color.WHITE);
		setBorder(null);
		setLayout(null);
		setSize(379,390);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Back Up", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 359, 122);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblChooseBackupLocation = new JLabel("Choose backup Location");
		lblChooseBackupLocation.setBounds(10, 24, 173, 14);
		panel.add(lblChooseBackupLocation);
		
		textField = new JTextField();
		textField.setBounds(10, 49, 229, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		btnBrowse = new JButton("Browse..");
		btnBrowse.setBounds(249, 48, 89, 23);
		panel.add(btnBrowse);
		
		btnBackupNow = new JButton("Backup now");
		btnBackupNow.setBounds(134, 88, 89, 23);
		panel.add(btnBackupNow);
		
		
		 
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Restore", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 154, 359, 122);
		add(panel_1);
		panel_1.setLayout(null);
		
		btnRestoreData = new JButton("Restore Data");
		btnRestoreData.setBounds(127, 88, 112, 23);
		panel_1.add(btnRestoreData);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 52, 229, 20);
		panel_1.add(textField_1);
		
		btnBrowseFile = new JButton("Browse..");
		btnBrowseFile.setBounds(249, 51, 89, 23);
		panel_1.add(btnBrowseFile);
		
		lblSelectBackupFile = new JLabel("Select Backup file");
		lblSelectBackupFile.setBounds(10, 27, 173, 14);
		panel_1.add(lblSelectBackupFile);
		btnBrowse.addActionListener(this);
		btnRestoreData.addActionListener(this);
		btnBackupNow.addActionListener(this);
		btnBrowseFile.addActionListener(this);
	}

	String file_path="",restore_file="";
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnBrowse)	
		{
			JFileChooser chooser = new JFileChooser();
		    
		    chooser.setDialogTitle("Select Backup Location");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);
		    

		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		    {
		      textField.setText(chooser.getSelectedFile().toString());
		      file_path=chooser.getSelectedFile().toString();
		    } 
		    
		}
		else if(e.getSource()==btnBackupNow)
		{
			try
			{
				Date dNow=new Date();
			    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy@hh-mm-aa");
					
			        FileOutputStream fos = new FileOutputStream(file_path+"\\DataBackUp-"+ft.format(dNow)+"_TEEM_TEX.bkp");
			        System.out.println(file_path+"\\DataBackUp-"+ft.format(dNow)+"TEEM_TEX.bkp");
			        
					ZipOutputStream zos = new ZipOutputStream(fos);

					String file1Name = "src/Resources/configurations/Billingsystem.accdb";
					String file2Name = "src/Resources/security/encrypt.bill";
					String file3Name = "src/Resources/configurations/number.properties";
					String file4Name = "src/Resources/configurations/config.properties";
					

					addToZipFile(file1Name, zos);
					addToZipFile(file2Name, zos);
					addToZipFile(file3Name, zos);
					addToZipFile(file4Name, zos);
					

					zos.close();
					fos.close();
					JOptionPane.showMessageDialog(this, "Backup Success. Backup path:"+file_path, "Message", JOptionPane.INFORMATION_MESSAGE);
					   
				} catch (FileNotFoundException ex) 
				{
					ex.printStackTrace();
				} catch (IOException ex2)
				{
					ex2.printStackTrace();
				}
			
			catch(Exception ex)
			{
				final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
				JOptionPane.showMessageDialog(this,"Error while Backuping Data! - "+ex ,"Message",JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		
		else if(e.getSource()==btnBrowseFile)
		{
			JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new java.io.File("."));
		    chooser.setDialogTitle("Select Backup File");
		    chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.bkp", "bkp")); 

		    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		    {
		      textField_1.setText(chooser.getSelectedFile().toString());
		      file_path=chooser.getSelectedFile().toString();
		    }
		    
		}
		else if(e.getSource()==btnRestoreData)
		{

	        if(textField_1.getText()!=null && (!textField_1.getText().equals("")))
	        {
	        	try 
		        {
	        		int i=JOptionPane.showConfirmDialog(this, "                     Restoring data will completely erase the \nprevious data and Restores the Backuped data. Sure What to continue?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
	    			if(i==0)
	    			{
	    				unzip(file_path,".");
	    				JOptionPane.showMessageDialog(this, "Restore Success. Please Restart the Application to Apply Changes!", "Message", JOptionPane.INFORMATION_MESSAGE);
	    			}
	    			
		        } catch (Exception ex) 
		        {
		        	final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
					if (runnable != null) runnable.run();
		        	JOptionPane.showMessageDialog(this, ex, "Message", JOptionPane.ERROR_MESSAGE);
		        }
	        }
	        else
	        {
	        	final Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
				if (runnable != null) runnable.run();
	        	JOptionPane.showMessageDialog(this, "Please Choose the Backup File", "Message", JOptionPane.ERROR_MESSAGE);
	        }
	        
		}
		
	}
	
	
	
	
/************************************************Compression Functions*********************************************************************/
	public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException 
	{
	
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[512000];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}


	public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) 
        {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
    
        while (entry != null) 
        {
            String filePath = destDirectory + File.separator + entry.getName();
        
            if (!entry.isDirectory()) 
            {
                extractFile(zipIn, filePath);
            } 
            else
            {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
        System.out.println("unzip");
        
    }
    
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException
    {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[512000];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        System.out.println("extract");
        bos.close();
    }
}
