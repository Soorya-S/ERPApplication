package Printer;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ComponentSnapShot {
	BufferedImage image2 ;
    public static BufferedImage getScreenShot(Component component) 
    {

        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        // paints into image's Graphics
        component.paint(image.getGraphics());
        return image;
    }

    public static void getSaveSnapShot(Component component, String fileName) throws Exception
    {
        BufferedImage img = getScreenShot(component);
        // write the captured image as a PNG
        ImageIO.write(img, "png", new File(fileName));
    }

    public ComponentSnapShot(Component panel)
    {
        
        try
        {
			getSaveSnapShot(panel, "src/Resources/images/panel.png");
			 PrintImage p=new PrintImage("src/Resources/images/panel.png");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
       
    }
}
