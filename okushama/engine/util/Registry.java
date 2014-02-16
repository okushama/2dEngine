package okushama.engine.util;

import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import okushama.engine.core.Input;
import okushama.engine.core.SimpleGame;
import okushama.engine.core.ITicker;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Registry {

	public Audio sound1 = Registry.registerSound("test");

	public static TrueTypeFont registerFont(String fileLocation, float size) {
		TrueTypeFont ttf = null;
		try {
			InputStream inputStream = ResourceLoader
					.getResourceAsStream(fileLocation);
			java.awt.Font awtFont;
			awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					inputStream);
			awtFont = awtFont.deriveFont(size);
			ttf = (TrueTypeFont) new TrueTypeFont(awtFont, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ttf;
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

	public static void registerTicker(SimpleGame game, ITicker ticker) {
		game.engine.addTicker(ticker);
	}

	public static void registerInput(SimpleGame game, Input input) {
		registerTicker(game, input);
	}

}
