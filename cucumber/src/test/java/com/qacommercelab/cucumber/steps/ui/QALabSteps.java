package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class QALabSteps {

    private final Pages pages;

    public QALabSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("I open the QA Lab page")
    public void iOpenTheQALabPage() {
        pages.qaLab().open();
    }

    @When("I trigger the delayed DOM element")
    public void iTriggerTheDelayedDomElement() {
        pages.qaLab().triggerDelayedDom();
    }

    @When("I run the QA Lab scenario {string}")
    public void iRunTheQALabScenario(String scenario) {
        pages.qaLab().triggerScenario(scenario);
    }

    @When("I open the QA Lab modal")
    public void iOpenTheQALabModal() {
        pages.qaLab().openModal();
    }

    @When("I cancel the QA Lab modal")
    public void iCancelTheQALabModal() {
        pages.qaLab().cancelModal();
    }

    @When("I confirm the QA Lab modal")
    public void iConfirmTheQALabModal() {
        pages.qaLab().confirmModal();
    }

    @Then("the dynamic element should appear with the text {string}")
    public void theDynamicElementShouldAppearWithTheText(String expected) {
        Assert.assertTrue(
                pages.qaLab().waitForDynamicElement(),
                "Dynamic element did not appear.");

        Assert.assertEquals(
                pages.qaLab().getDynamicElementText(),
                expected);
    }

    @Then("the QA Lab status should be {int}")
    public void theQALabStatusShouldBe(int expected) {
        Assert.assertEquals(
                pages.qaLab().getStatusFromOutput(),
                expected);
    }

    @Then("the QA Lab response should have taken at least {int} milliseconds")
    public void theQALabResponseShouldHaveTakenAtLeast(int milliseconds) {
        Assert.assertTrue(
                pages.qaLab().getElapsedMilliseconds() >= milliseconds,
                "Scenario completed faster than the intentional delay.");
    }

    @Then("the QA Lab response should have no body")
    public void theQALabResponseShouldHaveNoBody() {
        Assert.assertTrue(
                pages.qaLab().getOutput().contains("\"body\": null"),
                "A 204 response should contain no response body.");
    }

    @Then("the QA Lab Retry-After value should be {string}")
    public void theQALabRetryAfterValueShouldBe(String expected) {
        Assert.assertEquals(
                pages.qaLab().getRetryAfter(),
                expected);
    }

    @Then("the QA Lab response should not expose server internals")
    public void theQALabResponseShouldNotExposeServerInternals() {
        Assert.assertFalse(
                pages.qaLab().getOutput().toLowerCase().contains("node_modules"),
                "Server stack information should not be exposed.");
    }

    @Then("the QA Lab response should be larger than {int} characters")
    public void theQALabResponseShouldBeLargerThan(int length) {
        Assert.assertTrue(
                pages.qaLab().getOutput().length() > length,
                "Large response was unexpectedly small.");
    }

    @Then("the QA Lab modal should be displayed")
    public void theQALabModalShouldBeDisplayed() {
        Assert.assertTrue(
                pages.qaLab().isModalDisplayed(),
                "Modal was not displayed.");
    }

    @Then("the QA Lab modal should be closed")
    public void theQALabModalShouldBeClosed() {
        Assert.assertFalse(
                pages.qaLab().isModalDisplayed(),
                "Modal should be closed.");
    }
}
