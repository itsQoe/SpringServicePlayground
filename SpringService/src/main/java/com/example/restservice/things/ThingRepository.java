package com.example.restservice.things;

import org.springframework.data.repository.CrudRepository;

interface ThingRepository extends CrudRepository<Thing, Long> {

}
