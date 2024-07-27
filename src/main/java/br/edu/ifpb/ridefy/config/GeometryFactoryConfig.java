package br.edu.ifpb.ridefy.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeometryFactoryConfig {

    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }

    @Bean
    public GeometryFactory geometryFactory() {
        return new GeometryFactory();
    }
}
