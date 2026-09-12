package es.daw.sostenibilidad.huella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/** Laboratorio de huella del software: medir, optimizar y volver a medir. */
@SpringBootApplication
@EnableScheduling   // permite usar @Scheduled en la tarea carbon-aware
@EnableCaching      // permite usar @Cacheable en la versión optimizada del catálogo
public class HuellaSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuellaSoftwareApplication.class, args);
    }
}
