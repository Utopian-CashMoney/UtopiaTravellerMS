package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport implements Serializable {
    private static final long serialVersionUID = 7707393142740096516L;

    // Data
    @Id
    @Column(name = "iata_id")
    private String iataId;
    
    @Column(name = "city")
    private String city;

    // Relationships
    @JsonManagedReference(value = "airport-origin")
    @OneToMany(mappedBy = "origin")
    private Collection<Route> originOf;

    @JsonManagedReference(value = "airport-destination")
    @OneToMany(mappedBy = "destination")
    private Collection<Route> destinationOf;

    // Methods
    public String getIataId() {
        return iataId;
    }

    public void setIataId(String iata) {
        this.iataId = iata;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Collection<Route> getOriginOf() {
        return originOf;
    }

    public void setOriginOf(Collection<Route> origin_of) {
        this.originOf = origin_of;
    }

    public Collection<Route> getDestinationOf() {
        return destinationOf;
    }

    public void setDestinationOf(Collection<Route> destination_of) {
        this.destinationOf = destination_of;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(iataId, airport.iataId) && Objects.equals(city, airport.city) && Objects.equals(originOf, airport.originOf) && Objects.equals(destinationOf, airport.destinationOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iataId, city, originOf, destinationOf);
    }
}
