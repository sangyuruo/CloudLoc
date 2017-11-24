package test;

import com.emcloud.loc.domain.AlArea;
import com.emcloud.loc.domain.Area;
import com.emcloud.loc.domain.Emm;
import com.emcloud.loc.service.impl.AreaServiceImpl;
import com.emcloud.loc.web.rest.util.HttpUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;

import java.util.*;

public class test {
    private static AreaServiceImpl areaService;

    public static void main(String[] args) {

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
            //EntityUtils.toString(response.getEntity())  json数据

            ObjectMapper mapper = new ObjectMapper();
            //String json =mapper.writeValueAsString(EntityUtils.toString(response.getEntity()));
            Emm beanList = mapper.readValue(EntityUtils.toString(response.getEntity()), Emm.class);

            //把json转成list/map数据格式
            for(AlArea alArea : beanList.getResult()){
                Area area = new Area();
                area.setId(alArea.getId());
                area.setAreaCode(alArea.getAreacode());
                area.setAreaName(alArea.getName());
                area.setParentId(alArea.getParentid());
                area.setParentName(alArea.getParentname());
                area.setZipCode(alArea.getZipcode());
                area.setDepth(alArea.getDepth());
                System.out.println(alArea.getZipcode()+"-------"+alArea.getParentname());
                areaService.save(area);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
