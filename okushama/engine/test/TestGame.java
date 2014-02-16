package okushama.engine.test;

import okushama.engine.core.*;
import okushama.engine.util.Colour;
import okushama.engine.util.Registry;
import okushama.engine.util.Renderer;
import okushama.engine.util.TrueTypeFont;

import org.lwjgl.input.*;
import org.newdawn.slick.openal.Audio;


/**
 * Test case for SimpleGame, part of my 2d Game Engine that runs 
 * on lwjgl and slick-util (slick is only for lazy audio :D)
 * @author Mr_okushama
 */
public class TestGame extends SimpleGame {

	// main entry point
	public static void main(String[] args) {
		new TestGame();
	}

	// a few test assets
	public TrueTypeFont testFont;
	public Audio testSound;

	// simple position variables to play with
	public float testX = 0, testY = 0;

	public TestGame() {
		// WORK DAMN IT
		// title, window size, display scale
		super("Test Game", 400, 400, 4, "assets/texture/icon.png");
	}

	@Override
	public void registerAssets() {
		// loading of assets into memory
		testSound = Registry.registerSound("assets/sound/hitone.wav");
		testFont = Registry.registerFont("assets/font/font.TTF", 12f);

	}

	@Override
	public void onMouseClick(int btn) {	}

	@Override
	public void onKeyPress(int key) {
		if (key == Keyboard.KEY_F11) {
			// playing with display modes/scale
			setDisplay(400, 400, 4);
		}
	}

	@Override
	public void onRenderTick(float pt) {
		
		// draw a square with text in at test position
		Renderer.drawRect(testX, testY, 40, 40, Colour.purple);
		Renderer.drawString(testFont, "o7", testX + 12, testY + 12, Colour.white);
	}

	@Override
	public void onLogicTick(float pt) {

		// movement
		float speed = 1f;
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			speed = 3f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			testY += speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			testY -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			testX -= speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			testX += speed;
		}
		
		// Drag 
		if (Mouse.isButtonDown(0)) {
			int mouseX = Mouse.getX() / displayScale, mouseY = Mouse.getY()
					/ displayScale;
			if (mouseX > testX && mouseX < testX + 40 &&
				mouseY > testY && mouseY < testY + 40) {
				testX = mouseX - 20;
				testY = mouseY - 20;
			}
		}
		
		// Bounds
		if (testX < 0){
			testX = 0;
		}
		if (testY < 0){
			testY = 0;
		}
		if (testX > displayWidth - 40) {
			testX = displayWidth - 40;
		}
		if (testY > displayHeight - 40) {
			testY = displayHeight - 40;
		}
	}
}
