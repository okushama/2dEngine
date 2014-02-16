package okushama.engine.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public abstract class Input implements ITicker{

	@Override
	public void onLogicTick(float partialTick) {
		while (Mouse.next()) {
			int btn = Mouse.getEventButton();
			boolean state = Mouse.getEventButtonState();
			int mwheel = Mouse.getDWheel();
			if (btn > -1 && state) {
				onMouseClick(btn);
			}
			if (mwheel != 0) {
				onMouseScroll(mwheel);
			}
		}
		while (Keyboard.next()) {
			if (!Keyboard.isRepeatEvent() && !Keyboard.getEventKeyState()) {
				onKeyPress(Keyboard.getEventKey());
			}
		}
	}

	@Override
	public void onRenderTick(float partialTick) {}
	
	public abstract void onMouseClick(int b);

	public abstract void onMouseScroll(int dir);

	public abstract void onKeyPress(int k);

}
