package com.ss.utopia.entity;

import java.io.Serializable;
import java.util.Objects;

public class TicketPK implements Serializable {
    private static final long serialVersionUID = -345755094328368054L;
    protected Integer flightID;
    protected Integer travellerID;

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public Integer getTravellerID() {
        return travellerID;
    }

    public void setTravellerID(Integer travellerID) {
        this.travellerID = travellerID;
    }

    public TicketPK() {

    }

    public TicketPK(Integer flightID, Integer travellerID) {
        this.flightID = flightID;
        this.travellerID = travellerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketPK ticketPK = (TicketPK) o;
        return Objects.equals(flightID, ticketPK.flightID) && Objects.equals(travellerID, ticketPK.travellerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightID, travellerID);
    }
}
