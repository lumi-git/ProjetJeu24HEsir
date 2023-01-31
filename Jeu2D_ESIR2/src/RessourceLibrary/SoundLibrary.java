package RessourceLibrary;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class SoundLibrary {

    public static Clip Revive ;

    public void loadSounds(){

    }

//get the audio from url without bug
    public Clip getSound(URL url) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error with playing sound.");
            return null;
        }
    }


}
