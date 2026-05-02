package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizeTest extends BaseTest {

    @Test
    public void AT_RS_01_resizeValidImage() {
        clickButtonExact("Resize");
        uploadFirstFile("resize_valid1.jpg");
        fillVisibleNumberInputs("680", "1500");

        long before = completedDownloadCount();
        clickButtonExact("Download PNG");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_RS_01_resize_valid");
    }

    @Test
    public void AT_RS_02_batchResizeValidImages() {
        clickButtonExact("Batch Resize");
        uploadFirstFiles("resize_valid1.jpg", "resize_valid2.jpg");
        fillByPlaceholder("Width", "1280");
        fillByPlaceholder("Height", "720");

        long before = completedDownloadCount();
        clickButtonExact("Process & Download");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_RS_02_batch_resize");
    }

    @Test
    public void AT_RS_03_imageEnlargerValidImage() {
        clickButtonExact("Image Enlarger");
        uploadFirstFile("resize_valid2.jpg");
        setRangeValue(0, "250");

        long before = completedDownloadCount();
        clickButtonExact("Download PNG");
        waitForDownloadIncrease(before);

        savePageScreenshot("AT_RS_03_image_enlarger");
    }

    @Test
    public void AT_RS_04_resizeEmptySubmissionValidation() {
        clickButtonExact("Resize");

        Assert.assertTrue(
                isElementPresent(By.xpath("//*[contains(normalize-space(),'Select files')]")) &&
                        !isElementPresent(buttonByExact("Download PNG")),
                "Expected upload prompt and no download button before upload."
        );

        savePageScreenshot("AT_RS_04_resize_empty_validation");
    }
}