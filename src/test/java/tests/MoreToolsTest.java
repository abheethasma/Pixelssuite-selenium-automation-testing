package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MoreToolsTest extends BaseTest {

    @Test
    public void AT_MT_01_rotateValidImageTo180AndDownload() {
        clickButtonExact("Rotate");
        uploadFirstFile("ocr_valid.png");
        setRangeValue(0, "180");

        long before = completedDownloadCount();
        clickButtonExact("Download Rotated");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_MT_01_rotate_180");
    }

    @Test
    public void AT_MT_02_flipValidImageAndDownloadPng() {
        clickButtonExact("Flip");
        uploadFirstFile("ocr_valid.png");
        clickTextExact("Flip Horizontal");

        long before = completedDownloadCount();
        clickButtonExact("Download PNG");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_MT_02_flip");
    }

    @Test
    public void AT_MT_03_createMemeAndDownloadOutput() {
        clickButtonExact("Meme");

        if (isElementPresent(By.xpath("//*[contains(normalize-space(),'Select an image to add meme')]"))) {
            clickTextContains("Select an image to add meme");
        }

        uploadFirstFile("meme_base.jpg");

        setRangeValue(1, "8");
        setRangeValue(2, "25");

        WebElement topText = wait.until(d -> d.findElement(
                By.xpath("//*[@placeholder='Top text' or @aria-label='Top text']")
        ));
        topText.sendKeys("Hit YOU");

        clickCanvas(0, 156, 102);

        setRangeValue(0, "39");
        setRangeValue(1, "9");
        setRangeValue(2, "80");

        long before = completedDownloadCount();
        clickButtonExact("Download Meme");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_MT_03_meme");
    }

    @Test
    public void AT_MT_04_colorPickerInteractionAndCopyActions() {
        clickButtonExact("Color Picker");
        clickCanvas(1, 6, 113);

        Assert.assertTrue(isElementPresent(buttonByExact("Copy")));
        clickButtonExact("Copy", 1);
        clickButtonExact("Copy", 0);

        savePageScreenshot("AT_MT_04_color_picker");
    }

    @Test
    public void AT_MT_05_imageToTextOcrValidImage() {
        clickButtonExact("Image → Text");
        uploadFirstFile("img_valid1.jpg");
        clickButtonExact("Start OCR");

        pause(5000);
        savePageScreenshot("AT_MT_05_ocr_valid");
    }

    @Test
    public void AT_MT_06_imageToTextEmptySubmissionValidation() {
        clickButtonExact("Image → Text");

        Assert.assertTrue(
                isElementPresent(By.xpath("//*[contains(normalize-space(),'Select image')]")) &&
                        !isElementPresent(buttonByExact("Start OCR")),
                "Expected upload prompt and no OCR action before upload."
        );

        savePageScreenshot("AT_MT_06_ocr_empty_validation");
    }
}