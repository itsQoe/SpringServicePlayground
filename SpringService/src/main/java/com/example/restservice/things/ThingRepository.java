package com.example.restservice.things;

import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface ThingRepository extends Repository<Thing, Long> {
    Collection<ThingView> findByUserId(Long userId);

    Thing findById(Long id);

    Thing save(Thing thing);
}
