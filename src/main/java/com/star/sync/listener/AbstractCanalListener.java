package com.star.sync.listener;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.google.protobuf.InvalidProtocolBufferException;
import com.star.sync.event.CanalEvent;
import com.star.sync.service.MappingService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
public abstract class AbstractCanalListener<EVENT extends CanalEvent> implements ApplicationListener<EVENT> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCanalListener.class);

    @Resource
    private MappingService mappingService;

    @Override
    public void onApplicationEvent(EVENT event) {
        Entry entry = event.getEntry();
        String database = entry.getHeader().getSchemaName();
        String table = entry.getHeader().getTableName();

        RowChange change;
        try {
            change = RowChange.parseFrom(entry.getStoreValue());
        } catch (InvalidProtocolBufferException e) {
            logger.error("canalEntry_parser_error,根据CanalEntry获取RowChange失败！", e);
            return;
        }
        change.getRowDatasList().forEach(rowData -> doSync(database, table,rowData));
    }

    public String parseColumnsToInsert(List<Column> columns) {
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        keys.append(" (");
        values.append(" VALUES(");
        columns.forEach(column -> {
            if (column == null) {
                return;
            }
            keys.append(",").append(column.getName());
            values.append(",").append("?");
        });
        String sql = keys.append(")").toString().replaceFirst(",","").concat(values.append(")").toString().replaceFirst(",",""));

        return sql;
    }

    public String parseColumnsToUpdate(List<Column> columns) {
        StringBuilder sets = new StringBuilder();

        columns.forEach(column -> {
            if (column == null || column.getIsKey()) {
                return;
            }
            sets.append(",").append(column.getName()).append("=").append("?");
        });
        String sql = sets.toString().replaceFirst(",","");

        return sql;
    }

    public Object[] parseColumnsParams(List<Column> columns) {

        List<Object> params = new ArrayList<Object>(columns.size());
        columns.forEach(column -> {
            if (column == null) {
                return;
            }

            params.add(
                    (column.getValue()==null || StringUtils.isBlank(column.getValue()))
                            ? null
                            : mappingService.getClickhoseTypeObject(column.getMysqlType(), column.getValue()));
        });

        return params.toArray();
    }


    protected abstract void doSync(String database, String table, RowData rowData);
}
