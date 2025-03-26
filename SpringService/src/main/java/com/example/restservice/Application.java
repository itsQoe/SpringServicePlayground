package com.example.restservice;

import com.example.restservice.things.Thing;
import com.example.restservice.things.ThingRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {

    private final ThingRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        repo.save(new Thing(null, "test1", "description one", 33L));
        repo.save(new Thing(null, "test2", "description two", 33L));
        repo.save(new Thing(null, "test3", "description three", 33L));
    }

}
