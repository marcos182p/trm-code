package trm.gui;

import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author mpjms
 */
public class AngleChangeListener implements ChangeListener {

    private JTextField angleField;
    private JSlider angleSlider;

    public AngleChangeListener(JTextField angleField, JSlider angleSlider) {
        this.angleField = angleField;
        this.angleSlider = angleSlider;
    }
    
    @Override
    public void stateChanged(ChangeEvent ce) {
        angleField.setText(Integer.toString(angleSlider.getValue()));
    }

}
