/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.sound.game;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 *
 * @author Rafael
 */
public class MidiPlayer {

    private static Sequencer sequencer = null;
    public static final String PATH = "rsc//";
    
    private MidiPlayer(){}
    
    public static void play(String midifile) {
        Sequence sequence;
        try {
            sequence = MidiSystem.getSequence(new File(PATH + midifile));
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
        } catch (Exception ex) {
            Logger.getLogger(MidiPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void close() {
        sequencer.close();
    }
}
