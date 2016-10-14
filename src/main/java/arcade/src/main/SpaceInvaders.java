package arcade.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class SpaceInvaders extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public final String TITLE = "Space Invaders";
	public static final int HEIGHT = 480;
	public static final int WIDTH = 640;
	public static final int SCALE = 2;
	public static final int BILLION = 1000000000;
	
	private Thread thread;
	private boolean is_running = false;
	private boolean is_shooting = false;
	private BufferedImage image_buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	private BufferedImage space_background = null;
	
	private Player player;
	private Controller controller;
	
	private int num_enemy = 1;
	private int num_enemy_killed = 0;
	
	public int getNum_enemy() {
		return num_enemy;
	}

	public void setNum_enemy(int num_enemy) {
		this.num_enemy = num_enemy;
	}

	public int getNum_enemy_killed() {
		return num_enemy_killed;
	}

	public void setNum_enemy_killed(int num_enemy_killed) {
		this.num_enemy_killed = num_enemy_killed;
	}

	public int getWidth() {
		return WIDTH * SCALE;
	}
	
	public int getHeight() {
		return HEIGHT * SCALE;
	}
	
	public void init() {
		requestFocus();
		addKeyListener(new KeyInput(this));
		
		BufferedImageLoader buff_loader = new BufferedImageLoader();
		try {
			space_background = buff_loader.loadImage("/space_background.png");
			space_background = buff_loader.createResizedCopy(space_background, 1400, 1100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		player = new Player(640, 700, this);
		controller = new Controller(this);
	}
	
	public void keyPressed(KeyEvent event) {
		int key_pressed = event.getKeyCode();
		
		if (key_pressed == KeyEvent.VK_UP) {
			player.setYVel(-5);
		}
		else if (key_pressed == KeyEvent.VK_DOWN) {
			player.setYVel(5);
		}
		else if (key_pressed == KeyEvent.VK_LEFT) {
			player.setXVel(-5);
		}
		else if (key_pressed == KeyEvent.VK_RIGHT) {
			player.setXVel(5);
		}	
		else if (key_pressed == KeyEvent.VK_SPACE && is_shooting == false) {
			controller.addEntity(new Bullet(player.getXCoord() + 19, player.getYCoord() + 5));
			is_shooting = true;
		}
	}
	
	public void keyReleased(KeyEvent event) {
		int key_pressed = event.getKeyCode();
		
		if (key_pressed == KeyEvent.VK_UP) {
			player.setYVel(0);
		}
		else if (key_pressed == KeyEvent.VK_DOWN) {
			player.setYVel(0);
		}
		else if (key_pressed == KeyEvent.VK_LEFT) {
			player.setXVel(0);
		}
		else if (key_pressed == KeyEvent.VK_RIGHT) {
			player.setXVel(0);
		}		
		else if (key_pressed == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	}
	
	private synchronized void start() {
		if (is_running)
			return;

		is_running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if (is_running == false)
			return;
		
		int status = 0;
		is_running = false;
		
		try {
			thread.join();
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(status);
		
	}
	
	private void tick() {
		player.tick();
		controller.tick();
	}
	
	private void render() {
		BufferStrategy b_strategy = this.getBufferStrategy();
		if (b_strategy == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = b_strategy.getDrawGraphics();
		
		graphics.drawImage(image_buffer, 0, 0, getWidth(), getHeight(), this);
		graphics.drawImage(space_background, -50, 0, this);
		player.render(graphics);
		controller.render(graphics);
		
		graphics.dispose();
		b_strategy.show();
	}
		
	public void run() {
		init();
		
		long prev_time = System.nanoTime();
		final double ticks = 60.0;
		double nano_secs = BILLION / ticks;
		double time_passed = 0;
		
		while (is_running) {
			long current_time = System.nanoTime();
			time_passed += (current_time - prev_time) / nano_secs;
			prev_time = current_time;
			
			if (time_passed >= 1) {
				time_passed--;
				tick();
			}
			
			render();
		}
		stop();
	}
	
	public static void main(String args[]) {
		SpaceInvaders siGame = new SpaceInvaders();
		
		siGame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		siGame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		siGame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame j_frame = new JFrame(siGame.TITLE);
		j_frame.add(siGame);
		j_frame.pack();
		j_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j_frame.setLocationRelativeTo(null);
		j_frame.setVisible(true);
		j_frame.setResizable(false);	
		
		siGame.start();
	}
}
