package arcade.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	private double x_coord;
	private double y_coord;
	private double x_vel = 0.0;
	private double y_vel = 0.0;
	private int width;
	private int height;
	
	private BufferedImage spaceship;
	
	public Player(double x_coord, double y_coord, SpaceInvaders siGame) {
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		
		width = siGame.getWidth();
		height = siGame.getHeight();
			
		BufferedImageLoader buff_loader = new BufferedImageLoader();
		try {
			spaceship = buff_loader.loadImage("/spaceship.png");
			spaceship = buff_loader.createResizedCopy(spaceship, 40, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public double getXCoord() {
		return x_coord;
	}
	
	public void setXCoord(double x_coord) {
		this.x_coord = x_coord;
	}
	
	public void setXVel(double x_vel) {
		this.x_vel = x_vel;
	}
	
	public double getYCoord() {
		return y_coord;
	}
	
	public void setYCoord(double y_coord) {
		this.y_coord = y_coord;
	}
	
	public void setYVel(double y_vel) {
		this.y_vel = y_vel;
	}
	
	public void tick() {
		x_coord += x_vel;
		y_coord += y_vel;
		
		if (x_coord <= 0)
			x_coord = 0;
		if (x_coord >= width - 40)
			x_coord = width - 40;
		if (y_coord <= 0)
			y_coord = 0;
		if (y_coord >= height - 50)
			y_coord = height - 50;
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(spaceship, (int)x_coord, (int)y_coord, null);
	}
}
