package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PdfEditorTest extends BaseTest {

    private final By openEditorButton = By.xpath("//button[contains(normalize-space(),'Open Editor')]");
    private final By chooseFileButton = By.xpath("//button[contains(normalize-space(),'Choose File')]");
    private final By fileInput = By.cssSelector("input[type='file']");
    private final By downloadButton = By.xpath("//button[contains(normalize-space(),'Download')]");
    private final By textToolButton1 = By.xpath("//button[normalize-space()='Text']");
    private final By textToolButton2 = By.xpath("//button[contains(normalize-space(),'Text')]");
    private final By textToolButton3 = By.xpath("//*[@aria-label='Text']");
    private final By boldButton1 = By.xpath("//button[normalize-space()='Bold']");
    private final By boldButton2 = By.xpath("//button[contains(normalize-space(),'Bold')]");
    private final By boldButton3 = By.xpath("//*[@aria-label='Bold']");
    private final By textArea1 = By.cssSelector("textarea");
    private final By textArea2 = By.xpath("//*[@role='textbox']");
    private final By uploadPrompt = By.xpath("//*[contains(normalize-space(),'Choose File') or contains(normalize-space(),'Upload') or contains(normalize-space(),'Select PDF')]");

    private void openEditor() {
        wait.until(d -> d.findElement(openEditorButton)).click();
        wait.until(d ->
                isAnyPresent(chooseFileButton) ||
                isAnyPresent(fileInput) ||
                isAnyPresent(downloadButton) ||
                isAnyPresent(uploadPrompt)
        );
    }

    private void uploadPdf() {
        uploadFirstFile("editor_valid.pdf");
        wait.until(d -> d.findElements(By.cssSelector("canvas")).size() >= 2);
    }

    private boolean isAnyPresent(By... locators) {
        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                return true;
            }
        }
        return false;
    }

    private WebElement findFirstVisible(By... locators) {
        for (By locator : locators) {
            List<WebElement> elements = driver.findElements(locator);
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return element;
                }
            }
        }
        return null;
    }

    @Test
    public void AT_PE_01_openEditorWithValidPdf() {
        openEditor();
        uploadPdf();

        Assert.assertTrue(driver.findElements(By.cssSelector("canvas")).size() >= 2);
        savePageScreenshot("AT_PE_01_open_editor");
    }

    @Test
    public void AT_PE_02_addBoldTextAndDownloadEditedPdf() {
        openEditor();
        uploadPdf();

        WebElement textTool = findFirstVisible(textToolButton1, textToolButton2, textToolButton3);
        Assert.assertNotNull(textTool, "Text tool was not found in PDF Editor");
        textTool.click();

        clickCanvas(1, 321, 58);

        WebElement textArea = null;
        try {
            wait.until(d -> isAnyPresent(textArea1, textArea2));
            textArea = findFirstVisible(textArea1, textArea2);
        } catch (Exception ignored) {
        }

        Assert.assertNotNull(textArea, "Text input area was not found after clicking the Text tool");
        textArea.sendKeys("Playwright automation testing");

        WebElement boldTool = findFirstVisible(boldButton1, boldButton2, boldButton3);
        if (boldTool != null) {
            boldTool.click();
        }

        clickCanvas(1, 360, 95);
        pause(1500);

        long before = completedDownloadCount();

        WebElement download = findFirstVisible(downloadButton);
        Assert.assertNotNull(download, "Download button was not found after editing PDF");
        download.click();

        waitForDownloadIncrease(before);
        savePageScreenshot("AT_PE_02_editor_text_added");
    }

    @Test
    public void AT_PE_03_editorInitialStateVisible() {
        openEditor();

        Assert.assertTrue(
                isAnyPresent(chooseFileButton, fileInput, uploadPrompt),
                "PDF Editor initial upload state is not visible"
        );

        savePageScreenshot("AT_PE_03_editor_initial_state");
    }

    @Test
    public void AT_PE_04_editorEmptySubmissionValidation() {
        openEditor();

        Assert.assertTrue(
                isAnyPresent(chooseFileButton, fileInput, uploadPrompt),
                "PDF Editor upload control should be visible before uploading a file"
        );

        WebElement download = findFirstVisible(downloadButton);
        if (download != null) {
            Assert.assertFalse(download.isEnabled(), "Download button should not be enabled before uploading a PDF");
        }

        savePageScreenshot("AT_PE_04_editor_empty_validation");
    }
}