package com.qacommercelab.tests;

import com.qacommercelab.base.BaseTest;
import com.qacommercelab.pages.ContactPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

@Epic("QA Commerce Lab")
@Feature("Contact Support")
public class ContactTests extends BaseTest {

    @Test(
            groups = {"smoke", "contact"},
            description = "Valid contact request is accepted"
    )
    public void validContactRequestIsAccepted() {

        ContactPage page = new ContactPage(driver);

        page.open();
        page.fillValidForm();
        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Support request accepted."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "One-character name is rejected"
    )
    public void oneCharacterNameIsRejected() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("A");
        page.setEmail("qa@example.com");
        page.selectSubject("Technical problem");
        page.setMessage(
                "This message contains more than twenty characters."
        );

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Name too short."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Two-character name is accepted"
    )
    public void twoCharacterNameIsAccepted() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("QA");
        page.setEmail("qa@example.com");
        page.selectSubject("Technical problem");
        page.setMessage(
                "This message contains more than twenty characters."
        );

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Support request accepted."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Invalid email is rejected"
    )
    public void invalidEmailIsRejected() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("QA Tester");
        page.setEmail("invalid-email");
        page.selectSubject("Technical problem");
        page.setMessage(
                "This message contains more than twenty characters."
        );

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Invalid email."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Subject is required"
    )
    public void subjectIsRequired() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("QA Tester");
        page.setEmail("qa@example.com");
        page.setMessage(
                "This message contains more than twenty characters."
        );

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Select subject."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "All supported subjects can be selected"
    )
    public void supportedSubjectsCanBeSelected() {

        String[] subjects = {
                "Order issue",
                "Product question",
                "Technical problem",
                "Other"
        };

        ContactPage page = new ContactPage(driver);

        for (String subject : subjects) {

            page.open();
            page.selectSubject(subject);

            Assert.assertEquals(
                    page.getSelectedSubject(),
                    subject
            );
        }
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Nineteen-character message is rejected"
    )
    public void nineteenCharacterMessageIsRejected() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("QA Tester");
        page.setEmail("qa@example.com");
        page.selectSubject("Technical problem");
        page.setMessage("A".repeat(19));

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Message too short."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Twenty-character message is accepted"
    )
    public void twentyCharacterMessageIsAccepted() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("QA Tester");
        page.setEmail("qa@example.com");
        page.selectSubject("Technical problem");
        page.setMessage("A".repeat(20));

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Support request accepted."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Message textarea enforces one-thousand-character maximum"
    )
    public void messageMaximumLengthIsEnforced() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setMessage("A".repeat(1001));

        Assert.assertEquals(
                page.getMessageValue().length(),
                1000,
                "Message textarea should enforce maxlength=1000."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Unicode contact data is accepted"
    )
    public void unicodeContactDataIsAccepted() {

        ContactPage page = new ContactPage(driver);

        page.open();

        page.setName("محمد عامر");
        page.setEmail("qa@example.com");
        page.selectSubject("Technical problem");
        page.setMessage(
                "هذه رسالة اختبار تحتوي على نص عربي وتتجاوز عشرين حرفاً."
        );

        page.submit();

        Assert.assertEquals(
                page.getResultMessage(),
                "Support request accepted."
        );
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Supported PNG attachment is accepted"
    )
    public void validPngAttachmentIsAccepted()
            throws IOException {

        Path file = Files.createTempFile(
                "qa-contact-",
                ".png"
        );

        try {
            Files.writeString(
                    file,
                    "QA test attachment"
            );

            ContactPage page =
                    new ContactPage(driver);

            page.open();
            page.fillValidForm();
            page.uploadFile(file.toAbsolutePath().toString());
            page.submit();

            Assert.assertEquals(
                    page.getResultMessage(),
                    "Support request accepted."
            );

        } finally {
            Files.deleteIfExists(file);
        }
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Unsupported attachment extension is rejected"
    )
    public void unsupportedAttachmentTypeIsRejected()
            throws IOException {

        Path file = Files.createTempFile(
                "qa-contact-",
                ".txt"
        );

        try {
            Files.writeString(
                    file,
                    "Unsupported QA attachment"
            );

            ContactPage page =
                    new ContactPage(driver);

            page.open();
            page.fillValidForm();
            page.uploadFile(file.toAbsolutePath().toString());
            page.submit();

            Assert.assertEquals(
                    page.getResultMessage(),
                    "File type not allowed."
            );

        } finally {
            Files.deleteIfExists(file);
        }
    }

    @Test(
            groups = {"regression", "contact"},
            description = "Attachment larger than two megabytes is rejected"
    )
    public void attachmentAboveTwoMegabytesIsRejected()
            throws IOException {

        Path file = Files.createTempFile(
                "qa-contact-large-",
                ".pdf"
        );

        try (
                RandomAccessFile randomAccessFile =
                        new RandomAccessFile(
                                file.toFile(),
                                "rw"
                        )
        ) {
            randomAccessFile.setLength(
                    2L * 1024 * 1024 + 1
            );
        }

        try {
            ContactPage page =
                    new ContactPage(driver);

            page.open();
            page.fillValidForm();
            page.uploadFile(file.toAbsolutePath().toString());
            page.submit();

            Assert.assertEquals(
                    page.getResultMessage(),
                    "File too large."
            );

        } finally {
            Files.deleteIfExists(file);
        }
    }
}