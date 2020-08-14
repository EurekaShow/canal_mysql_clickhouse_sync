package com.star.sync.service;

import io.searchbox.client.JestResult;

import java.util.Map;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
public interface ClickhouseService {
    void insert(String sql,Object... params);

    void batchInsertById(String table,  Map<String, Map<String, Object>> idDataMap);

    void update(String sql,Object... params);

    void deleteById(String sql,Object... params);
}
