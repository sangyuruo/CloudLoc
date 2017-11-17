package com.emcloud.loc.service;

import com.emcloud.loc.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Address.
 */
public interface AddressService {

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    Address save(Address address);

    /**
     * update a address.
     *
     * @param address the entity to update
     * @return the persisted entity
     */
    Address update(Address address);

    /**
     *  Get all the addresses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Address> findAll(Pageable pageable);

    /**
     *  Get the "id" address.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Address findOne(Long id);

    /**
     *  Delete the "id" address.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     *  Get the "id" address.
     *
     *  @param addressName the id of the entity
     *  @return the entity
     */
    Page<Address> findByAddressName(Pageable pageable,String addressName);
}
