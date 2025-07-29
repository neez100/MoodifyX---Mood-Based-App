package com.moodifyx;

import java.io.File;
import javax.sound.sampled.*;


public class SongPlayer {
    public static Clip clip;

    public static void play(String filePath) {

        try{
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));

        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        }catch(Exception e) {
            System.out.println("An error occured while trying to play the sound, bro.... or sis or somn' " + e.getMessage());
        }
    }

    public static void stop() {
            if(clip != null && clip.isRunning()) {
                clip.stop();
                clip.close();
                
            }
        }
    
}
