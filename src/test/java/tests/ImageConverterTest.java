package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageConverterTest extends BaseTest {

    @Test
    public void AT_IC_01_convertToJpgValidImage() {
        clickButtonExact("To JPG", 1);
        uploadFirstFile("convert_valid.png");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_IC_01_to_jpg");
    }

    @Test
    public void AT_IC_02_convertToPngValidImage() {
        clickButtonExact("To PNG", 2);
        uploadFirstFile("convert_valid.jpg");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_IC_02_to_png");
    }

    @Test
    public void AT_IC_03_convertToWebpValidImage() {
        clickButtonExact("To WebP", 1);
        uploadFirstFile("convert_valid.png");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_IC_03_to_webp");
    }

    @Test
    public void AT_IC_04_imageConverterEmptySubmissionValidation() {
        clickButtonExact("To JPG", 1);

        Assert.assertTrue(
                isElementPresent(By.xpath("//*[contains(normalize-space(),'Select files')]")) &&
                        !isElementPresent(buttonByExact("Download")),
                "Expected upload prompt and no download button before upload."
        );

        savePageScreenshot("AT_IC_04_converter_empty_validation");
    }
}