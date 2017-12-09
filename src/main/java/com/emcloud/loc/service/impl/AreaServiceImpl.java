package com.emcloud.loc.service.impl;

import com.emcloud.loc.domain.Area;
import com.emcloud.loc.security.SecurityUtils;
import com.emcloud.loc.service.AreaService;
import com.emcloud.loc.repository.AreaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


/**
 * Service Implementation for managing Area.
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService{

    private final Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    /**
     * Save a area.
     *
     * @param area the entity to save
     * @return the persisted entity
     */
    @Override
    public Area save(Area area) {
        log.debug("Request to save Area : {}", area);
        area.createdBy(SecurityUtils.getCurrentUserLogin());
        area.setCreateTime(Instant.now());
        area.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        area.setUpdateTime(Instant.now());
        return areaRepository.save(area);
    }

    /**
     * update a area.
     *
     * @param area the entity to update
     * @return the persisted entity
     */
    @Override
    public Area update(Area area) {
        log.debug("Request to update Area : {}", area);
        area.setUpdatedBy(SecurityUtils.getCurrentUserLogin());
        area.setUpdateTime(Instant.now());
        return areaRepository.saveAndFlush(area);
    }


    /**
     *  Get all the areas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Area> findAll(Pageable pageable) {
        log.debug("Request to get all Areas");
        return areaRepository.findAll(pageable);
    }

    /**
     *  Get one area by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Area findOne(Long id) {
        log.debug("Request to get Area : {}", id);
        return areaRepository.findOne(id);
    }

    /**
     *  Delete the  area by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Area : {}", id);
        areaRepository.delete(id);
    }

    /**
     *  Get the areas by areaname or areacode.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Area> findAllByAreaNameOrAreaCode(Pageable pageable,String areaName,String areaCode) {
        log.debug("Request to get by areaname or areacode");
        return areaRepository.findAllByAreaNameOrAreaCodeContaining(pageable,areaName,areaCode);
    }
}
