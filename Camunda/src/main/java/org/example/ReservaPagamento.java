package org.example;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import org.example.handler.CreateReservationHandler;
import org.example.handler.NotificationHandler;
import org.example.handler.PaymentHandler;
import org.example.handler.PublishOppHandler;

import java.time.Duration;

public class ReservaPagamento extends App{

    public static void main(String[] args) {

        final OAuthCredentialsProvider credentialsProvider =
                new OAuthCredentialsProviderBuilder()
                        .authorizationServerUrl(ZEEBE_AUTHORIZATION_SERVER_URL)
                        .audience(ZEEBE_TOKEN_AUDIENCE)
                        .clientId(ZEEBE_CLIENT_ID)
                        .clientSecret(ZEEBE_CLIENT_SECRET)
                        .build();

        try (final ZeebeClient client =
                     ZeebeClient.newClientBuilder()
                             .gatewayAddress(ZEEBE_ADDRESS)
                             .credentialsProvider(credentialsProvider)
                             .build()) {
            final JobWorker paymentWorker =
                    client.newWorker()
                            .jobType("service_process_payment")
                            .handler(new PaymentHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
            final JobWorker creationWorker =
                    client.newWorker()
                            .jobType("service_create_reservation")
                            .handler(new CreateReservationHandler())
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);
            final JobWorker NotificationWorker =
                    client.newWorker()
                            .jobType("notification")
                            .handler(new NotificationHandler()  )
                            .timeout(Duration.ofSeconds(10).toMillis())
                            .open();
            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
