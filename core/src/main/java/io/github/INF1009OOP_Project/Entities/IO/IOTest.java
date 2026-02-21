//package io.github.INF1009OOP_Project.Entities.IO;
//
//import com.badlogic.gdx.ScreenAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.Input.Buttons;
//
//public class IOTest extends ScreenAdapter {
//
//	private IOManager io;
//
//	public IOTest() {
//		io = new IOManager();
//		io.getSound().soundOn();
//	}
//
//	@Override
//	public void render(float delta) {
//		io.update();
//
//		// Keyboard, Mouse, Sound test
//		if (io.getKeyboard().moveLeft()) {
//			Gdx.app.log("IO TEST", "Move Left detected");
//		}
//
//		if (io.getKeyboard().moveRight()) {
//			Gdx.app.log("IO TEST", "Move Right detected");
//		}
//
//		if (io.getKeyboard().isShooting()) {
//			Gdx.app.log("IO TEST", "Shoot detected");
//			io.getSound().playShootingSound();
//		}
//
//		if (io.getMouse().isMousePressed(Buttons.LEFT)) {
//			Gdx.app.log("IO TEST", "Mouse click at " + io.getMouse().getX() + ", " + io.getMouse().getY());
//		}
//	}
//}



