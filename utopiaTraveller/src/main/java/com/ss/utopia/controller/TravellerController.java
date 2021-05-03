package com.ss.utopia.controller;

import com.ss.utopia.entity.Traveller;
import com.ss.utopia.service.TravellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controllers for administrative Traveller-related CRUD operations...
 * @author Joshua Podhola
 */
@RestController
@RequestMapping("/admin/traveller")
public class TravellerController {
    @Autowired
    private TravellerService travellerService;

    @GetMapping("/all")
    public List<Traveller> getAll() {
        try {
            return travellerService.all();
        } catch (Exception e) {
            // TODO: What exceptions would really be thrown here?
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/given_name/{given_name}")
    public List<Traveller> searchByGivenName(@PathVariable("given_name") String givenName) {
        try {
            return travellerService.searchByGivenName(givenName);
        } catch (Exception e) {
            // TODO: What exceptions would really be thrown here?
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/family_name/{family_name}")
    public List<Traveller> searchByFamilyName(@PathVariable("family_name") String familyName) {
        try {
            return travellerService.searchByFamilyName(familyName);
        } catch (Exception e) {
            // TODO: What exceptions would really be thrown here?
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/membership_number/{membership_number}")
    public List<Traveller> searchByMembershipNumber(@PathVariable("membership_number") String membershipNumber) {
        try {
            return travellerService.searchByMembershipNumber(membershipNumber);
        } catch (Exception e) {
            // TODO: What exceptions would really be thrown here?
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/create")
    public String create(@RequestBody Traveller traveller) {
        try {
            traveller = travellerService.create(traveller);
            return(String.format("Traveller created with ID %d", traveller.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return("Creation failed.");
        }
    }

    @DeleteMapping("/delete_number/{membership_number}")
    public String deleteNumber(@PathVariable("membership_number") String membershipNumber) {
        try {
            Integer result = travellerService.deleteByMembershipNumber(membershipNumber);
            return String.format("Deleted %d traveller(s).", result);
        } catch (Exception e) {
            e.printStackTrace();
            return("Deletion failed.");
        }
    }

    @DeleteMapping("/delete_name/{family_name}")
    public String deleteName(@PathVariable("family_name") String familyName) {
        try {
            Integer result = travellerService.deleteByFamilyName(familyName);
            return String.format("Deleted %d traveller(s).", result);
        } catch (Exception e) {
            e.printStackTrace();
            return("Deletion failed.");
        }
    }

    @PutMapping("/update/{membership_number}")
    public String update(@PathVariable("membership_number") String membershipNumber,
                         @RequestParam(required = false) String familyName,
                         @RequestParam(required = false) String givenName) {
        try {
            if(familyName == null && givenName == null) {
                return("No new values given.");
            }
            if(familyName != null) {
                Boolean res = travellerService.updateFamilyNameByMembershipNumber(membershipNumber, familyName);
                if(res == null) {
                    return("Multiple traveller found with that membership ID. Not updated.");
                }
                if(res && givenName == null) {
                    return("Family name updated.");
                }
                if(!res) {
                    return ("No traveller found with that membership ID.");
                }
            }
            Boolean res = travellerService.updateGivenNameByMembershipNumber(membershipNumber, givenName);
            if(res == null) {
                return("Multiple traveller found with that membership ID. Not updated.");
            }
            if(res && familyName == null) {
                return("Given name updated.");
            }
            if(!res) {
                return ("No traveller found with that membership ID.");
            }
            return("Given and family name updated.");
        } catch (Exception e) {
            e.printStackTrace();
            return("Update failed.");
        }
    }
}
