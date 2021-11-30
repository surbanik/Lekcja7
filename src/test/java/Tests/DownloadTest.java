package Tests;

import Page.FormPage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DownloadTest extends TestBase {

    int expectedNumberOfFilesAfterDownload=0;
    @Test
    @Order(1)
    public void downloadTest() throws InterruptedException {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        FormPage formPage = new FormPage();

        WebElement menuBasic = driver.findElement(By.linkText(formPage.menuBasicLinkText));
        WebElement menuBasicForm = driver.findElement(By.id(formPage.basicFormId));
        action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();

        WebElement testFileToDownloadButton = driver.findElement(By.cssSelector(formPage.testFileToDownloadButtonCss));

        expectedNumberOfFilesAfterDownload = Helpers.countFilesInDownloadDirectory() + 1;
        testFileToDownloadButton.click();
        //wiem że sleep nie jest dobrym rozwiązaniem ale nie mogę wpaść na nic innego. Próbowałem FluentWait ale metoda .until przyjmuje argumenty typu Function i nie wiem jak sobie z tym poradzić
        //a jeśli nie damy sleep() numberOfFilesAfterDownload jest pobierany szybciej niż plik zdąży się pobrać
        sleep(1000);
        int numberOfFilesAfterDownload = Helpers.countFilesInDownloadDirectory();
        assertEquals(expectedNumberOfFilesAfterDownload, numberOfFilesAfterDownload);
    }

    @Test
    @Order(2)
    public void isFileExist(){
        List filesList = Helpers.listFilesInDownloadDirectory();
        assertTrue(filesList.contains("test-file-to-download.xlsx"));
    }

}
