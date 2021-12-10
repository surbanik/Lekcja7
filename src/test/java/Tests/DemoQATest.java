package Tests;

import Page.DemoQAPage;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

public class DemoQATest extends TestBase {

    DemoQAPage demoQAPage = new DemoQAPage();

    @RepeatedTest(10)
    public void shouldFillDemoQaForm() {
        driver.get("https://demoqa.com/automation-practice-form");
        Actions action = new Actions(driver);
        WebElement googleAdv = driver.findElement(By.id("google_ads_iframe_/21849154601,22343295815/Ad.Plus-Anchor_0__container__"));
        WebElement adplusAdv = driver.findElement(By.id("adplus-anchor"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", googleAdv);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", adplusAdv);
        int expectedNumberOfSelectedElements = 0;

        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(10));

        WebElement subject = driver.findElement(By.id(demoQAPage.subjectInputId));
        subject.sendKeys("Ma");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Maths']")));
        WebElement maths = driver.findElement(By.xpath("//div[text()='Maths']"));
        maths.click();
        expectedNumberOfSelectedElements += 1;


        subject.sendKeys("c");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(demoQAPage.subjectResultsListCss), 1));
        List<WebElement> cList = driver.findElements(By.cssSelector(demoQAPage.subjectResultsListCss));
        System.out.println(cList.size());

        int random = ThreadLocalRandom.current().nextInt(0, cList.size());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cList.get(random));
        String randomCItemName = cList.get(random).getText();
        cList.get(random).click();
        expectedNumberOfSelectedElements += 1;

        List<WebElement> listOfSelectedElements = driver.findElements(By.className("css-1rhbuit-multiValue"));
        int numberOfSelectedElements = listOfSelectedElements.size();
        assertEquals(expectedNumberOfSelectedElements, numberOfSelectedElements);
        assertEquals(listOfSelectedElements.get(listOfSelectedElements.size() - 1).getText(), randomCItemName);


        subject.sendKeys("a");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(demoQAPage.subjectResultsListCss), 1));
        List<WebElement> aList = driver.findElements(By.cssSelector(demoQAPage.subjectResultsListCss));
        System.out.println(aList.size());

        random = ThreadLocalRandom.current().nextInt(0, aList.size());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", aList.get(random));
        String randomAItemName = aList.get(random).getText();
        aList.get(random).click();
        expectedNumberOfSelectedElements += 1;

        listOfSelectedElements = driver.findElements(By.className("css-1rhbuit-multiValue"));
        numberOfSelectedElements = listOfSelectedElements.size();
        assertEquals(expectedNumberOfSelectedElements, numberOfSelectedElements);
        assertEquals(listOfSelectedElements.get(listOfSelectedElements.size() - 1).getText(), randomAItemName);

        WebElement lastItemXButton = driver.findElement(By.cssSelector("div:nth-child("+listOfSelectedElements.size()+")>div.subjects-auto-complete__multi-value__remove"));
        String nameOfLastElementBeforeDeleting = listOfSelectedElements.get(listOfSelectedElements.size()-1).getText();
        lastItemXButton.click();

        listOfSelectedElements = driver.findElements(By.className("css-1rhbuit-multiValue"));
        String nameOfLastElementAfterDeleting = listOfSelectedElements.get(listOfSelectedElements.size()-1).getText();
        assertNotEquals(nameOfLastElementBeforeDeleting, nameOfLastElementAfterDeleting);


        WebElement clearSubjectInputButton = driver.findElement(By.className("subjects-auto-complete__clear-indicator"));
        clearSubjectInputButton.click();
        listOfSelectedElements = driver.findElements(By.className("css-1rhbuit-multiValue"));
        System.out.println(listOfSelectedElements.size());
        assertTrue(listOfSelectedElements.isEmpty());
    }
}
