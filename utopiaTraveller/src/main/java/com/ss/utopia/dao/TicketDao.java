package com.ss.utopia.dao;

import com.ss.utopia.entity.TicketPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.utopia.entity.Ticket;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface TicketDao extends JpaRepository<Ticket, TicketPK> {
    List<Ticket> findByTraveller_MembershipNumber(String membershipNumber);
    List<Ticket> findByTraveller_MembershipNumberLike(String membershipNumber);
    List<Ticket> findByFlight_DepartureTimeAfter(LocalDateTime flight_departureTime);
    List<Ticket> findByFlight_DepartureTimeBefore(LocalDateTime flight_departureTime);
    List<Ticket> findByFlight_DepartureTimeBetween(LocalDateTime flight_departureTime, LocalDateTime flight_departureTime2);
    List<Ticket> findByFlight_Route_Origin_CityLike(String flight_route_origin_city);
    List<Ticket> findByFlight_Route_Destination_CityLike(String flight_route_destination_city);
}