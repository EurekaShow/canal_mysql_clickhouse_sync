package com.star.sync.listener;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.star.sync.event.DeleteCanalEvent;
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
public class DeleteCanalListener extends AbstractCanalListener<DeleteCanalEvent> {
    private static final Logger logger = LoggerFactory.getLogger(DeleteCanalListener.class);

    @Resource
    private ClickhouseService clickhouseService;
    //private ElasticsearchService elasticsearchService;

    @Override
    protected void doSync(String database, String table, RowData rowData) {

        List<Column> columns = rowData.getBeforeColumnsList();

        Optional<Column> idColumn = columns.stream().filter(t -> t.getIsKey()).findFirst();

        if (!idColumn.isPresent() || StringUtils.isBlank(idColumn.get().getValue())) {
            logger.warn("DELETE 从column中找不到主键,database=" + database + ",table=" + table);
            return;
        }
        String sql = "ALTER TABLE ".concat(table).concat(" delete WHERE ").concat(idColumn.get().getName()).concat(" =?");

        clickhouseService.deleteById(sql,idColumn.get().getValue());

        logger.debug("DELETE 同步ch操作成功！database=" + database + ",table=" + table + ",id=" + idColumn.get().getValue());
    }
}
