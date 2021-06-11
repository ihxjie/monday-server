package com.ihxjie.mondayserver.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ihxjie.mondayserver.api.map.MapInfo;
import com.ihxjie.mondayserver.api.map.MapVo;
import com.ihxjie.mondayserver.api.map.Poi;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xjie
 * @date 2021/6/2 18:06
 */
@Component
public class GouldUtil {
    //在高德申请的应用Key
    private static final String GOULD_KEY= "5b4196996513a1be55be361a7cbcdc3e";

    public List<MapVo> getAddressByKeyword(String keyword){

        if (isContainChinese(keyword)){
            String queryUrl = "https://restapi.amap.com/v5/place/text?key=" + GOULD_KEY + "&keywords=" + keyword;
            String queryResult = getResponse(queryUrl); // 高德接品返回的是JSON格式的字符串
            JSONObject job = JSONObject.parseObject(queryResult);
            MapInfo mapInfo = JSON.toJavaObject(job, MapInfo.class);
            List<Poi> pois = mapInfo.getPois();
            if (pois == null){
                return null;
            }
            List<MapVo> mapVos = new ArrayList<>();
            for (Poi poi : pois){
                MapVo mapVo = new MapVo();
                mapVo.setLabel(poi.getName());
                mapVo.setValue(poi.getId());
                mapVos.add(mapVo);
            }
            // System.out.println(mapVos);
            return mapVos;
        }
        if (keyword == null){
            return null;
        }
        return null;
    }

    public String getAddressById(String id){
        String queryUrl = "https://restapi.amap.com/v5/place/detail?key=" + GOULD_KEY + "&id=" + id;
        String queryResult = getResponse(queryUrl); // 高德接品返回的是JSON格式的字符串
        JSONObject job = JSONObject.parseObject(queryResult);
        MapInfo mapInfo = JSON.toJavaObject(job, MapInfo.class);
        Poi poi = mapInfo.getPois().get(0);

        return poi.getLocation();
    }

    public boolean isContainChinese(String str){
        if (str == null){
            return false;
        }
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 3.发送请求
     * @param serverUrl 请求地址
     */
    private static String getResponse(String serverUrl) {
        // 用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}