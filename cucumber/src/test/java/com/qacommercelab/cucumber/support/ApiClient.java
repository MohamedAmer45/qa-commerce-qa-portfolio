package com.qacommercelab.cucumber.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qacommercelab.utils.ConfigReader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sends REST requests to the QA Commerce Lab API and keeps the last response.
 * One instance is created per scenario by PicoContainer.
 */
public class ApiClient {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    private final Map<String, String> headers = new LinkedHashMap<>();

    private HttpResponse<String> response;
    private long elapsedMilliseconds;

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public void send(String method, String path, String body) {

        HttpRequest.Builder request = HttpRequest.newBuilder(
                URI.create(ConfigReader.get("baseUrl") + path))
                .timeout(Duration.ofSeconds(30));

        if (body != null) {
            request.header("Content-Type", "application/json");
        }

        headers.forEach(request::header);

        request.method(
                method.toUpperCase(),
                body == null
                        ? HttpRequest.BodyPublishers.noBody()
                        : HttpRequest.BodyPublishers.ofString(body));

        long start = System.nanoTime();

        try {
            response = client.send(
                    request.build(),
                    HttpResponse.BodyHandlers.ofString());

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Request failed: " + method + " " + path, exception);

        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "Request interrupted: " + method + " " + path, exception);
        }

        elapsedMilliseconds = (System.nanoTime() - start) / 1_000_000;
    }

    public int status() {
        return requireResponse().statusCode();
    }

    public String body() {
        return requireResponse().body();
    }

    public String header(String name) {
        return requireResponse().headers().firstValue(name).orElse(null);
    }

    public long elapsedMilliseconds() {
        return elapsedMilliseconds;
    }

    /**
     * Resolves a dotted path such as "data.id" or "data.0.name" against the response body.
     * A missing segment yields a MissingNode instead of an exception.
     */
    public JsonNode json(String path) {

        JsonNode node;

        try {
            node = MAPPER.readTree(body());
        } catch (IOException exception) {
            throw new AssertionError(
                    "Response body is not valid JSON: " + body(), exception);
        }

        if (node == null) {
            throw new AssertionError("Response body is empty.");
        }

        for (String segment : path.split("\\.")) {
            node = segment.matches("\\d+")
                    ? node.path(Integer.parseInt(segment))
                    : node.path(segment);
        }

        return node;
    }

    private HttpResponse<String> requireResponse() {
        if (response == null) {
            throw new IllegalStateException("No API request has been sent in this scenario.");
        }

        return response;
    }
}
