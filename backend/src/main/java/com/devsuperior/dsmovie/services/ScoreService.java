package com.devsuperior.dsmovie.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;

@Service
public class ScoreService {

	@Autowired
	ScoreRepository scoreRepository;
	
	@Autowired
	MovieRepository movieRepository;
		
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		UserEntity user = userRepository.findByEmail(dto.getEmail());
		if (user == null) {
			user = new UserEntity();
			user.setEmail(dto.getEmail());
			user = userRepository.saveAndFlush(user);
		}
		
		
		MovieEntity movie = movieRepository.findById(dto.getMovieId()).get();
		ScoreEntity score = new ScoreEntity();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (ScoreEntity s : movie.getScores()) {
			sum = sum + s.getValue();
		}
		
		double avg = sum / movie.getScores().size();
		movie.setScore(avg);
		movie.setCount(movie.getCount() + 1);
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
