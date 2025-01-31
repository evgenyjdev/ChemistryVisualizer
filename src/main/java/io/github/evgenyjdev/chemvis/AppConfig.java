package io.github.evgenyjdev.chemvis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.epam.indigo.Indigo;
import com.epam.indigo.IndigoRenderer;

@Configuration
public class AppConfig {

    @Bean
    public Indigo indigo() {
        return new Indigo();
    }

    @Bean
    public IndigoRenderer indigoRenderer(Indigo indigo) {
        return new IndigoRenderer(indigo);
    }
}