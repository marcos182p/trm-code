package component.environment.impl;

import java.util.HashMap;
import java.util.Map;

import trm.model.Solid;
import component.environment.spec.prov.IEnvironment;

public class FacadeIEnvironment implements IEnvironment {

    private Map<String, Environment> instances = new HashMap<String, Environment>();

    public FacadeIEnvironment() {
    }

    @Override
    public void addSolid(Solid solid, String name) {

        Environment environment = instances.get(name);

        if (environment != null) {
            environment.addSolid(solid);
        } else {
            throw new RuntimeException("Ambiente não existe!");
        }

    }

    @Override
    public void removeSolid(Solid solid, String name) {
        Environment environment = instances.get(name);

        if (environment != null) {
            environment.removeSolid(solid);
        } else {
            throw new RuntimeException("Ambiente não existe!");
        }

    }

    @Override
    public void setFPS(long fps, String name) {
        Environment environment = instances.get(name);

        if (environment != null) {
            environment.setFPS(fps);
        } else {
            throw new RuntimeException("Ambiente não existe!");
        }

    }

    @Override
    public void start(String name) {
        Environment environment = instances.get(name);

        if (environment != null) {
            environment.start();
        } else {
            throw new RuntimeException("Ambiente não existe!");
        }
    }

    @Override
    public void stop(String name) {
        Environment environment = instances.get(name);

        if (environment != null) {
            environment.stop();
        } else {
            throw new RuntimeException("Ambiente não existe!");
        }
    }

    @Override
    public boolean createInstance(String name) {

        if (instances.containsKey(name)) {
            return false;
        } else {
            Environment environment = new Environment();
            instances.put(name, environment);
            return true;
        }

    }
}
