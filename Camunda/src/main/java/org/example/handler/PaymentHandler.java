package org.example.handler;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import org.example.services.PaymentProcess;

public class PaymentHandler implements JobHandler {

    PaymentProcess paymentProcess = new PaymentProcess();
    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {
        boolean isConditionMet = paymentProcess.pay();
        client.newCompleteCommand(job.getKey()).variables("{\"decision\": " + isConditionMet + "}").send().join();
    }
}
