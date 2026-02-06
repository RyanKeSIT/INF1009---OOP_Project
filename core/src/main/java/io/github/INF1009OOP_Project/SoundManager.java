package io.github.INF1009OOP_Project;

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

		// loop bgm
		bgm.setLooping(true);

		bgm.setVolume(volume);
	}

	public void soundOn() {
		bgm.play();
	}

	public void soundOff() {
		bgm.stop();
	}

	public void playShootingSound() {
		shootingSound.play(volume);
	}

	public void setVolume(float volume) {
		this.volume = volume;
		bgm.setVolume(volume);
	}
}