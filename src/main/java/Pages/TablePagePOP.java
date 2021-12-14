package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TablePagePOP extends BasePage {


    public TablePagePOP(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "tbody>tr")
    List<WebElement> table;

    public List<RowPage> createRowPageList() {
        List<RowPage> rowPageList = new ArrayList<>();
        for (WebElement row : table) {
            rowPageList.add(new RowPage(row));
        }
        return rowPageList;
    }

    public void display4000HighMountainsInSwitzerland() {
        for (RowPage rowPage : createRowPageList()) {
            if (rowPage.getState().contains("Switzerland") && rowPage.getHeigh() > 4000) {
                System.out.println(rowPage.getRank() + " " + rowPage.getPeak() + " " + rowPage.getMountainRange());
            }
        }
    }


}
