package org.example.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.example.services.PublishOpp;

public class PublishOppHandler implements JobHandler {
    PublishOpp publish = new PublishOpp();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        publish.publish();
        client.newCompleteCommand(job.getKey()).send().join();
    }

}
