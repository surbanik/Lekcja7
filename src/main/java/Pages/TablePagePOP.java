package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TablePagePOP extends BasePage {

//
//    Zadania:
//    1. W selenium basic
//    dodać nową wersję testu formularza i oprzeć go na POP (pamiętajcie ze page object tworzymy w src>main>java>pages)
//    to samo zrobić dla testu tabeli
//    dla chętnych: dowiedzieć się jak działa DefaultElementLocatorFactory i jak jej użycie zmienia działanie mechanizmu PageFactory,
//    a następnie projektując PageObject dla testu tabeli zrobić TablePage zawierający List<RowPage>, gdzie RowPage to będzie page object wiersza w tabeli :)
//
//    public RowPage(WebElement row) {​​​​
//        PageFactory.initElements(new DefaultElementLocatorFactory(row), this);
//    }.


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
