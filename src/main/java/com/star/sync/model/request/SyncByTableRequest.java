package com.star.sync.model.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
public class SyncByTableRequest {
    @NotBlank
    private String database;
    @NotBlank
    private String table;
    private Integer stepSize = 500;
    private Long from;
    private Long to;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Integer getStepSize() {
        return stepSize;
    }

    public void setStepSize(Integer stepSize) {
        this.stepSize = stepSize;
    }
}
