package com.RobeOrtiz.videogames_library.service;

import org.springframework.stereotype.Service;

import com.RobeOrtiz.videogames_library.entity.Statistics;
import com.RobeOrtiz.videogames_library.repository.StatisticsRepository;

import jakarta.transaction.Transactional;

@Service
public class StatisticsService {

	private final StatisticsRepository statisticsRepository;

	public StatisticsService(StatisticsRepository statisticsRepository) {
		this.statisticsRepository = statisticsRepository;
	}
	
	
	
	
	public long getTotalVisits() {
		Statistics statistics = statisticsRepository.findById(1L).orElse(null);	
		
		if(statistics == null) {
			System.err.println("No se puede acceder al registro de visitas");
			return -1L;
		}
		
		
		return statistics.getTotalVisits();
	}
	
	@Transactional
	public long incrementAndGetTotalVisits() {
		Statistics statistics = statisticsRepository.findById(1L).orElse(null);
		
		
		if(statistics == null) {
			System.err.println("Unable to access total_visit information in the database");
			return 0;
		}
		
		
		statistics.setTotalVisits(getTotalVisits()+1);
		statisticsRepository.save(statistics);
		return statistics.getTotalVisits();		
	}
	
}
