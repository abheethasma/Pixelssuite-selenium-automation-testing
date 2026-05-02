package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DocumentConverterTest extends BaseTest {

    @Test
    public void AT_DC_01_imageToPdfValidConversion() {
        clickButtonExact("Image → PDF");
        uploadFirstFile("img_valid1.jpg");

        long before = completedDownloadCount();
        clickButtonExact("Create PDF");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_DC_01_image_to_pdf");
    }

    @Test
    public void AT_DC_02_pdfToWordValidConversion() {
        clickButtonExact("PDF → Word");
        uploadFirstFile("pdf_valid_simple.pdf");

        long before = completedDownloadCount();
        clickButtonExact("Convert to Word");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_DC_02_pdf_to_word");
    }

    @Test
    public void AT_DC_03_wordToPdfValidConversion() {
        clickButtonExact("Word → PDF");
        uploadFirstFile("word_valid.docx");

        long before = completedDownloadCount();
        clickButtonExact("Convert to PDF");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_DC_03_word_to_pdf");
    }

    @Test
    public void AT_DC_04_emptySubmissionValidation() {
        clickButtonExact("Image → PDF");

        Assert.assertTrue(
                isButtonDisabled("Create PDF") ||
                        isElementPresent(By.xpath("//*[contains(normalize-space(),'Select Images')]")),
                "Expected disabled action or visible upload prompt."
        );

        savePageScreenshot("AT_DC_04_empty_validation");
    }
}