package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route implements Serializable {
    private static final long serialVersionUID = -9190994961314139861L;

    // Data
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relationships
    @JsonManagedReference(value = "route-flight")
    @OneToMany(mappedBy = "route")
    private Collection<Flight> flights;

    @ManyToOne
    @JsonBackReference(value = "airport-origin")
    @JoinColumn(name = "origin_id", referencedColumnName = "iata_id")
    private Airport origin;

    @ManyToOne
    @JsonBackReference(value = "airport-destination")
    @JoinColumn(name = "destination_id", referencedColumnName = "iata_id")
    private Airport destination;

    // Methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Collection<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Collection<Flight> flights) {
        this.flights = flights;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(flights, route.flights) && Objects.equals(origin, route.origin) && Objects.equals(destination, route.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flights, origin, destination);
    }
}
