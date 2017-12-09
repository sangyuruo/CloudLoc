package com.emcloud.loc.repository;

import com.emcloud.loc.domain.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Area entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
   // @Query("select a from Area a where a.areaCode like %xx%")

    Page<Area> findAllByAreaNameOrAreaCodeContaining(Pageable pageable,String areaName,String areaCode);
   // Page<Area> findAllByAreaNameContaining(Pageable pageable,String areaName);
}
