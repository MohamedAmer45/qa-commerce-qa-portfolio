package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.QALabPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("QA Commerce Lab")
@Feature("QA Lab")
public class QALabTests extends BaseTest {

    @Test(groups = { "smoke", "qa-lab" }, description = "Delayed DOM element appears after trigger")
    public void delayedDomElementAppears() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerDelayedDom();

        Assert.assertTrue(
                page.waitForDynamicElement(),
                "Dynamic element did not appear.");

        Assert.assertEquals(
                page.getDynamicElementText(),
                "Dynamic element appeared after 900 ms.");
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Slow scenario returns HTTP 200 after intentional delay")
    public void slowScenarioIsHandled() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-async");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                200);

        Assert.assertTrue(
                page.getElapsedMilliseconds() >= 1400,
                "Slow scenario completed faster than expected.");
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Empty scenario returns HTTP 204")
    public void emptyScenarioReturns204() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-204");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                204);

        Assert.assertTrue(
                page.getOutput().contains("\"body\": null"),
                "204 response should contain no response body.");
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Bad request scenario returns HTTP 400")
    public void badRequestScenarioReturns400() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-400");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                400);
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Unauthorized scenario returns HTTP 401")
    public void unauthorizedScenarioReturns401() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-401");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                401);
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Not found scenario returns HTTP 404")
    public void notFoundScenarioReturns404() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-404");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                404);
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Conflict scenario returns HTTP 409")
    public void conflictScenarioReturns409() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-409");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                409);
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Validation scenario returns HTTP 422")
    public void validationScenarioReturns422() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-422");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                422);
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Rate limit scenario returns 429 and Retry-After header")
    public void rateLimitScenarioReturns429() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-429");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                429);

        Assert.assertEquals(
                page.getRetryAfter(),
                "5",
                "Unexpected Retry-After value.");
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Server error scenario returns controlled HTTP 500")
    public void serverErrorScenarioReturns500() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-500");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                500);

        Assert.assertFalse(
                page.getOutput()
                        .toLowerCase()
                        .contains("node_modules"),
                "Server stack information should not be exposed.");
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Large response scenario succeeds")
    public void largeResponseScenarioSucceeds() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.triggerScenario("lab-big");

        Assert.assertEquals(
                page.getStatusFromOutput(),
                200);

        Assert.assertTrue(
                page.getOutput().length() > 1000,
                "Large response was unexpectedly small.");
    }

    @Test(groups = { "smoke", "qa-lab" }, description = "Modal can be opened and cancelled")
    public void modalCanBeCancelled() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.openModal();

        Assert.assertTrue(
                page.isModalDisplayed(),
                "Modal was not displayed.");

        page.cancelModal();

        Assert.assertFalse(
                page.isModalDisplayed(),
                "Modal should close after Cancel.");
    }

    @Test(groups = { "regression", "qa-lab" }, description = "Modal can be opened and confirmed")
    public void modalCanBeConfirmed() {

        QALabPage page = new QALabPage(driver);

        page.open();
        page.openModal();

        Assert.assertTrue(
                page.isModalDisplayed());

        page.confirmModal();

        Assert.assertFalse(
                page.isModalDisplayed(),
                "Modal should close after Confirm.");
    }
}
