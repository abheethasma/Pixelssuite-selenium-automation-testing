package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CropTest extends BaseTest {

    @Test
    public void AT_CR_01_cropToJpgValidImage() {
        clickButtonExact("To JPG", 0);
        uploadFirstFile("crop_valid.jpg");
        fillVisibleNumberInputs("10", "20");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_CR_01_crop_to_jpg");
    }

    @Test
    public void AT_CR_02_cropToPngValidImage() {
        clickButtonExact("To PNG", 0);
        uploadFirstFile("crop_valid.png");
        fillVisibleNumberInputs("7", "9");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_CR_02_crop_to_png");
    }

    @Test
    public void AT_CR_03_cropToWebpValidImage() {
        clickButtonExact("To WebP", 0);
        uploadFirstFile("crop_valid.webp");
        fillVisibleNumberInputs("30", "80");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_CR_03_crop_to_webp");
    }

    @Test
    public void AT_CR_04_cropEmptySubmissionValidation() {
        clickButtonExact("To JPG", 0);

        Assert.assertTrue(
                isElementPresent(By.xpath("//*[contains(normalize-space(),'Select files')]")) &&
                        !isElementPresent(buttonByExact("Download")),
                "Expected crop upload prompt and no download button before upload."
        );

        savePageScreenshot("AT_CR_04_crop_empty_validation");
    }
}