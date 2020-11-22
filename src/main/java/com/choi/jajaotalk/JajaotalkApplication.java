package com.choi.jajaotalk;

        import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.context.annotation.Bean;

        import javax.annotation.PostConstruct;
        import java.util.TimeZone;

@SpringBootApplication
public class JajaotalkApplication {

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication.run(JajaotalkApplication.class, args);
    }
    @Bean
    Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
//        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true);
        return hibernate5Module;
    }
}
