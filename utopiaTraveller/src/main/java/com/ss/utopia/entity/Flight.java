package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {
    private static final long serialVersionUID = -4262878510116123649L;

    // Data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "departure_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDateTime departureTime;
    
    @Column(name = "economy_seats")
    private int economySeats;
    
    @Column(name = "business_seats")
    private int businessSeats;
    
    @Column(name = "firstclass_seats")
    private int firstClassSeats;

    // Relationships
    @ManyToOne
    @JsonBackReference(value = "route-flight")
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    private Route route;
    
    @ManyToOne
    @JsonBackReference(value = "airplane-flights")
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;

    @JsonManagedReference(value = "flight-tickets")
    @OneToMany(mappedBy = "flight")
    private Collection<Ticket> tickets;

    // Methods

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departure_time) {
        this.departureTime = departure_time;
    }

    public int getEconomySeats() {
        return economySeats;
    }

    public void setEconomySeats(int economy_seats) {
        this.economySeats = economy_seats;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public void setBusinessSeats(int business_seats) {
        this.businessSeats = business_seats;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(int firstclass_seats) {
        this.firstClassSeats = firstclass_seats;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return economySeats == flight.economySeats && businessSeats == flight.businessSeats && firstClassSeats == flight.firstClassSeats && Objects.equals(id, flight.id) && Objects.equals(departureTime, flight.departureTime) && Objects.equals(route, flight.route) && Objects.equals(airplane, flight.airplane) && Objects.equals(tickets, flight.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureTime, economySeats, businessSeats, firstClassSeats, route, airplane, tickets);
    }
}
