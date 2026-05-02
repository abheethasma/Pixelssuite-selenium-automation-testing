package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    protected final String baseUrl = "https://www.pixelssuite.com/";
    protected final Path projectRoot = Paths.get(System.getProperty("user.dir"));
    protected final Path downloadsDir = projectRoot.resolve("downloads");
    protected final Path screenshotsDir = projectRoot.resolve("screenshots");
    protected final Path testDataDir = projectRoot.resolve("test-data");

    @BeforeMethod
    public void setUp() throws IOException {
        Files.createDirectories(downloadsDir);
        Files.createDirectories(screenshotsDir);
        cleanDirectory(downloadsDir);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadsDir.toAbsolutePath().toString());
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        prefs.put("plugins.always_open_pdf_externally", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);

        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected By buttonByExact(String text) {
        return By.xpath("//button[normalize-space()='" + text + "']");
    }

    protected By buttonByContains(String text) {
        return By.xpath("//button[contains(normalize-space(),'" + text + "')]");
    }

    protected void clickButtonExact(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(buttonByExact(text))).click();
    }

    protected void clickButtonExact(String text, int index) {
        List<WebElement> visible = driver.findElements(buttonByExact(text))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());
        visible.get(index).click();
    }

    protected void clickTextExact(String text) {
        By locator = By.xpath("//*[normalize-space()='" + text + "']");
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void clickTextContains(String text) {
        By locator = By.xpath("//*[contains(normalize-space(),'" + text + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void uploadFirstFile(String fileName) {
        File file = testDataDir.resolve(fileName).toFile();
        WebElement input = wait.until(d -> {
            List<WebElement> inputs = d.findElements(By.cssSelector("input[type='file']"));
            return inputs.isEmpty() ? null : inputs.get(0);
        });
        input.sendKeys(file.getAbsolutePath());
    }

    protected void uploadFirstFiles(String... fileNames) {
        String joined = Arrays.stream(fileNames)
                .map(name -> testDataDir.resolve(name).toFile().getAbsolutePath())
                .collect(Collectors.joining("\n"));
        WebElement input = wait.until(d -> {
            List<WebElement> inputs = d.findElements(By.cssSelector("input[type='file']"));
            return inputs.isEmpty() ? null : inputs.get(0);
        });
        input.sendKeys(joined);
    }

    protected void fillVisibleNumberInputs(String... values) {
        List<WebElement> inputs = driver.findElements(By.cssSelector("input[type='number']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());

        for (int i = 0; i < Math.min(inputs.size(), values.length); i++) {
            inputs.get(i).clear();
            inputs.get(i).sendKeys(values[i]);
        }
    }

    protected void fillByPlaceholder(String placeholder, String value) {
        By locator = By.xpath("//*[@placeholder='" + placeholder + "']");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    protected void setRangeValue(int index, String value) {
        List<WebElement> sliders = driver.findElements(By.cssSelector("input[type='range']"));
        WebElement slider = sliders.get(index);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].value=arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                        "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                slider, value
        );
    }

    protected void clickCanvas(int canvasIndex, int x, int y) {
        List<WebElement> canvases = driver.findElements(By.cssSelector("canvas"));
        WebElement canvas = canvases.get(canvasIndex);
        actions.moveToElement(canvas, x, y).click().perform();
    }

    protected long completedDownloadCount() {
        File[] files = downloadsDir.toFile().listFiles(file ->
                file.isFile() &&
                        !file.getName().endsWith(".crdownload") &&
                        !file.getName().endsWith(".tmp"));
        return files == null ? 0 : files.length;
    }

    protected void waitForDownloadIncrease(long beforeCount) {
        new WebDriverWait(driver, Duration.ofSeconds(120))
                .until(d -> completedDownloadCount() > beforeCount);
    }

    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected boolean isButtonDisabled(String exactText) {
        List<WebElement> buttons = driver.findElements(buttonByExact(exactText));
        return !buttons.isEmpty() && !buttons.get(0).isEnabled();
    }

    protected void savePageScreenshot(String fileName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = screenshotsDir.resolve(fileName + ".png").toFile();
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void saveElementScreenshot(By locator, String fileName) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        File src = element.getScreenshotAs(OutputType.FILE);
        File dest = screenshotsDir.resolve(fileName + ".png").toFile();
        try {
            FileHandler.copy(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void cleanDirectory(Path dir) throws IOException {
        if (!Files.exists(dir)) return;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                Files.deleteIfExists(path);
            }
        }
    }
}