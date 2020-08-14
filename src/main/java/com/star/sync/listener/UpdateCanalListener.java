package com.star.sync.listener;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.star.sync.event.UpdateCanalEvent;
import com.star.sync.service.ClickhouseService;
import com.star.sync.service.MappingService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@Component
public class UpdateCanalListener extends AbstractCanalListener<UpdateCanalEvent> {
    private static final Logger logger = LoggerFactory.getLogger(UpdateCanalListener.class);

    @Resource
    private MappingService mappingService;

    @Resource
    private ClickhouseService clickhouseService;
    //private ElasticsearchService elasticsearchService;

    @Override
    protected void doSync(String database, String table, RowData rowData) {

        List<Column> columns = rowData.getAfterColumnsList();
        Optional<Column> idColumn = columns.stream().filter(t -> t.getIsKey()).findFirst();

         if (!idColumn.isPresent() || StringUtils.isBlank(idColumn.get().getValue())) {
            logger.warn("update从column中找不到主键,database=" + database + ",table=" + table);
            return;
        }

        logger.debug("update主键id,database=" + database + ",table=" + table + ",id=" + idColumn.get().getValue());

//         String sql = "ALTER TABLE ".concat(table).concat(" UPDATE ").concat(parseColumnsToUpdate(columns))
//                .concat(" WHERE ").concat(idColumn.get().getName()).concat("=?");
//
//         List<Object> params = parseColumnsParams(columns,true);
//         params.add(idColumn.get().getValue());

//        clickhouseService.update(sql,params);


        String del_sql = "ALTER TABLE ".concat(table).concat(" delete WHERE ").concat(idColumn.get().getName()).concat(" =?");
        clickhouseService.deleteById(del_sql,idColumn.get().getValue());

        String sql = "insert into ".concat(table).concat(parseColumnsToInsert(columns));
        clickhouseService.insert(sql,parseColumnsParams(columns));

        logger.debug("update_es_info 同步es插入操作成功！database=" + database + ",table=" + table);
    }
}
