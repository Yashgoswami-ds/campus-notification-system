package com.campus.service.scheduler;


import com.campus.service.entity.NotificationPriority;

import java.util.Comparator;

public class NotificationTaskComparator
        implements Comparator<NotificationTask> {

    @Override
    public int compare(NotificationTask t1, NotificationTask t2) {

        int priorityCompare = Integer.compare(
                getPriorityValue(t2.getPriority()),
                getPriorityValue(t1.getPriority())
        );

        // Same priority → older task first
        if (priorityCompare == 0) {
            return t1.getCreatedAt()
                    .compareTo(t2.getCreatedAt());
        }

        return priorityCompare;
    }

    private int getPriorityValue(NotificationPriority priority) {

        return switch (priority) {
            case URGENT -> 4;
            case HIGH -> 3;
            case MEDIUM -> 2;
            case LOW -> 1;
        };
    }
}