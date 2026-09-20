package com.qacommercelab.cucumber.steps.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.qacommercelab.cucumber.support.ApiClient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ApiSteps {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final ApiClient api;

    public ApiSteps(ApiClient api) {
        this.api = api;
    }

    // ---------------------------------------------------------------- requests

    @Given("I set the request header {string} to {string}")
    public void iSetTheRequestHeader(String name, String value) {
        api.setHeader(name, value);
    }

    @When("I send a {word} request to {string}")
    public void iSendARequestTo(String method, String path) {
        api.send(method, path, null);
    }

    @When("I send a {word} request to {string} with body:")
    public void iSendARequestToWithBody(String method, String path, String body) {
        api.send(method, path, body);
    }

    @When("I search the API for a query of {int} {string} characters")
    public void iSearchTheApiForAQueryOfCharacters(int length, String character) {
        api.send("GET", "/api/search?q=" + character.repeat(length), null);
    }

    /**
     * The quantity is a {word} rather than an {int} so scenarios can also send
     * invalid values such as -1 or 1.5.
     */
    @When("I place an order for product {int} with quantity {word} using card {string}")
    public void iPlaceAnOrderForProduct(int productId, String quantity, String card) {
        String body = """
                {
                  "items": [{ "id": %d, "qty": %s }],
                  "shipping": { "email": "qa.user@example.com", "address": "123 QA Street" },
                  "payment": { "cardNumber": "%s" }
                }
                """.formatted(productId, quantity, card);

        api.send("POST", "/api/orders", body);
    }

    /**
     * Builds an order request from the supplied rows. Supported keys: items (a JSON array),
     * email, address and card. A missing or blank row is omitted from the request, which
     * lets scenarios describe incomplete orders.
     */
    @When("I place an order with these details:")
    public void iPlaceAnOrderWithTheseDetails(DataTable details) throws JsonProcessingException {
        Map<String, String> values = details.asMap(String.class, String.class);

        ObjectNode order = MAPPER.createObjectNode();

        // Empty table cells are read as null, so a blank row is treated as "not supplied".
        String items = values.get("items");
        if (items != null && !items.isBlank()) {
            order.set("items", MAPPER.readTree(items));
        }

        ObjectNode shipping = MAPPER.createObjectNode();
        putIfPresent(shipping, "email", values.get("email"));
        putIfPresent(shipping, "address", values.get("address"));
        if (!shipping.isEmpty()) {
            order.set("shipping", shipping);
        }

        ObjectNode payment = MAPPER.createObjectNode();
        putIfPresent(payment, "cardNumber", values.get("card"));
        if (!payment.isEmpty()) {
            order.set("payment", payment);
        }

        api.send("POST", "/api/orders", MAPPER.writeValueAsString(order));
    }

    // ------------------------------------------------------------- status/header

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expected) {
        Assert.assertEquals(
                api.status(),
                expected,
                "Unexpected HTTP status. Body: " + api.body());
    }

    @Then("the response should have no body")
    public void theResponseShouldHaveNoBody() {
        Assert.assertTrue(
                api.body() == null || api.body().isEmpty(),
                "Expected an empty body but received: " + api.body());
    }

    @Then("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String name, String expected) {
        Assert.assertEquals(
                api.header(name),
                expected,
                "Unexpected value for header " + name + ".");
    }

    @Then("the response header {string} should contain {string}")
    public void theResponseHeaderShouldContain(String name, String expected) {
        String actual = api.header(name);

        Assert.assertNotNull(actual, "Header " + name + " was not returned.");
        Assert.assertTrue(
                actual.toLowerCase().contains(expected.toLowerCase()),
                "Header " + name + " was '" + actual + "', expected to contain '" + expected + "'.");
    }

    @Then("the response body should not contain {string}")
    public void theResponseBodyShouldNotContain(String text) {
        Assert.assertFalse(
                api.body().contains(text),
                "Response body unexpectedly contains '" + text + "'.");
    }

    @Then("the response time should be at least {int} milliseconds")
    public void theResponseTimeShouldBeAtLeast(int milliseconds) {
        Assert.assertTrue(
                api.elapsedMilliseconds() >= milliseconds,
                "Response took " + api.elapsedMilliseconds() + " ms, expected at least " + milliseconds + " ms.");
    }

    // --------------------------------------------------------------- JSON fields

    @Then("the JSON field {string} should equal {string}")
    public void theJsonFieldShouldEqual(String path, String expected) {
        JsonNode node = requireField(path);

        if (node.isNumber() && isNumeric(expected)) {
            Assert.assertEquals(
                    node.decimalValue().compareTo(new BigDecimal(expected)),
                    0,
                    "Field " + path + " was " + node + ", expected " + expected + ".");
            return;
        }

        Assert.assertEquals(
                node.asText(),
                expected,
                "Unexpected value for field " + path + ".");
    }

    @Then("the JSON field {string} should not exist")
    public void theJsonFieldShouldNotExist(String path) {
        Assert.assertTrue(
                api.json(path).isMissingNode(),
                "Field " + path + " should not be present.");
    }

    @Then("the JSON field {string} should be a non-empty string")
    public void theJsonFieldShouldBeANonEmptyString(String path) {
        JsonNode node = requireField(path);

        Assert.assertTrue(node.isTextual(), "Field " + path + " is not a string.");
        Assert.assertFalse(node.asText().isBlank(), "Field " + path + " is empty.");
    }

    @Then("the JSON field {string} should be a valid timestamp")
    public void theJsonFieldShouldBeAValidTimestamp(String path) {
        String value = requireField(path).asText();

        try {
            Instant.parse(value);
        } catch (Exception exception) {
            Assert.fail("Field " + path + " is not an ISO-8601 timestamp: " + value);
        }
    }

    // --------------------------------------------------------------- JSON arrays

    @Then("the JSON array {string} should have {int} item(s)")
    public void theJsonArrayShouldHaveItems(String path, int expected) {
        Assert.assertEquals(
                requireArray(path).size(),
                expected,
                "Unexpected number of items in " + path + ".");
    }

    @Then("the JSON array {string} should have at least {int} item(s)")
    public void theJsonArrayShouldHaveAtLeastItems(String path, int minimum) {
        Assert.assertTrue(
                requireArray(path).size() >= minimum,
                "Expected at least " + minimum + " items in " + path + ".");
    }

    @Then("every item in {string} should have {string} equal to {string}")
    public void everyItemShouldHaveFieldEqualTo(String path, String field, String expected) {
        for (JsonNode item : requireArray(path)) {
            Assert.assertEquals(
                    item.path(field).asText(),
                    expected,
                    "Item " + item + " has an unexpected " + field + ".");
        }
    }

    @Then("every item in {string} should have {string} greater than {int}")
    public void everyItemShouldHaveFieldGreaterThan(String path, String field, int minimum) {
        for (JsonNode item : requireArray(path)) {
            Assert.assertTrue(
                    item.path(field).asDouble() > minimum,
                    "Item " + item + " does not have " + field + " greater than " + minimum + ".");
        }
    }

    @Then("the {string} values in {string} should be sorted {string}")
    public void theValuesShouldBeSorted(String field, String path, String direction) {
        List<Double> actual = new ArrayList<>();

        for (JsonNode item : requireArray(path)) {
            actual.add(item.path(field).asDouble());
        }

        List<Double> expected = new ArrayList<>(actual);

        expected.sort(direction.equalsIgnoreCase("descending")
                ? Comparator.reverseOrder()
                : Comparator.naturalOrder());

        Assert.assertEquals(actual, expected, "Values of " + field + " are not sorted " + direction + ".");
    }

    // ------------------------------------------------------------------- helpers

    private JsonNode requireField(String path) {
        JsonNode node = api.json(path);

        Assert.assertFalse(
                node.isMissingNode(),
                "Field " + path + " was not found in: " + api.body());

        return node;
    }

    private JsonNode requireArray(String path) {
        JsonNode node = requireField(path);

        Assert.assertTrue(node.isArray(), "Field " + path + " is not an array.");

        return node;
    }

    private void putIfPresent(ObjectNode target, String name, String value) {
        if (value != null && !value.isBlank()) {
            target.put(name, value);
        }
    }

    private boolean isNumeric(String value) {
        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
