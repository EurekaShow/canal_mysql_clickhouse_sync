package com.star.sync.service.impl;

import com.google.common.collect.Maps;
//import com.star.sync.model.DatabaseTableModel;
//import com.star.sync.model.IndexTypeModel;
import com.star.sync.service.MappingService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@Service
//@PropertySource("classpath:mapping.properties")
//@ConfigurationProperties
public class MappingServiceImpl implements MappingService, InitializingBean {
    //private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    private Map<String, String> dbEsMapping;
//    private BiMap<DatabaseTableModel, IndexTypeModel> dbEsBiMapping;
    private Map<String, String> tablePrimaryKeyMap;
    private Map<String, Converter> mysqlTypeMapping;

    @Override
    public Map<String, String> getTablePrimaryKeyMap() {
        return tablePrimaryKeyMap;
    }

    @Override
    public Object getClickhoseTypeObject(String mysqlType, String data) {
        Optional<Entry<String, Converter>> result = mysqlTypeMapping.entrySet().parallelStream().filter(
                entry -> mysqlType.toLowerCase().contains(entry.getKey())
        ).findFirst();
        return (result.isPresent() ? result.get().getValue() : (Converter) data1 -> data1).convert(data);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        dbEsBiMapping = HashBiMap.create();
//        dbEsMapping.forEach((key, value) -> {
//            String[] keyStrings = StringUtils.split(key, ".");
//            String[] valueStrings = StringUtils.split(value, ".");
//            dbEsBiMapping.put(new DatabaseTableModel(keyStrings[0], keyStrings[1]), new IndexTypeModel(valueStrings[0], valueStrings[1]));
//        });

        mysqlTypeMapping = Maps.newHashMap();
        mysqlTypeMapping.put("char", data -> data);
        mysqlTypeMapping.put("text", data -> data);
        mysqlTypeMapping.put("blob", data -> data);
        mysqlTypeMapping.put("bigchar", data -> data);
        mysqlTypeMapping.put("int", Integer::valueOf);
        mysqlTypeMapping.put("smallint", Integer::valueOf);
        mysqlTypeMapping.put("bool", Integer::valueOf);
        mysqlTypeMapping.put("tinyint", Integer::valueOf);

//        mysqlTypeMapping.put("date", data -> LocalDateTime.parse(data, formatter));
//        mysqlTypeMapping.put("time", data -> LocalDateTime.parse(data, formatter));
        mysqlTypeMapping.put("date", data -> formatter.parse(data, new ParsePosition(0)));
        mysqlTypeMapping.put("time", data -> formatter.parse(data,new ParsePosition(0)));

        mysqlTypeMapping.put("float", Double::valueOf);
        mysqlTypeMapping.put("double", Double::valueOf);
        mysqlTypeMapping.put("decimal", Double::valueOf);
    }

//    public Map<String, String> getDbEsMapping() {
//        return dbEsMapping;
//    }
//
//    public void setDbEsMapping(Map<String, String> dbEsMapping) {
//        this.dbEsMapping = dbEsMapping;
//    }

    private interface Converter {
        Object convert(String data);
    }

//      "date": "Date",
//      "datetime": "DateTime",
//      "bool": "UInt8",
//      "float": "Float32",
//      "double": "Float64",
//      "varchar": "String",
//      "decimal": "Decimal{digits}",
//      "tinyint": "Int8",
//      "int": "Int32",
//      "smallint": "Int16",
//      "mediumint": "Int32",
//      "bigint": "Int64",
//      "timestamp": "DateTime",
//      "char": "FixedString",
//      "bigchar": "String"
}
