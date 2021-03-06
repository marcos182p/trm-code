package trm.sound.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author TRM
 * @version 0.99
 */
public class WavPlayer {
    private static final String PATH = "rsc//";
    private static AudioClip audioClip;
    public static void play(String wavFile) {
        
        File f = new File(PATH + wavFile);
        try {
            audioClip = Applet.newAudioClip(f.toURI().toURL());
            audioClip.play();
        } catch (MalformedURLException ex) {
            Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
