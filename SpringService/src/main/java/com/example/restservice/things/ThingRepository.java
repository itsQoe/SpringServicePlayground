package com.example.restservice.things;

import org.springframework.data.jpa.repository.JpaRepository;

interface ThingRepository extends JpaRepository<Thing, Long> {

}
