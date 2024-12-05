package org.example.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.example.services.CreateReservation;

public class CreateReservationHandler implements JobHandler {
    CreateReservation createReservation = new CreateReservation();

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        createReservation.create();
        client.newCompleteCommand(job.getKey()).send().join();
    }
}
