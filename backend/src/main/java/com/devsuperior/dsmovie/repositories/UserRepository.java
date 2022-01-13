package com.devsuperior.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmovie.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByEmail(String email);
}
