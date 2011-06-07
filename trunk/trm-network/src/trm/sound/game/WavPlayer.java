/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.sound.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class WavPlayer {
    private static AudioClip audioClip;
    public static void play(String wavFile) {
        
        File f = new File(wavFile);
        try {
            audioClip = Applet.newAudioClip(f.toURI().toURL());
            audioClip.play();
        } catch (MalformedURLException ex) {
            Logger.getLogger(WavPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
