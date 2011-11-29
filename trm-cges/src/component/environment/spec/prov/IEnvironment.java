package component.environment.spec.prov;

import trm.model.Solid;

public interface IEnvironment {

    public void addSolid(Solid solid, String name);

    public void removeSolid(Solid solid, String name);

    public void setFPS(long fps, String name);

    public void start(String name);

    public void stop(String name);

    public boolean createInstance(String name);
}
