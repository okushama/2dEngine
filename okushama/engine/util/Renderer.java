package okushama.engine.util;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public class Renderer {
		
	public static void drawRect(float x, float y, float w, float h, float[] c){
		glColor4f(c[0], c[1], c[2], c[3]);
		glBegin(GL_QUADS);
		glVertex2f(x, y);
		glVertex2f(x+w, y);
		glVertex2f(x+w, y+h);
		glVertex2f(x, y+h);
		glEnd();
	}
	
	public static void drawSprite(SpriteSheet sheet, int sheetX, int sheetY, float x, float y, float w, float h){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		sheet.getSprite(sheetX, sheetY).getFlippedCopy(false, true).draw(x, y, w, h);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
	}
	
	public static void drawSprite(Image image, float x, float y, float w, float h){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_TEXTURE_2D);
		image.setColor(0, 1f, 0f, 0f);
		image.getFlippedCopy(false, true).draw(x, y, w, h);
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
	}
	
	public static void drawTexturedRect(float x, float y, float w, float h){
		glEnable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(1, 1);
		glVertex2f(x+w, y);
		GL11.glTexCoord2f(1, 0);
		glVertex2f(x+w, y+h);	
		GL11.glTexCoord2f(0, 0);
		glVertex2f(x, y+h);	
		GL11.glTexCoord2f(0, 1);
		glVertex2f(x, y);
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void drawString(TrueTypeFont font, String text, float x, float y, float[] c){
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glColor4f(c[0], c[1], c[2], c[3]);
		font.drawString(x, y, text);
		glDisable(GL_BLEND);
	}
	
	public static void drawCenteredString(TrueTypeFont font, String text, float y, float[] c, float displayWidth){
		glEnable(GL_BLEND);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glColor4f(c[0], c[1], c[2], c[3]);
		font.drawString(displayWidth / 2 - (font.getWidth(text) / 2), y, text);
		glDisable(GL_BLEND);
	}
}
