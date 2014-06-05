package okushama.engine.core;

import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import okushama.engine.util.Timer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

public class Engine {

	private boolean isRunning = false;
	private Timer timer = new Timer();
	private int WINDOW_WIDTH, WINDOW_HEIGHT, SCALE_FACTOR;
	private String displayName, windowIcon;
	private ArrayList<ITicker> tickers = new ArrayList<ITicker>();

	public Engine(String n, int width, int height, int scaleFactor, String i) {
		try {
			displayName = n;
			WINDOW_WIDTH = width;
			WINDOW_HEIGHT = height;
			SCALE_FACTOR = scaleFactor;
			windowIcon = i;
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() throws Exception {
		isRunning = true;
		Display.setTitle(displayName);
		setWindowIcon(windowIcon);
		setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, SCALE_FACTOR);
		Display.create();
		setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, SCALE_FACTOR);

	}

	public void setDisplayMode(int w, int h, int scale) {
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0.0, w / scale, 0.0, h / scale, -1.0, 1.0);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glViewport(0, 0, Display.getDisplayMode().getWidth(), Display
					.getDisplayMode().getHeight());
		} catch (Exception e) {

		}
	}

	public void setWindowIcon(String icon) {
		try {
			Display.setIcon(new ByteBuffer[] { new ImageIOImageData()
					.imageToByteBuffer(ImageIO.read(new File(icon)), false,
							false, null) });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTicker(ITicker t) {
		if (!tickers.contains(t)) {
			tickers.add(t);
		}
	}

	public void run() {
		timer.start();
		for (int i = 0; i < tickers.size(); i++) {
			if (tickers.get(i) instanceof LogicTicker) {
				((LogicTicker) tickers.get(i)).start();
			}
			// tickers.get(i).onRenderTick(delta);
		}
		while (true) {
			float delta = timer.getDelta() / 10;
			Display.update();
			timer.updateFPS();
			glClear(GL_COLOR_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			for (int i = 0; i < tickers.size(); i++) {
				if (!(tickers.get(i) instanceof LogicTicker)) {
					if(tickers.get(i) instanceof Input){
						tickers.get(i).onLogicTick(delta);
					}else{
						tickers.get(i).onRenderTick(delta);
					}
				}
			}
			Display.sync(90);
			if (Display.isCloseRequested()) {
				cleanup();
			}
		}
	}

	public long getTime() {
		return timer.getTime();
	}

	public void cleanup() {
		isRunning = false;
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		AL.destroy();
		System.out.println("Clean up successful!");
		System.exit(0);
	}
}
