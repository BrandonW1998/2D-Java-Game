package util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	Clip clip;
	URL soundURL[] = new URL[30];

	// SoundHandler Constructor
	public SoundHandler() {
		soundURL[0] = getClass().getResource("/sound/Twangy-Music.wav");
		soundURL[1] = getClass().getResource("/sound/Item-Pickup.wav");
		soundURL[2] = getClass().getResource("/sound/Door-Open.wav");
	}

	// Load sound from file
	public void setFile(int i) {
		// Input stream for audio files
		AudioInputStream aInStream;
		try {
			aInStream = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(aInStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Play sound file
	public void play() {
		clip.start();
	}

	// Loop sound file (until stopped manually)
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	// Stop sound file
	public void stop() {
		clip.stop();
	}
}
