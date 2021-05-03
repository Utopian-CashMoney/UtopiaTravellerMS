package com.ss.utopia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.utopia.entity.Airplane;

@Repository
public interface AirplaneDao  extends JpaRepository<Airplane, Integer> {

	public Optional<Airplane> findById(Integer id);
}
