package com.emcloud.loc.service.impl;

import com.emcloud.loc.security.SecurityUtils;
import com.emcloud.loc.service.AddressService;
import com.emcloud.loc.domain.Address;
import com.emcloud.loc.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZonedDateTime;


/**
 * Service Implementation for managing Address.
 */
@Service
@Transactional
public class AddressServiceImpl implements AddressService{

    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Save a address.
     *
     * @param address the entity to save
     * @return the persisted entity
     */
    @Override
    public Address save(Address address) {
        log.debug("Request to save Address : {}", address);
        address.setCreatedBy(SecurityUtils.getCurrentUserLogin());
        address.setCreateTime(Instant.now());
        address.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        address.setUpdateTime(Instant.now());

        return addressRepository.save(address);
    }

    /**
     * update a address.
     *
     * @param address the entity to update
     * @return the persisted entity
     */
    @Override
    public Address update(Address address) {
        log.debug("Request to update Address : {}", address);
        address.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        address.setUpdateTime(Instant.now());
        return addressRepository.save(address);
    }

    /**
     *  Get all the addresses.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Address> findAll(Pageable pageable) {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll(pageable);
    }

    /**
     *  Get one address by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Address findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findOne(id);
    }

    /**
     *  Delete the  address by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.delete(id);
    }

    /**
     *  Get one address by id.
     *
     *  @param addressName the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Address> findByaddressName(Pageable pageable,String addressName) {
        log.debug("Request to get Address : {}", addressName);
        return addressRepository.findAllByAddressNameContaining(pageable,addressName);
    }
}
