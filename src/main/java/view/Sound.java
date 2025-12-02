package view;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/*
 * Author: Ludvig Hygrell
 *
 * Minimum decibel = -80.0
 * Maximum decibel = 6.0206
 */

public class Sound {
    private Clip clip;
    private FloatControl gainControl;

    //Create a Sound object using a wav file and its directory(dir)
    public Sound(String dir) {
        try {
            URL url = getClass().getResource(dir);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
        }
    }

    //Play the sound once at defualt volume
    public void play(){
        try{
            gainControl.setValue(0);
            clip.setFramePosition(0);
            clip.start();
        } catch(Exception e){
        }
    }

    //Play the sound once at a specified volume
    public void play(int decibel){
        try{
            if(gainControl.getMinimum()<decibel|| decibel < gainControl.getMaximum()){
                gainControl.setValue(decibel);
                clip.setFramePosition(0);
                clip.start();
            }
        } catch(Exception e){

        }
    }

    //Play the sound indefinitely
    public void startLoop() {
        try {
            gainControl.setValue(0);
            clip.setFramePosition(0);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Play the sound indefinitely at a specified volume
    public void startLoop(int decibel) {
        try {
            if(gainControl.getMinimum()<decibel|| decibel < gainControl.getMaximum()){
                gainControl.setValue(decibel);
                clip.setFramePosition(0);
                clip.start();
                clip.loop(clip.LOOP_CONTINUOUSLY);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Interrupt the sound
    public void stop(){
        clip.stop();
    }
}