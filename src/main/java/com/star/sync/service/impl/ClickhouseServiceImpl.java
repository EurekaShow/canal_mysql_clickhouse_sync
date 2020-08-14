package com.star.sync.service.impl;

import com.star.sync.service.ClickhouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@Service
public class ClickhouseServiceImpl implements ClickhouseService {
    private static final Logger logger = LoggerFactory.getLogger(ClickhouseServiceImpl.class);

//    @Resource
//    private ClickhouseClient client;


    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public void insert(String sql,Object... params)  {
        try {
            jdbcTemplate.update(sql,params);

        } catch (Throwable e) {
            logger.error("Clickhouse插入错误, INSERT SQL=" + sql, e);
        }

    }

    @Override
    public void batchInsertById(String table, Map<String, Map<String, Object>> idDataMap) {

        idDataMap.forEach((id, dataMap) ->{
//            this.insert(table,dataMap);
        });
    }

    @Override
    public void update(String sql,Object... params) {
        try {
            jdbcTemplate.update(sql,params);

        } catch (Throwable e) {
            logger.error("Clickhouse更新错误, SQL=" + sql, e);
        }
    }

    @Override
    public void deleteById(String sql, Object... params) {

        try {
            jdbcTemplate.update(sql,params);

        } catch (Throwable e) {
            logger.error("Clickhouse删除错误, SQL=" + sql, e);
        }
    }
}
