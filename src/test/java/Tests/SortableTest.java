package Tests;

import Page.DemoQAPage;
import Page.MainPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

class SortableTest extends TestBase {

    MainPage mainPage = new MainPage();


    @Test
    public void sortableTest() throws InterruptedException {
        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        WebElement menuInteractions = driver.findElement(By.linkText(mainPage.menuInteractionsLinkText));
        WebElement menuInteractionsSortable = driver.findElement(By.id(mainPage.interactionsSortableId));
        action.moveToElement(menuInteractions).moveToElement(menuInteractionsSortable).click().build().perform();

        int[] tab = {1, 2, 3, 4, 5, 6, 7};
        shuffleArray(tab);
        System.out.println(Arrays.toString(tab));
        for (int i = 0; i < tab.length; i++) {
            moveElementTo(tab[i], i + 1);
        }
        sleep(1000000);
    }

    //dragAndDrop nie działa więc użyłem takiej kombinacji
    public void moveElementTo(int whichItem, int toWhatPosition) {
        Actions action = new Actions(driver);
        action.dragAndDrop(driver.findElement(By.xpath("//li[text()='Item " + whichItem + "']")),
                driver.findElement(By.cssSelector("#sortable>li:nth-child(" + toWhatPosition + ")")))
                .build()
                .perform();
    }

    public static void shuffleArray(int[] tab) {
        for (int i = tab.length - 1; i >= 1; i--) {
            Random random = new Random();
            int j = random.nextInt(i + 1);
            swapElements(tab, i, j);
        }
    }

    private static void swapElements(int[] tab, int i, int j) {
        int temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }
}