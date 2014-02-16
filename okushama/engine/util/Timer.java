package okushama.engine.util;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Timer {

	public long lastFrame, lastFPS;
	public int fps;

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public float getDelta() {
		long time = getTime();
		float delta = (float) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	public void start() {
		lastFPS = getTime();
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
}
