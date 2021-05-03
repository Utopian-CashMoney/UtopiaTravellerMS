package com.ss.utopia.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ss.utopia.entity.Flight;

@Repository
@Transactional
public interface FlightDao extends JpaRepository<Flight, Integer> {
	
	List<Flight> findByRouteId(Integer id);
	List<Flight> findByAirplaneId(Integer id);
	
	@Query(
			  value = "select * from flight where DATE(departure_time) = ?", 
			  nativeQuery = true)
	List<Flight> findByDate(Date date);
	
	
	@Query(
			  value = "select * from flight where DATE(departure_time) < ?", 
			  nativeQuery = true)
	List<Flight> findByDateBefore(Date date);
	
	
	@Query(
			  value = "select * from flight where DATE(departure_time) BETWEEN ? AND ?", 
			  nativeQuery = true)
	List<Flight> findByDateBetween(Date dateStart, Date dateEnd);
	
	
	@Query(
			  value = "select * from flight where DATE(departure_time) > ?", 
			  nativeQuery = true)

	List<Flight> findByDateAfter(Date date);
	
	Optional<Flight> findById(Integer id);

}
