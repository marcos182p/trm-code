package component.environment.impl;

public class FactoryEnvironment {

    public static synchronized Manager getManager() {
        return Manager.getInstance();
    }
}
