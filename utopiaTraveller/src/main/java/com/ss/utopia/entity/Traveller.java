package com.ss.utopia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "traveller")
public class Traveller implements Serializable {
    private static final long serialVersionUID = 1315988496086304814L;

    // Data  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "given_name")
    private String givenName;
    
    @Column(name = "family_name")
    private String familyName;
    
    @Column(name = "membership_number")
    private String membershipNumber;

    // Relationships
    @JsonManagedReference(value = "ticket-traveller")
    @OneToMany(mappedBy = "traveller")
    private Collection<Ticket> tickets;

    // Methods
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String given_name) {
        this.givenName = given_name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String family_name) {
        this.familyName = family_name;
    }

    public String getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(String memebership_number) {
        this.membershipNumber = memebership_number;
    }

    public Collection<Ticket> getFlights() {
        return tickets;
    }

    public void setFlights(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Traveller traveller = (Traveller) o;
        return Objects.equals(id, traveller.id) && Objects.equals(givenName, traveller.givenName) && Objects.equals(familyName, traveller.familyName) && Objects.equals(membershipNumber, traveller.membershipNumber) && Objects.equals(tickets, traveller.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, givenName, familyName, membershipNumber, tickets);
    }

    @Override
    public String toString() {
        return "Traveller{" +
                "id=" + id +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", membershipNumber='" + membershipNumber + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}
