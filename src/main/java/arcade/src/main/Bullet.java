package arcade.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet implements Entity {
	
	private double x_coord;
	private double y_coord;
	private BufferedImage bullet;
	
	public Bullet(double x_coord, double y_coord) {
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		
		BufferedImageLoader buff_loader = new BufferedImageLoader();
		try {
			bullet = buff_loader.loadImage("/bullet.png");
			bullet = buff_loader.createResizedCopy(bullet, 3, 7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public double getXCoord() {
		return x_coord;
	}
	
	public double getYCoord() {
		return y_coord;
	}
	
	public void tick() {
		y_coord -= 10;
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(bullet, (int)x_coord, (int)y_coord, null);
	}
}
