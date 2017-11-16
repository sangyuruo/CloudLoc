package com.emcloud.loc.repository;

import com.emcloud.loc.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Address entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
     Page<Address> findAllByAddressNameContaining(Pageable pageable,String addressName);
}
