package arcade.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	private LinkedList<Entity> entity_list = new LinkedList<Entity>();
	
	SpaceInvaders siGame;
	Entity entity;
	
	Random rand_num = new Random();
	
	public Controller(SpaceInvaders siGame) {
		this.siGame = siGame;
	}
	
	public void addEntity(Entity ent) {
		entity_list.add(ent);
	}
	
	public void removeEntity(Entity ent) {
		entity_list.remove(ent);
	}
	
	public void addEnemy(int num_enemy) {
		for (int i = 0; i < num_enemy; i++) {
			addEntity(new Enemy(rand_num.nextInt(siGame.WIDTH * siGame.SCALE), -10, siGame));
		}
	}
	
	public void tick() {
		for (int i = 0; i < entity_list.size(); i++) {
			entity = entity_list.get(i);
			entity.tick();
		}
	}
	
	public void render(Graphics graphics) {
		for (int i = 0; i < entity_list.size(); i++) {
			entity = entity_list.get(i);
			entity.render(graphics);
		}
	}
}
