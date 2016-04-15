package hu.codingmentor.config;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(hu.codingmentor.config.Exceptions.BadRequestExceptionMapper.class);
        resources.add(hu.codingmentor.services.rest.CartRESTService.class);
        resources.add(hu.codingmentor.services.rest.InventoryRESTService.class);
        resources.add(hu.codingmentor.services.rest.UserRESTService.class);
    }
}
