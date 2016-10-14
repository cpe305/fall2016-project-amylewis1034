package arcade.src.main;

import java.awt.Graphics;

public interface Entity {
	
	public void tick();
	public void render(Graphics graphics);
	
	public double getXCoord();
	public double getYCoord();
}
