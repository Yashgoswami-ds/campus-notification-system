package com.campus.service.scheduler;


import org.springframework.stereotype.Service;

import java.util.PriorityQueue;

@Service
public class NotificationQueueService {
    private  final PriorityQueue<NotificationTask> queue = new
            PriorityQueue<>(new NotificationTaskComparator());

public   void   addTask(NotificationTask task){

    queue.offer(task);

}

public  NotificationTask getNextNotification(){
    return queue.poll();

}

public  NotificationTask  getPeekNotification(){
    return  queue.peek();
}

public  int size(){
    return  queue.size();

}

public  boolean isEmpty(){
    return queue.isEmpty();
}



}
