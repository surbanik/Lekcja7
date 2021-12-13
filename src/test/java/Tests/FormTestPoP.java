package Tests;


import Pages.FormPagePOP;
import Pages.MainPagePOP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormTestPoP extends TestBase {

    @Test
    public void shouldFillUpForm() {
        String userFirstName = "Jan";
        String userLastName = "Kowalski";
        String usersEmail = "JanKowalski@firma.pl";
        String userAge = "21";
        String expectedMessage = "Form send with success";


        new MainPagePOP(driver)
                .goToForm();

        new FormPagePOP(driver)
                .setFirstName(userFirstName)
                .setLastName(userLastName)
                .setEmail(usersEmail)
                .setMaleSex()
                .setAge(userAge)
                .setRandomYOE()
                .setAutomationTesterProfession()
                .setRandomContinent()
                .setSelectSeleniumCommands("Wait Commands", "Switch Commands")
                .uploadTestFile()
                .clickOnSignInButton();

        assertEquals(new FormPagePOP(driver).getValidationMessage(), expectedMessage);

    }
}
