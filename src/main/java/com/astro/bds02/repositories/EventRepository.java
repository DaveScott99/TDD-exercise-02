package com.astro.bds02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astro.bds02.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
