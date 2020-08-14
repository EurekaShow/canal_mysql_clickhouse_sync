package com.star.sync.event;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;

/**
 * @author eureka
 * @version 1.0
 * @since 2020-08-14
 */
public class UpdateCanalEvent extends CanalEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UpdateCanalEvent(Entry source) {
        super(source);
    }
}
