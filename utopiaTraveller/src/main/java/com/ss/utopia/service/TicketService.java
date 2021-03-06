package com.ss.utopia.service;

import com.ss.utopia.dao.FlightDao;
import com.ss.utopia.dao.RouteDao;
import com.ss.utopia.dao.TicketDao;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.Ticket;
import com.ss.utopia.entity.TicketPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Ticket Service
 * @author Charvin Patel
 */
@Service
@Transactional
public class TicketService {
    @Autowired
    private TicketDao ticketDao;
    
    @Autowired
    private FlightDao flightDao;
    
    @Autowired
    private RouteDao routeDao;

    public List<Ticket> getAllTickets() {
        return ticketDao.findAll();
    }
    

    public List<Ticket> searchByMembershipNumber(String membershipNumber) {
        return ticketDao.findByTraveller_MembershipNumberLike(membershipNumber);
    }

    public List<Ticket> searchByDepartureTimeAfter(LocalDateTime flight_departureTime) {
    	
    	return ticketDao.findByFlight_DepartureTimeAfter(flight_departureTime);
    }

    public List<Ticket> searchByDepartureTimeBefore(LocalDateTime flightDepartureTime) {
        return ticketDao.findByFlight_DepartureTimeBefore(flightDepartureTime);
    }

    public List<Ticket> searchByDepartureTimeRange(LocalDateTime flightDepartureTime, LocalDateTime flightDepartureTime2) {
        return ticketDao.findByFlight_DepartureTimeBetween(flightDepartureTime, flightDepartureTime2);
    }

    public List<Ticket> searchByOriginCity(String flightRouteOriginCity) {
        return ticketDao.findByFlight_Route_Origin_CityLike(flightRouteOriginCity);
    }

    public List<Ticket> searchByDestinationCity(String flightRouteDestinationCity) {
        return ticketDao.findByFlight_Route_Destination_CityLike(flightRouteDestinationCity);
    }
    
    
    // Show all flights to book a ticket
	public List<Flight> getAllFlights(){
		return flightDao.findAll();
	}
	
	
	// Show all routes to book a ticket
	public List<Route> getAllRoutes(){
		return routeDao.findAll();
	}
	
	// Add Flight to book a ticket
	public void addFlight(Flight flight)  
	{    
		flightDao.save(flight);    
	}  


    /**
     * Insert into the database if the ID doesn't already exist.
     * @param ticket Ticket to add.
     * @return The created ticket, or null if it already exists (and thus no changes made)
     */
    public Ticket insert(Ticket ticket) {
        TicketPK ticketPK = new TicketPK(ticket.getFlightID(), ticket.getTravellerID());
        if(!ticketDao.existsById(ticketPK)) return ticketDao.save(ticket);
        return null;
    }
    
    /**
     * Delete by primary key (traveller and flight id)
     * @param ticketPK Ticket primary key
     * @return True if it was deleted, false if not found.
     */
    public Boolean delete(TicketPK ticketPK) {
        if(ticketDao.existsById(ticketPK)) {
            ticketDao.deleteById(ticketPK);
            return true;
        }
        return false;
    }
}