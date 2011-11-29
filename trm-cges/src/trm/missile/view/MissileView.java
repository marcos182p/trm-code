package trm.missile.view;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import trm.model.Missile;

public class MissileView extends Drawable {

    private Image missileImage;
    private Missile missile;

    public MissileView(Missile missile) {
        try {
            missileImage = ImageIO.read(new File("src/resources/missile.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.missile = missile;
    }

    @Override
    public void drawIn(Graphics2D g) {
        double s = 2;
        int x = (int) missile.getPosition().getX();
        int y = (int) missile.getPosition().getY();
        int w = (int) (missileImage.getWidth(null) * s);
        int h = (int) (missileImage.getHeight(null) * s);
        g.translate(x, y);
        g.rotate(missile.getAngle());
        g.translate(-x, -y);
        if (missileImage != null) {
            g.drawImage(missileImage, x - w / 2, y - h / 2, w, h, null);
        }
    }
}
