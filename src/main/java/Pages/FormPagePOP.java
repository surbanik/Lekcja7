package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FormPagePOP extends BasePage {
    static private Logger logger = LoggerFactory.getLogger(FormPagePOP.class);

    public FormPagePOP(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputFirstName3")
    WebElement formFirstNameInput;

    @FindBy(id = "inputLastName3")
    WebElement formLastNameInput;

    @FindBy(id = "inputEmail3")
    WebElement formEmailIInput;

    @FindBy(id = "gridRadiosMale")
    WebElement formSexRadiosMale;

    @FindBy(id = "inputAge3")
    WebElement formAgeInput;

    @FindBy(css = "input[name='gridRadiosExperience']")
    List<WebElement> YOERadiosList;

    @FindBy(id = "gridCheckAutomationTester")
    WebElement formProfessionAutomationTester;

    @FindBy(id = "selectContinents")
    WebElement formContinentsSelect;

    @FindBy(id = "selectSeleniumCommands")
    WebElement formSeleniumCommandsSelect;

    @FindBy(id = "chooseFile")
    WebElement formFile;

    @FindBy(xpath = "//button[text()='Sign in']")
    WebElement signInButton;

    @FindBy(css = "[role=\"button\"]")
    WebElement testFileToDownloadButtonCss;

    @FindBy(id = "validator-message")
    WebElement validatorMessage;


    public FormPagePOP setFirstName(String firstName) {
        formFirstNameInput.sendKeys(firstName);
        logger.info("W pole: 'First name' wpisano wartość: {}", firstName);
        return this;
    }

    public FormPagePOP setLastName(String lastName) {
        formLastNameInput.sendKeys(lastName);
        logger.info("W pole: 'Last name' wpisano wartość: {}", lastName);
        return this;
    }

    public FormPagePOP setEmail(String email) {
        formEmailIInput.sendKeys(email);
        logger.info("W pole: 'Email' wpisano wartość: {}", email);
        return this;
    }

    public FormPagePOP setMaleSex() {
        formSexRadiosMale.click();
        logger.info("Ustawiono płeć jako: 'mężczyzna'");
        return this;
    }

    public FormPagePOP setAge(String age) {
        formAgeInput.sendKeys(age);
        logger.info("W pole: 'Age' wpisano wartość: {}", age);
        return this;
    }

    public FormPagePOP setRandomYOE() {
        int randomRadiosListPosition = ThreadLocalRandom.current().nextInt(0, YOERadiosList.size());
        YOERadiosList.get(randomRadiosListPosition).click();
        logger.info("W pole: 'Years of experience' wpisano wartość: {}", randomRadiosListPosition);
        return this;

    }

    public FormPagePOP setAutomationTesterProfession() {
        formProfessionAutomationTester.click();
        logger.info("Ustawiono zawód jako: 'Automation tester'");
        return this;
    }

    public FormPagePOP setRandomContinent() {
        int randomContinentsListPosition = ThreadLocalRandom.current().nextInt(1, YOERadiosList.size() + 1);
        Select continentsSelect = new Select(formContinentsSelect);
        continentsSelect.selectByIndex(randomContinentsListPosition);
        logger.info("W pole: 'Continents' wpisano wartość: {}", continentsSelect.getOptions().get(randomContinentsListPosition).getText());
        return this;
    }

    public FormPagePOP setSelectSeleniumCommands(String... args) {
        Select seleniumCommandsSelect = new Select(formSeleniumCommandsSelect);
        for (String arg : args) {
            seleniumCommandsSelect.selectByVisibleText(arg);
            logger.info("Z listy: 'Selenium Commands' wybrano wartość: {}", arg);
        }
        return this;
    }

    public FormPagePOP uploadTestFile() {
        formFile.sendKeys(System.getProperty("user.dir") + "\\src\\main\\resources\\uploadFile.txt");
        logger.info("Wybrano ścieżkę wysyłanego pliku: {}", System.getProperty("user.dir") + "\\src\\main\\resources\\uploadFile.txt");
        return this;
    }

    public void clickOnSignInButton() {
        signInButton.click();
        logger.info("Użytkownik kliknął w button 'Sign in'");
    }

    public String getValidationMessage() {
        logger.info("na ekranie wyświetlono {}", validatorMessage.getText());
        return validatorMessage.getText();
    }


}
