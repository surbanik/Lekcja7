package Tests;

import Pages.MainPagePOP;
import Pages.TablePagePOP;
import org.junit.jupiter.api.Test;

public class TableTestPOP extends TestBase {

    @Test
    public void findSwitzMountainHigherThan4000() {

        new MainPagePOP(driver)
                .goToTable();

        new TablePagePOP(driver)
                .display4000HighMountainsInSwitzerland();
    }
}
