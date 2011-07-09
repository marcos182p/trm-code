package trm.core.lps;

import java.lang.reflect.Method;
import trm.net.server.game.PlayerServer;

/**
 *
 * @author Marcos
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        
        Class c = PlayerServer.class;
//        Method method = c.getDeclaredMethod("getInf");
        
        for (Method m: c.getMethods()) {
            System.out.println(m);
        }
//        
//        
//        System.out.println(method);
        
//        ObserverImpl observer = new ObserverImpl();
//        
//        
//        UMLModel.getUMLModel().register(PlayerInf.class);
//        
//        PlayerInf playerInf = new PlayerInf(1L, "marcos182p");
//        playerInf.register(observer);
//        
//        playerInf.getId();
        
//        Class c = ServerTask.class;
//        
//        c.getMethod("putServerTask", ServerTask.class);
//        System.out.println();
        
        
        
    }
    
}
