package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPagePOP extends BasePage{
    static private Logger logger = LoggerFactory.getLogger(MainPagePOP.class);

    public MainPagePOP(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Basic")
    WebElement menuBasic;

    @FindBy(id = "form-item")
    WebElement menuBasicForm;

    @FindBy(id = "table-item")
    WebElement menuBasicTable;




    public void goToForm(){
    action.moveToElement(menuBasic).moveToElement(menuBasicForm).click().build().perform();
    logger.info("Użytkownik przeszedł do zakładki Basic/Form");
    }



    public void goToTable(){
    action.moveToElement(menuBasic).moveToElement(menuBasicTable).click().build().perform();
    logger.info("Użytkownik przeszedł do zakładki Basic/Table");
    }



}
