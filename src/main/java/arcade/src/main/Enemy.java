package arcade.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy implements Entity {
	private double x_coord;
	private double y_coord;
	private BufferedImage enemy;
	private SpaceInvaders siGame;
	
	Random rand_num = new Random();
	
	public Enemy(double x_coord, double y_coord, SpaceInvaders siGame) {
		this.x_coord = x_coord;
		this.y_coord = y_coord;
		this.siGame = siGame;
		
		BufferedImageLoader buff_loader = new BufferedImageLoader();
		try {
			enemy = buff_loader.loadImage("/enemy.png");
			enemy = buff_loader.createResizedCopy(enemy, 80, 45);
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
		y_coord += 2;
		
		if (y_coord > siGame.HEIGHT * siGame.SCALE) {
			y_coord = 0;
			x_coord = rand_num.nextInt(siGame.WIDTH * siGame.SCALE);
		}
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(enemy, (int)x_coord, (int)y_coord, null);
	}
}
