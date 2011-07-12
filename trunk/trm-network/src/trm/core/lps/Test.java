package trm.core.lps;

import java.lang.reflect.Method;
import trm.core.Player;

/**
 *
 * @author Marcos
 */
public class Test {
    public static void main(String[] args) throws Exception {
//        
        Class c = Player.class;
//        Method method = c.getDeclaredMethod("getInf");
        
        for (Method m: c.getMethods()) {
            System.out.println(m);
        }
        
        System.out.println(Class.forName("trm.core.Player").equals(c));
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
