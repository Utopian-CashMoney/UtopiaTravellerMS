package com.ss.utopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ss.utopia.entity.Traveller;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TravellerDao extends JpaRepository<Traveller, Integer> {
    List<Traveller> findByGivenNameLike(String givenName);
    List<Traveller> findByFamilyNameLike(String familyName);
    List<Traveller> findByMembershipNumberLike(String membershipNumber);
}