package com.qacommercelab.cucumber.steps.ui;

import com.qacommercelab.cucumber.support.Pages;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ContactSteps {

    private static final String VALID_MESSAGE = "This message contains more than twenty characters.";

    private final Pages pages;
    private final List<Path> temporaryFiles = new ArrayList<>();

    public ContactSteps(Pages pages) {
        this.pages = pages;
    }

    @After
    public void deleteTemporaryFiles() throws IOException {
        for (Path file : temporaryFiles) {
            Files.deleteIfExists(file);
        }
    }

    @Given("I open the contact page")
    public void iOpenTheContactPage() {
        pages.contact().open();
    }

    @When("I fill in the contact form with valid details")
    public void iFillInTheContactFormWithValidDetails() {
        pages.contact().fillValidForm();
    }

    @When("I complete the contact form with name {string}, email {string} and subject {string}")
    public void iCompleteTheContactFormWithNameEmailAndSubject(
            String name,
            String email,
            String subject) {

        pages.contact().setName(name);
        pages.contact().setEmail(email);

        if (!subject.isBlank()) {
            pages.contact().selectSubject(subject);
        }

        pages.contact().setMessage(VALID_MESSAGE);
    }

    @When("I complete the contact form with a message of {int} characters")
    public void iCompleteTheContactFormWithAMessageOfCharacters(int length) {
        pages.contact().setName("QA Tester");
        pages.contact().setEmail("qa@example.com");
        pages.contact().selectSubject("Technical problem");
        pages.contact().setMessage("A".repeat(length));
    }

    @When("I complete the contact form with the name {string} and the message {string}")
    public void iCompleteTheContactFormWithTheNameAndTheMessage(String name, String message) {
        pages.contact().setName(name);
        pages.contact().setEmail("qa@example.com");
        pages.contact().selectSubject("Technical problem");
        pages.contact().setMessage(message);
    }

    @When("I select the contact subject {string}")
    public void iSelectTheContactSubject(String subject) {
        pages.contact().selectSubject(subject);
    }

    @When("I type {int} characters into the contact message field")
    public void iTypeCharactersIntoTheContactMessageField(int length) {
        pages.contact().setMessage("A".repeat(length));
    }

    @When("I attach a temporary {string} file of {int} bytes")
    public void iAttachATemporaryFile(String extension, int bytes) throws IOException {
        Path file = Files.createTempFile("qa-contact-", "." + extension);
        temporaryFiles.add(file);

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file.toFile(), "rw")) {
            randomAccessFile.setLength(bytes);
        }

        pages.contact().uploadFile(file.toAbsolutePath().toString());
    }

    @When("I submit the contact form")
    public void iSubmitTheContactForm() {
        pages.contact().submit();
    }

    @Then("the contact result should be {string}")
    public void theContactResultShouldBe(String expected) {
        Assert.assertEquals(
                pages.contact().getResultMessage(),
                expected);
    }

    @Then("the selected contact subject should be {string}")
    public void theSelectedContactSubjectShouldBe(String expected) {
        Assert.assertEquals(
                pages.contact().getSelectedSubject(),
                expected);
    }

    @Then("the contact message field should contain {int} characters")
    public void theContactMessageFieldShouldContainCharacters(int expected) {
        Assert.assertEquals(
                pages.contact().getMessageValue().length(),
                expected,
                "Message textarea did not enforce its maximum length.");
    }
}
