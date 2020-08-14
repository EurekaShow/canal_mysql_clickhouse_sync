package com.star.sync.listener;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.star.sync.event.InsertCanalEvent;
import com.star.sync.service.ClickhouseService;
import com.star.sync.service.MappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
@Component
public class InsertCanalListener extends AbstractCanalListener<InsertCanalEvent> {
    private static final Logger logger = LoggerFactory.getLogger(InsertCanalListener.class);

    @Resource
    private MappingService mappingService;

    @Resource
    private ClickhouseService clickhouseService;
    //private ElasticsearchService elasticsearchService;

    @Override
    protected void doSync(String database, String table, RowData rowData) {
        List<Column> columns = rowData.getAfterColumnsList();
        String sql = "insert into ".concat(table).concat(parseColumnsToInsert(columns));
        clickhouseService.insert(sql,parseColumnsParams(columns));
        logger.debug("insert_ch_info 同步ch插入操作成功！database=" + database + ",table=" + table );
    }
}
