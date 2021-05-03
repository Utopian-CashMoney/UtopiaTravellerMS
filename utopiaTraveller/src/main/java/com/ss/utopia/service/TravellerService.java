package com.ss.utopia.service;

import com.ss.utopia.dao.TravellerDao;
import com.ss.utopia.entity.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {
    @Autowired
    private TravellerDao travellerDao;

    /**
     * Get all Travellers in the database.
     * @author Joshua Podhola
     * @return List of all travellers.
     */
    public List<Traveller> all() {
        return travellerDao.findAll();
    }

    /**
     * Search by given name.
     * @author Joshua Podhola
     * @param givenName Given name query.
     * @return List of all matching travellers.
     */
    public List<Traveller> searchByGivenName(String givenName) {
        return travellerDao.findByGivenNameLike(String.format("%%%s%%", givenName));
    }

    /**
     * Search by family name.
     * @author Joshua Podhola
     * @param familyName Family name to search by.
     * @return A list of matching travellers.
     */
    public List<Traveller> searchByFamilyName(String familyName) {
        return travellerDao.findByFamilyNameLike(String.format("%%%s%%", familyName));
    }

    /**
     * Search by membership number
     * @param membershipNumber Membership number to search by
     * @return A list of matching travellers.
     */
    public List<Traveller> searchByMembershipNumber(String membershipNumber) {
        return travellerDao.findByMembershipNumberLike(String.format("%%%s%%", membershipNumber));
    }

    /**
     * Update the given name of a traveller by their membership number IF AND ONLY IF one traveller matches that number.
     * @param membershipNumber Membership number
     * @param newGivenName New given name
     * @return Null if more than one match, true if one match and changes saved, false if no matches.
     */
    public Boolean updateGivenNameByMembershipNumber(String membershipNumber, String newGivenName) {
        List<Traveller> travellers = this.searchByMembershipNumber(membershipNumber);
        if(travellers.size() > 1) {
            return null;
        }
        if(travellers.size() == 0) {
            return false;
        }
        Traveller traveller = travellers.get(0);
        traveller.setGivenName(newGivenName);
        travellerDao.save(traveller);
        return true;
    }

    /**
     * Update the family name of a traveller by their membership number IF AND ONLY IF one traveller matches that number.
     * @author Joshua Podhola
     * @param membershipNumber Membership number
     * @param newFamilyName New family name
     * @return Null if more than one match, true if one match and changes saved, false if no matches.
     */
    public Boolean updateFamilyNameByMembershipNumber(String membershipNumber, String newFamilyName) {
        List<Traveller> travellers = this.searchByMembershipNumber(membershipNumber);
        if(travellers.size() > 1) {
            return null;
        }
        if(travellers.size() == 0) {
            return false;
        }
        Traveller traveller = travellers.get(0);
        traveller.setFamilyName(newFamilyName);
        travellerDao.save(traveller);
        return true;
    }

    /**
     * Create a new traveller
     * @author Joshua Podhola
     * @param traveller The traveller to create
     */
    public Traveller create(Traveller traveller) {
        return travellerDao.save(traveller);
    }

    /**
     * Delete all matching family name
     * @param familyName Family name
     * @return Number of deleted items
     */
    public Integer deleteByFamilyName(String familyName) {
        List<Traveller> travellers = this.searchByFamilyName(familyName);
        Integer n = travellers.size();
        travellerDao.deleteAll(travellers);
        return n;
    }

    /**
     * Delete all matching membership number
     * @param membershipNumber Membership number to search for
     * @return Number of deleted entries
     */
    public Integer deleteByMembershipNumber(String membershipNumber) {
        List<Traveller> travellers = this.searchByMembershipNumber(membershipNumber);
        Integer n = travellers.size();
        travellerDao.deleteAll(travellers);
        return n;
    }
}
