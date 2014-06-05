package okushama.engine.test;

import static org.lwjgl.opengl.GL11.*;
import okushama.engine.core.*;
import okushama.engine.util.Colour;
import okushama.engine.util.Registry;
import okushama.engine.util.Renderer;
import okushama.engine.util.TrueTypeFont;

import org.lwjgl.input.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.Texture;


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
	public Texture testTexture;
	public SpriteSheet testSheet;
	public Image invader1, invader2, hero;

	// simple position variables to play with
	public float testX = 0, testY = 0;

	public TestGame() {
		// WORK DAMN IT
		// title, window size, display scale
		super("Test Game", 400, 400, 2, "assets/texture/test.png");
	}

	@Override
	public void init() {
		// loading of assets into memory
		//testSound = Registry.registerSound("assets/sound/hitone.wav");
		testFont = Registry.registerFont("assets/font/font.TTF", 12f);
		testTexture = Registry.registerTexture("assets/texture/test2.png");
		testSheet = Registry.registerSpriteSheet("assets/texture/sheet.png", 16, 16);
		SpriteSheet invader = Registry.registerSpriteSheet("assets/texture/invaders.png", 16, 16);
		invader1 = invader.getSprite(0, 0);
		invader2 = invader.getSprite(1, 0);
		hero = invader.getSprite(2, 0);
	}

	@Override
	public void onMouseClick(int btn) {	}

	@Override
	public void onKeyPress(int key) {
		if (key == Keyboard.KEY_F11) {
			// playing with display modes/scale
			setDisplay(400%32, 400%32, 4);
		}
	}

	@Override
	public void onRenderTick(float pt) {
		int time = (int)((engine.getTime()/500) % 16);
		glColor4f(1f,0f,0f,1f);
		if(time % 2 == 0){
			Renderer.drawSprite(invader1, 40, testY, 32, 32);
		}else{
			Renderer.drawSprite(invader2, 40, testY, 32, 32);
		}
		
		Renderer.drawSprite(hero, testX, 0, 32, 32);
		//Renderer.drawSprite(testSheet, time, 0, testX,	testY, 32, 32);	
		//Renderer.drawString(testFont, "Sprite Sheet Test #1", 0	, 0, Colour.white);
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
			if (mouseX > testX && mouseX < testX + 32 &&
				mouseY > testY && mouseY < testY + 32) {
				testX = mouseX - 16;
				testY = mouseY - 16;
			}
		}
		
		// Bounds
		if (testX < 0){
			testX = 0;
		}
		if (testY < 0){
			testY = 0;
		}
		if (testX > displayWidth - 32) {
			testX = displayWidth - 32;
		}
		if (testY > displayHeight - 32) {
			testY = displayHeight - 32;
		}
	}
}
