package org.example;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.handler.NotificationHandler;
import org.example.handler.PublishOppHandler;

import java.time.Duration;

/**
 * 
 */
public class App {
    static Dotenv dotenv = Dotenv.configure().load();
    public static final String ZEEBE_ADDRESS = dotenv.get("ZEEBE_ADDRESS");
    public static final String ZEEBE_CLIENT_ID = dotenv.get("ZEEBE_CLIENT_ID");
    public static final String ZEEBE_CLIENT_SECRET = dotenv.get("ZEEBE_CLIENT_SECRET");
    public static final String ZEEBE_AUTHORIZATION_SERVER_URL = dotenv.get("ZEEBE_AUTHORIZATION_SERVER_URL");
    public static final String ZEEBE_TOKEN_AUDIENCE = dotenv.get("ZEEBE_TOKEN_AUDIENCE");

}
