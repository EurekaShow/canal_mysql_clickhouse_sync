package com.star.sync.service;

import com.star.sync.model.DatabaseTableModel;
import com.star.sync.model.IndexTypeModel;

import java.util.Map;
/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
public interface MappingService {

    /**
     * 获取数据库表的主键映射
     */
    Map<String, String> getTablePrimaryKeyMap();

    /**
     * 获取Elasticsearch的数据转换后类型
     *
     * @param mysqlType mysql数据类型
     * @param data      具体数据
     * @return Elasticsearch对应的数据类型
     */
    Object getClickhoseTypeObject(String mysqlType, String data);
}
