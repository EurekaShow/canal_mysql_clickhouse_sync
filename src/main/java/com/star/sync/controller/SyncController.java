package com.star.sync.controller;

import com.star.sync.model.request.SyncByTableRequest;
import com.star.sync.model.response.Response;
import com.star.sync.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@Controller
@RequestMapping("/sync")
public class SyncController {
    private static final Logger logger = LoggerFactory.getLogger(SyncController.class);

//    @Resource
//    private SyncService syncService;

    /**
     * 通过库名和表名全量同步数据
     *
     * @param request 请求参数
     */
    @RequestMapping("/byTable")
    @ResponseBody
    public String syncTable(@Validated SyncByTableRequest request) {
        logger.debug("request_info: " + JsonUtil.toJson(request));
//        String response = Response.success(syncService.syncByTable(request)).toString();
        logger.debug("response_info: " + JsonUtil.toJson(request));
//        return response;
        return "";
    }
}
