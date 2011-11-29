package component.problem.impl;

public class FactoryProblem {

    public static synchronized Manager getManager() {
        return Manager.getInstance();
    }
}
