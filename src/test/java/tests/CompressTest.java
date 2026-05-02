package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CompressTest extends BaseTest {

    @Test
    public void AT_CM_01_compressImageValidJpg() {
        clickButtonExact("Compress Image");
        uploadFirstFile("compress_valid.jpg");
        setRangeValue(0, "0.52");

        long before = completedDownloadCount();
        clickButtonExact("Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_CM_01_compress_jpg");
    }

    @Test
    public void AT_CM_02_compressToGifValidGif() {
        clickButtonExact("To GIF");
        uploadFirstFile("compress_valid.gif");

        if (isElementPresent(By.xpath("//*[normalize-space()='O2']"))) {
            clickTextExact("O2");
        }
        if (isElementPresent(By.xpath("//*[contains(normalize-space(),'Colors128')]"))) {
            clickTextContains("Colors128");
        }

        setRangeValue(0, "169");
        setRangeValue(1, "90");

        clickButtonExact("Compress");

        long before = completedDownloadCount();
        clickButtonExact("Download GIF");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_CM_02_compress_gif");
    }

    @Test
    public void AT_CM_03_compressToPngValidPng() {
        clickButtonExact("To PNG", 1);
        uploadFirstFile("compress_valid.png");

        long before = completedDownloadCount();
        clickButtonExact("Download PNG");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_CM_03_compress_png");
    }

    @Test
    public void AT_CM_04_compressEmptySubmissionValidation() {
        clickButtonExact("Compress Image");

        Assert.assertTrue(
                isElementPresent(By.xpath("//*[contains(normalize-space(),'Select files')]")) &&
                        !isElementPresent(buttonByExact("Download")),
                "Expected upload prompt and no download button before upload."
        );

        savePageScreenshot("AT_CM_04_compress_empty_validation");
    }
}