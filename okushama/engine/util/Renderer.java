package okushama.engine.util;
import static org.lwjgl.opengl.GL11.*;

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
