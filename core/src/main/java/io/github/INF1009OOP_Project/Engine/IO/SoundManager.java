package io.github.INF1009OOP_Project.Engine.IO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	private Music bgm;
	private Sound shootingSound;
	private Sound correctSound;
	private Sound wrongSound;
	private float volume;
	private boolean muted = false;

	public SoundManager() {
		volume = 0.5f;

		// bgm and audio effect
		bgm = Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
		shootingSound = Gdx.audio.newSound(Gdx.files.internal("shootingsound.mp3"));

		// correct and wrong sound
		correctSound = Gdx.audio.newSound(Gdx.files.internal("correct_ans.mp3"));
		wrongSound = Gdx.audio.newSound(Gdx.files.internal("wrong_ans.mp3"));

		// loop bgm
		bgm.setLooping(true);

		bgm.setVolume(volume);
	}

	public void playCorrectSound() {
		if (!muted) {
			correctSound.play(volume);
		}
	}

	public void playWrongSound() {
		if (!muted) {
			wrongSound.play(volume);
		}
	}

	public void soundOn() {
		muted = false;

		if (!bgm.isPlaying()) {
			bgm.play();
		}
	}

	public void soundOff() {
		muted = true;
		bgm.stop();
		shootingSound.stop();
	}

	public void playShootingSound() {
		if (!muted) {
			shootingSound.play(volume);
		}
	}

	public void setVolume(float volume) {
		this.volume = volume;
		bgm.setVolume(volume);
	}

	public float getVolume() {
		return this.volume;
	}

	public boolean isMuted() {
		return muted;
	}
}