package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RowPage {
    static private Logger logger = LoggerFactory.getLogger(RowPage.class);

    public RowPage(WebElement row) {
        PageFactory.initElements(new DefaultElementLocatorFactory(row), this);
    }


    @FindBy(css = "td:nth-child(2)")
    WebElement peak;

    @FindBy(css = "td:nth-child(3)")
    WebElement mountainRange;

    @FindBy(css = "td:nth-child(4)")
    WebElement state;

    @FindBy(css = "td:nth-child(5)")
    WebElement height;

    @FindBy(css = "th")
    WebElement rank;


    public String getRank() {
        return rank.getText();
    }

    public String getPeak() {
        return peak.getText();
    }

    public String getMountainRange() {
        return mountainRange.getText();
    }

    public String getState() {
        return state.getText();
    }

    public int getHeigh() {
        return Integer.parseInt(height.getText());
    }
}
