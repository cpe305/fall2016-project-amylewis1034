package arcade.src.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage buffered_image;
	
	public BufferedImage loadImage(String path) {
		try {
			buffered_image = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffered_image;			
	}
	
	public BufferedImage createResizedCopy(BufferedImage original_image, int scaled_width, int scaled_height) {
    	BufferedImage scaled_image = new BufferedImage(scaled_width, scaled_height, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = scaled_image.createGraphics();
    	
    	g.drawImage(original_image, 0, 0, scaled_width, scaled_height, null); 
    	g.dispose();
    	
    	return scaled_image;
    }
}
