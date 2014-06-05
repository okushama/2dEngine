package okushama.engine.util;

import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import okushama.engine.core.Input;
import okushama.engine.core.SimpleGame;
import okushama.engine.core.ITicker;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Registry {

	public Audio sound1 = Registry.registerSound("test");

	public static TrueTypeFont registerFont(String fileLocation, float size, boolean aa) {
		TrueTypeFont ttf = null;
		try {
			InputStream inputStream = ResourceLoader
					.getResourceAsStream(fileLocation);
			java.awt.Font awtFont;
			awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					inputStream);
			awtFont = awtFont.deriveFont(size);
			ttf = (TrueTypeFont) new TrueTypeFont(awtFont, aa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ttf;
	}
	
	public static TrueTypeFont registerFont(String fileLocation, float size){
		return registerFont(fileLocation, size, false);
	}

	public static Audio registerSound(String soundLocation) {
		Audio audio = null;
		try {
			if (soundLocation.toLowerCase().contains(".ogg")) {
				audio = AudioLoader.getAudio("OGG",
						ResourceLoader.getResourceAsStream(soundLocation));
			}
			if (soundLocation.toLowerCase().contains(".wav")) {
				audio = AudioLoader.getAudio("WAV",
						ResourceLoader.getResourceAsStream(soundLocation));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audio;
	}
	
	public static Texture registerTexture(String textureLocation) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture(
					"PNG",
					ResourceLoader.getResourceAsStream(textureLocation));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return texture;
	}

	public static SpriteSheet registerSpriteSheet(String textureLocation, int tileSizeX,
			int tileSizeY) {
		SpriteSheet sheet = null;
		try {
			sheet = new SpriteSheet(textureLocation, tileSizeX, tileSizeY);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public static void registerTicker(SimpleGame game, ITicker ticker) {
		game.engine.addTicker(ticker);
	}

	public static void registerInput(SimpleGame game, Input input) {
		registerTicker(game, input);
	}

}
