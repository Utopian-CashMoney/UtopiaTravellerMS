package com.ss.utopia.controller;
import com.ss.utopia.entity.Flight;
import com.ss.utopia.entity.Route;
import com.ss.utopia.entity.Ticket;
import com.ss.utopia.entity.TicketPK;
import com.ss.utopia.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
* Ticket Controller
* @author Charvin Patel
*/

@RestController
@RequestMapping("/admin/ticket")
public class TicketController {
   @Autowired
   private TicketService ticketService;
   

   @GetMapping("/all")
   public List<Ticket> getAll() {
       try {
           return ticketService.getAllTickets();
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @PostMapping("/create")
   public String create(@RequestBody Ticket ticket) {
       try {
           Ticket ticket1 = ticketService.insert(ticket);
           if (ticket1 != null) {
               return "Ticket created.";
           }
           return "Ticket already exists.";
       } catch (Exception e) {
           e.printStackTrace();
           return "Error creating ticket.";
       }
   }

   @GetMapping("/search/membership_id")
   public List<Ticket> searchByMembershipNumber(@RequestParam String id) {
	   try {
           return ticketService.searchByMembershipNumber(id);
       } catch(Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @GetMapping("/search/departure_after")
   public List<Ticket> searchByDepartureTimeAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
       try {
           return ticketService.searchByDepartureTimeAfter(time);
       } catch(Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @GetMapping("/search/departure_before")
   public List<Ticket> searchByDepartureTimeBefore(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time) {
       try {
           return ticketService.searchByDepartureTimeBefore(time);
       } catch(Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @GetMapping("/search/departure_between")
   public List<Ticket> searchByDepartureTimeRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime lower, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime upper) {
       try {
           return ticketService.searchByDepartureTimeRange(lower, upper);
       } catch(Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @GetMapping("/search/origin_city")
   public List<Ticket> searchByOriginCity(@RequestParam String origin) {
       try {
           return ticketService.searchByOriginCity(origin);
       } catch(Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   @GetMapping("/search/destination_city")
   public List<Ticket> searchByDestinationCity(@RequestParam String destination) {
       try {
           return ticketService.searchByDestinationCity(destination);
       } catch(Exception e) {
           e.printStackTrace();
           return null;
       }
   }
   
	// Read All Flights for booking the ticket
	@GetMapping("/flight/read")
	public List<Flight> getFlights() {
		List<Flight> flights = new ArrayList<>();
		flights = ticketService.getAllFlights();
		return flights;
	}
	
	
	// Read All Routes for booking the ticket
	@GetMapping("/route/read")
	public List<Route> getRoutes() {
		List<Route> routes = new ArrayList<>();
		routes = ticketService.getAllRoutes();
		return routes;
	}
	
	
	// Add flight to book a ticket
	@PostMapping("/routes/flight/add")
	public String saveFlight(@RequestBody Flight flight) {
		ticketService.addFlight(flight);
		return "Flight Successfully Added!";
	}


   @DeleteMapping("/delete")
   public String delete(@RequestParam int travellerID, @RequestParam int flightID) {
       try {
           TicketPK ticketPK = new TicketPK(flightID, travellerID);
           Boolean status = ticketService.delete(ticketPK);
           if (status) {
               return "Ticket deleted.";
           }
           return "Ticket not found.";
       } catch (DataIntegrityViolationException e) {
           return Objects.requireNonNull(e.getRootCause()).getLocalizedMessage();
       } catch (Exception e) {
           e.printStackTrace();
           return "Error deleting ticket.";
       }
   }

   }