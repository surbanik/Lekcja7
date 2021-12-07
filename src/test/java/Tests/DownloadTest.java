package Tests;

import Page.FormPage;
import Page.MainPage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class DownloadTest extends TestBase {
    private Logger loggerDownload = LoggerFactory.getLogger(DownloadTest.class);

    int numberOfFilesBeforeDownload=0;
    @Test
    @Order(1)
    public void downloadTest() throws InterruptedException {

        driver.get("https://seleniumui.moderntester.pl/");
        loggerDownload.info("Otwarto stronę: https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        FormPage formPage = new FormPage();
        MainPage mainPage = new MainPage();

        WebElement menuBasic = driver.findElement(By.linkText(mainPage.menuBasicLinkText));
        WebElement menuBasicForm = driver.findElement(By.id(mainPage.basicFormId));
        action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();

        WebElement testFileToDownloadButton = driver.findElement(By.cssSelector(formPage.testFileToDownloadButtonCss));

        numberOfFilesBeforeDownload = Helpers.countFilesInDownloadDirectory();
        loggerDownload.info("Liczba plików w folderze {} przed pobraniem pliku wynosi {}",Helpers.DOWNLOAD_DIR,numberOfFilesBeforeDownload);
        testFileToDownloadButton.click();
        sleep(1000);
        int numberOfFilesAfterDownload = Helpers.countFilesInDownloadDirectory();
        loggerDownload.info("Liczba plików w folderze {} po pobraniu pliku wynosi {}",Helpers.DOWNLOAD_DIR,numberOfFilesAfterDownload);
        assertEquals(numberOfFilesBeforeDownload+1, numberOfFilesAfterDownload);
    }

    @Test
    @Order(2)
    public void isFileExist(){
        List filesList = Helpers.listFilesInDownloadDirectory();
        loggerDownload.info("Liczba plików w folderze wynosi {}",filesList.size());
        assertTrue(filesList.contains("test-file-to-download.xlsx"));
    }

}
