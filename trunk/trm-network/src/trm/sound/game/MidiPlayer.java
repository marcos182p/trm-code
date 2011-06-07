/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.sound.game;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    private static Map<String, Sequence> sequences = new HashMap<String, Sequence>();

    public static void pre_load(String midiFile) {
        try {
            sequences.put(midiFile, MidiSystem.getSequence(new File(PATH + midiFile)));
        } catch (Exception ex) {
            Logger.getLogger(MidiPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MidiPlayer(){}
    
    public static void play(String midifile) {
        Sequence sequence;
        try {
            sequence = sequences.get(midifile);
            if(sequence == null) {
                sequence = MidiSystem.getSequence(new File(PATH + midifile));
            }
            if(sequencer == null) {
                sequencer = MidiSystem.getSequencer();
                sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
                sequencer.open();
            }
            if(sequencer != null && sequence != null) {
                
                sequencer.setSequence(sequence);
                sequencer.start();
            }
        } catch (Exception ex) {
            Logger.getLogger(MidiPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void stop() {
        if(sequencer != null) {
            sequencer.stop();
        }
    }

    public static void close() {
        if(sequencer != null) {
            sequencer.close();
        }
    }
}
