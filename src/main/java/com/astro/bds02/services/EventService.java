package com.astro.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.astro.bds02.dto.EventDTO;
import com.astro.bds02.entities.City;
import com.astro.bds02.repositories.EventRepository;
import com.astro.bds02.services.exceptions.DatabaseException;
import com.astro.bds02.services.exceptions.EntityNotFoundException;

@Service
public class EventService {

	private final EventRepository repository;

	public EventService(EventRepository repository) {
		this.repository = repository;
	}
	
	public List<EventDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(event -> new EventDTO(event))
				.sorted((c1, c2) -> c1.getName().compareTo(c2.getName()))
				.collect(Collectors.toList());
	}
	
	public EventDTO update(Long id, EventDTO dto) {
		
		return repository.findById(id)
			.map(eventFound -> {
				
				eventFound.setName(dto.getName());
				eventFound.setUrl(dto.getUrl());
				eventFound.setDate(dto.getDate());
				eventFound.setCity(new City(dto.getCityId(), null));
				
				return new EventDTO(repository.save(eventFound));
			})
		
			.orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
		
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Evento não encontrada ID: " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade");
		}
	}
	
}
