package Tests;

import Page.FormPage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
public class DownloadTest extends TestBase {
    private Logger logger = LoggerFactory.getLogger(DownloadTest.class);

    int numberOfFilesBeforeDownload=0;
    @Test
    @Order(1)
    public void downloadTest() throws InterruptedException {

        driver.get("https://seleniumui.moderntester.pl/");
        logger.info("Otwarto stronę: ");
        Actions action = new Actions(driver);
        FormPage formPage = new FormPage();

        WebElement menuBasic = driver.findElement(By.linkText(formPage.menuBasicLinkText));
        WebElement menuBasicForm = driver.findElement(By.id(formPage.basicFormId));
        action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();

        WebElement testFileToDownloadButton = driver.findElement(By.cssSelector(formPage.testFileToDownloadButtonCss));

        numberOfFilesBeforeDownload = Helpers.countFilesInDownloadDirectory();
        logger.info("Liczba plików w folderze {} przed pobraniem pliku wynosi {}",Helpers.DOWNLOAD_DIR,numberOfFilesBeforeDownload);
        testFileToDownloadButton.click();
        sleep(1000);
        int numberOfFilesAfterDownload = Helpers.countFilesInDownloadDirectory();
        logger.info("Liczba plików w folderze {} po pobraniu pliku wynosi {}",Helpers.DOWNLOAD_DIR,numberOfFilesAfterDownload);
        assertEquals(numberOfFilesBeforeDownload+1, numberOfFilesAfterDownload);
    }

    @Test
    @Order(2)
    public void isFileExist(){
        List filesList = Helpers.listFilesInDownloadDirectory();
        logger.info("Liczba plików w folderze wynosi {}",filesList.size());
        assertTrue(filesList.contains("test-file-to-download.xlsx"));
    }

}
