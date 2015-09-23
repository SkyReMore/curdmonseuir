package avt.sp.ln;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoENG {
	File soun1 = new File("audi1.wav");
	File soun2 = new File("audi2.wav");
	
	AudioInputStream audi1, audi2;
	Clip bclip, clipr;
	
	SoENG() {
		try {
			audi1 = AudioSystem.getAudioInputStream(soun1);
			audi2 = AudioSystem.getAudioInputStream(soun2);
			bclip = AudioSystem.getClip();
			clipr = AudioSystem.getClip();
			bclip.open(audi1);
			clipr.open(audi2);
		} catch (UnsupportedAudioFileException excep) {
			System.out.println("UnsupportedAudioFileException");
		} catch (IOException excep) {
			System.out.println("IOException");
		} catch (LineUnavailableException excep) {
			System.out.println("LineUnavailableException");
		}
	}
}
