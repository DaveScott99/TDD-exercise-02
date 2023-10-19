package com.astro.bds02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astro.bds02.entities.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
