package com.astro.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.astro.bds02.dto.CityDTO;
import com.astro.bds02.entities.City;
import com.astro.bds02.repositories.CityRepository;
import com.astro.bds02.services.exceptions.DatabaseException;
import com.astro.bds02.services.exceptions.EntityNotFoundException;

@Service
public class CityService {

	private final CityRepository repository;

	public CityService(CityRepository repository) {
		this.repository = repository;
	}
	
	public List<CityDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(city -> new CityDTO(city))
				.sorted((c1, c2) -> c1.getName().compareTo(c2.getName()))
				.collect(Collectors.toList());
	}
	
	public CityDTO insert(CityDTO dto) {
		return new CityDTO(repository.save(new City(null, dto.getName())));
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Cidade não encontrada ID: " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
	
}
