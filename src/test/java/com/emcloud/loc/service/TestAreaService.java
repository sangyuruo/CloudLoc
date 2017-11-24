package com.emcloud.loc.service;
import com.emcloud.loc.EmCloudLocApp;
import com.emcloud.loc.domain.AlArea;
import com.emcloud.loc.domain.Area;
import com.emcloud.loc.domain.Emm;
import com.emcloud.loc.service.impl.AreaServiceImpl;
import com.emcloud.loc.web.rest.util.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
/**
 * Test class for the UserResource REST controller.
 *
 * @see TestAreaService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmCloudLocApp.class)
//@Transactional
public class TestAreaService {

    @Autowired
    private AreaServiceImpl areaService;
    @Test
  //  @Transactional
    public void fetchJson() {

        String host = "http://jisuarea.market.alicloudapi.com";
        String path = "/area/all";
        String method = "GET";
        String appcode = "2d64ca52435743b1934fd761a4ea3655";
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
            ObjectMapper mapper = new ObjectMapper();
            Emm beanList = mapper.readValue(EntityUtils.toString(response.getEntity()), Emm.class);
            //把json转成list/map数据格式
            int i=0;
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
                i++;
                System.out.println("阿紫=========================================阿紫=============================================阿紫"+i);
                areaService.save(area);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
