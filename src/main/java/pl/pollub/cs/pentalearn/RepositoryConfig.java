package pl.pollub.cs.pentalearn;


import org.reflections.Reflections;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.util.Set;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        Reflections reflections = new Reflections("pl.pollub.cs.pentalearn.domain");

        Set<Class<? extends Object>> allClasses =
                reflections.getSubTypesOf(Object.class);

       for(Class c: allClasses){
           config.exposeIdsFor(c);
       }
    }

}