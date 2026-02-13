package io.github.INF1009OOP_Project;

public class IOManager {

	private SoundManager sound;
	private Keyboard keyboard;
	private Mouse mouse;

	public IOManager() {
		sound = new SoundManager();
		keyboard = new Keyboard();
		mouse = new Mouse();
	}
	
	public SoundManager getSound() {
        return sound;
    }

	public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }
    
    public void update() {
        keyboard.update();
        mouse.update();
    }
}
