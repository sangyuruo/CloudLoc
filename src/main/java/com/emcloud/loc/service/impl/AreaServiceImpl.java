package com.emcloud.loc.service.impl;

import com.emcloud.loc.security.SecurityUtils;
import com.emcloud.loc.service.AreaService;
import com.emcloud.loc.domain.Area;
import com.emcloud.loc.repository.AreaRepository;
import com.emcloud.loc.web.rest.util.HttpUtils;
import org.apache.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


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
        area.setCreatedBy(SecurityUtils.getCurrentUserLogin());
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
        return areaRepository.save(area);
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
    public static void main(String[] args) {
        String host = "http://jisuarea.market.alicloudapi.com";
        String path = "/area/all";
        String method = "GET";
        String appcode = "你自己的AppCode";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
