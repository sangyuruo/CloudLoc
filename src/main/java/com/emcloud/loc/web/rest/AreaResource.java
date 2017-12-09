package com.emcloud.loc.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.emcloud.loc.domain.AlArea;
import com.emcloud.loc.domain.Area;

import com.emcloud.loc.domain.Emm;
import com.emcloud.loc.service.AreaService;
import com.emcloud.loc.web.rest.errors.BadRequestAlertException;
import com.emcloud.loc.web.rest.util.HeaderUtil;
import com.emcloud.loc.web.rest.util.HttpUtils;
import com.emcloud.loc.web.rest.util.PaginationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import jodd.util.StringUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Area.
 */
@RestController
@RequestMapping("/api")
public class AreaResource {

    private final Logger log = LoggerFactory.getLogger(AreaResource.class);

    private static final String ENTITY_NAME = "area";

    private AreaService areaService;

    public AreaResource(AreaService areaService) {
        this.areaService = areaService;
    }


    /**
     * POST  /areas : Create a new area.
     *
     * @param area the area to create
     * @return the ResponseEntity with status 201 (Created) and with body the new area, or with status 400 (Bad Request) if the area has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/areas")
    @Timed
    public ResponseEntity<Area> createArea(@Valid @RequestBody Area area) throws URISyntaxException {
        log.debug("REST request to save Area : {}", area);
        if (area.getId() != null) {
            throw new BadRequestAlertException("A new area cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Area result = areaService.save(area);
        return ResponseEntity.created(new URI("/api/areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /areas : Updates an existing area.
     *
     * @param area the area to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated area,
     * or with status 400 (Bad Request) if the area is not valid,
     * or with status 500 (Internal Server Error) if the area couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/areas")
    @Timed
    public ResponseEntity<Area> updateArea(@Valid @RequestBody Area area) throws URISyntaxException {
        log.debug("REST request to update Area : {}", area);
        if (area.getId() == null) {
            return createArea(area);
        }
        Area result = areaService.save(area);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, area.getId().toString()))
            .body(result);
    }

    /**
     * GET  /areas : get all the areas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of areas in body
     */
    @GetMapping("/areas")
    @Timed
    public ResponseEntity<List<Area>> getAllAreas(@ApiParam org.springframework.data.domain.Pageable pageable) {
        log.debug("REST request to get all Areas");
        Page<Area> page = areaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/Areas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

   /**
     * GET  /areas : get all the areas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of areas in body
     */
    @GetMapping("/areas/byname")
    @Timed
    public ResponseEntity<List<Area>> getAllAreasByAreaName(@ApiParam Pageable pageable, @PathVariable String areaName ) {
        log.debug("REST request to get all Areas");
        Page<Area> page;
        if(StringUtil.isBlank(areaName))
        {
            page = areaService.findAll(pageable);

        }
        else
        {
            String areaCode = areaName;
            page = areaService.findAllByAreaNameOrAreaCode(pageable,areaName,areaCode);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/Areas/addressName");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


    /**
     * GET  /areas/:id : get the "id" area.
     *
     * @param id the id of the area to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the area, or with status 404 (Not Found)
     */
    @GetMapping("/areas/{id}")
    @Timed
    public ResponseEntity<Area> getArea(@PathVariable Long id) {
        log.debug("REST request to get Area : {}", id);
        Area area = areaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(area));
    }

    /**
     * DELETE  /areas/:id : delete the "id" area.
     *
     * @param id the id of the area to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/areas/{id}")
    @Timed
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        log.debug("REST request to delete Area : {}", id);
        areaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

   /* public void fetchJson() {
        String host = "http://jisuarea.market.alicloudapi.com";
        String path = "/area/all";
        String method = "GET";
        String appcode = "2d64ca52435743b1934fd761a4ea3655";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            ObjectMapper mapper = new ObjectMapper();
            Emm beanList = mapper.readValue(EntityUtils.toString(response.getEntity()), Emm.class);
            //把json转成list/map数据格式
            for (AlArea alArea : beanList.getResult()) {
                Area area = new Area();
                area.setId(alArea.getId());
                area.setAreaCode(alArea.getAreacode());
                area.setAreaName(alArea.getName());
                area.setParentId(alArea.getParentid());
                area.setParentName(alArea.getParentname());
                area.setZipCode(alArea.getZipcode());
                area.setDepth(alArea.getDepth());
                area.setUpdatedBy(area.getUpdatedBy());
                area.setUpdateTime(area.getUpdateTime());
                area.setCreatedBy(area.getCreatedBy());
                area.setCreateTime(area.getUpdateTime());
                System.out.println("最新代码");
                areaService.save(area);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
