package com.ihxjie.mondayserver.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ihxjie.mondayserver.api.map.MapVo;
import com.ihxjie.mondayserver.api.util.GouldUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author xjie
 * @date 2021/6/2 18:07
 */
@RestController
@RequestMapping("/api/map")
public class MapController {
    @Autowired
    private GouldUtil gouldUtil;

    public static final Logger log = LoggerFactory.getLogger(MapController.class);

    @GetMapping("/queryAddressByKeyword")
    public List<MapVo> queryAddressByKeyword(String keyword){
        return gouldUtil.getAddressByKeyword(keyword);
    }

}