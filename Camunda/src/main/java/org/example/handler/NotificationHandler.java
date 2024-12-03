package org.example.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.example.services.Notification;

public class NotificationHandler implements JobHandler {
    Notification notification = new Notification();
    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        notification.notifie();
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
