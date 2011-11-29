package component.environment.spec.prov;

import java.util.List;

public interface IManager {

    public <E> E getProvidedInterface(Class<E> classType);

    public <E> E getRequiredInterface(Class<E> classType);

    public <E> void setRequiredInterface(Class<E> classType, Object objeto);

    public List<String> getProvidedInterfaces();
}
