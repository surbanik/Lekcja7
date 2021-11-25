package Tests;
import Page.FormPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTest extends TestBase {

    @Test
    public void formTest() {

        driver.get("https://seleniumui.moderntester.pl/");
        Actions action = new Actions(driver);
        FormPage formPage = new FormPage();

        WebElement menuBasic = driver.findElement(By.linkText(formPage.menuBasicLinkText));
        WebElement menuBasicForm = driver.findElement(By.id(formPage.basicFormId));
        action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();

        WebElement firstName = driver.findElement(By.id(formPage.formFirstNameId));
        WebElement lastName = driver.findElement(By.id(formPage.formLastNameId));
        WebElement email = driver.findElement(By.id(formPage.formEmailId));
        WebElement sex = driver.findElement(By.id(formPage.formSexId));
        WebElement age = driver.findElement(By.id(formPage.formAgeId));
        WebElement yoe = driver.findElement(By.id(formPage.formRandomYOEId));
        WebElement profession = driver.findElement(By.id(formPage.formProfessionId));
        WebElement file = driver.findElement(By.id(formPage.formFileId));
        WebElement signInButton = driver.findElement(By.xpath(formPage.signInButtonXpath));
        Select continents = new Select(driver.findElement(By.id(formPage.formContinentsId)));
        Select seleniumCommands = new Select(driver.findElement(By.id(formPage.formSeleniumCommandsId)));

        firstName.sendKeys("Jan");
        lastName.sendKeys("Kowalski");
        email.sendKeys("JanKowalski@firma.pl");
        sex.click();
        age.sendKeys("21");
        yoe.click();
        profession.click();
        continents.selectByIndex(formPage.formRandomContinentsIndex);
        seleniumCommands.selectByVisibleText("Switch Commands");
        seleniumCommands.selectByVisibleText("Wait Commands");
        file.sendKeys(formPage.filePath);
        signInButton.click();
        String validatorMessage = driver.findElement(By.id(formPage.validatorMessageId)).getText();
        assertEquals(validatorMessage, "Form send with success");
    }
}
