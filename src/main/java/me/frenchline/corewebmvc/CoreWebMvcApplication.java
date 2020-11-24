package me.frenchline.corewebmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreWebMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreWebMvcApplication.class, args);
    }

    @Bean("tempEvent")
    public Event event() {
        Event event = new Event();
        event.setName("Spring is coming.");
        event.setLimit(11111111);
        return event;
    }

}
