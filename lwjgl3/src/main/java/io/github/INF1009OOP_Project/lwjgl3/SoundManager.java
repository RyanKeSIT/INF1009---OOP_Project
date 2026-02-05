package io.github.INF1009OOP_Project.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private Music bgm;
	private Sound shootingSound;

	private float volume;

	public SoundManager() {
		volume = 0.5f;

		// pending suitable bgm and audio effect
		bgm = Gdx.audio.newMusic(Gdx.files.internal("pending"));
		shootingSound = Gdx.audio.newSound(Gdx.files.internal("pending"));

		// play bgm throughout
		bgm.setLooping(true);

		bgm.setVolume(volume);
	}

	public void playMusic() {
		bgm.play();
	}

	public void stopMusic() {
		bgm.stop();
	}

	public void playShoot() {
		shootingSound.play(volume);
	}

	public void setVolume(float volume) {
		this.volume = volume;
		bgm.setVolume(volume);
	}

	public void soundOff() {
		setVolume(0f);
	}

	public void soundOn() {
		setVolume(0.5f);
	}
}
