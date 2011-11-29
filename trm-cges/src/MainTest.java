
import component.environment.impl.Environment;
import component.environment.impl.FactoryEnvironment;
import component.environment.impl.Manager;
import component.environment.spec.prov.IEnvironment;
import java.awt.Dimension;
import java.awt.Toolkit;
import trm.missile.view.DrawPanel;
import trm.missile.window.MainWindow;
import trm.model.Canon;
import trm.model.Vector2D;

public class MainTest {

    public static void main(String[] args) {

        Canon canon = new Canon(5);
        canon.setPosition(new Vector2D(200, 400));
        canon.setAngleDegrees(-88);
        canon.setIntensity(12);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        Manager managerEnv = FactoryEnvironment.getManager();
        IEnvironment iEnvironmentImpl = managerEnv.getProvidedInterface(IEnvironment.class);

        iEnvironmentImpl.createInstance("ambiente");
        Environment env = iEnvironmentImpl.getEnvironment("ambiente");

        MainWindow window = new MainWindow(canon, 600, 600, env);
        window.setLocation((screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);

        env.setFPS(60);

        DrawPanel panel = window.getPanel();

        env.addObserver(panel);

        env.start();
        window.open();
    }
}
