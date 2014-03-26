package tsb.EcoHunter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import tsb.EcoHunter.entity.mob.Player;
import tsb.EcoHunter.graphics.Screen;
import tsb.EcoHunter.input.Keyboard;
import tsb.EcoHunter.level.Level;
import tsb.EcoHunter.level.RandomLevel;
/**
 * 
 * @author Timothy the Scholar
 * @version 1.0
 */
public class Game extends Canvas implements Runnable {
	private JFrame frame;
	private Thread thread;
	boolean running = false;
	public static String title = "EcoHunter 3000";

	// Create a Screen object form the Screen class
	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;

	public static int width = 300;
	public static int height = 300 / 16 *9;
	public static int scale = 3;

	public int xx = 0, yy = 0;
	
	// Implemented to timeout the game after n seconds
	public long startTime, currTime;

	// Create an image to be drawn on the screen
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	// Method to draw on said image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	/**
	 * Constructs a Game object. Creates objects necessary for key input, 
	 * displaying things on the screen, and having a window open.
	 */
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		key = new Keyboard();
		addKeyListener(key);
		
		screen = new Screen(width, height);
		frame = new JFrame();
		level = Level.level1;
		player = new Player(0,0, key);
		level.loadMobs();
		
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * This method is the backbone of the game. It orchestrates really everything
	 * else. 
	 */
	@Override
	public void run() {
		// Init a system to limit frames per second
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " fps: " + frames);
				updates = 0;
				frames = 0;
			}
		}

	}
	/**
	 * Minor updates to the game's status are made with this method
	 */
	public void update() {
		key.update();
		player.update();
		level.update();
		currTime = System.currentTimeMillis();
//		if(currTime - startTime > 30000){
//			System.exit(0);
//		}
	}
	
	/**
	 * Renders the current game status to the screen. Handles the buffer strategy 
	 * and other related items of extreme technicality.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width/2;
		int yScroll = player.y - screen.height/2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);

		// Set the current pixel array to the Screen class's
		for (int a = 0; a < pixels.length; a++) {
			pixels[a] = screen.pixels[a];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ariel", Font.PLAIN, 40));
		g.drawString("X: " + player.x + " Y: " + player.y,10, 40);
		g.dispose();
		bs.show();
	}
	/**
	 * Starts the game and initiates a new thread named "Display"
	 */
	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	/**
	 * Stops the game and disposes thread.
	 */
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
