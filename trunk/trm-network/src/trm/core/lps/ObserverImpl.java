package trm.core.lps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ObserverImpl implements Observer {
    
    private List<StackTraceElement> lastStack;
    private Event lastEvent;
    
    public ObserverImpl() {
       
    }
    
    public void reset() {
        lastStack = null;
    }
    
    public List<StackTraceElement> getLastStack() {
        return lastStack;
    }
    
    public Event getLastEvent() {
        return lastEvent;
    }
    
    @Override
    public void update(Event event) {
        lastEvent = event;
        Throwable thr = new Throwable();
        thr.fillInStackTrace();

        lastStack = StackUtil.filterStack(thr.getStackTrace());
        
    }
    
}
