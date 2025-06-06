package tests.api_ui;

import com.codeborne.selenide.Selenide;
import helpers.WithLogin;

import model.AuthResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePages;
import steps.BookApiSteps;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.*;

public class UserApiUi extends TestBase {

    ProfilePages profilePages = new ProfilePages();

    @WithLogin
    @Test
    @Tag("Smoke")
    void login() {
        profilePages.openPage();
    }

    @WithLogin
    @Test
    void deleteBook() {

        String token = getWebDriver().manage().getCookieNamed("token").getValue();
        String userId = getWebDriver().manage().getCookieNamed("userID").getValue();


        BookApiSteps.deleteBooks(token, userId);
        BookApiSteps.addBook(token, userId, isbn);

        profilePages.openPage()
                    .clickOnButtonDeleteBook()
                    .clickOnButtonCloseModal()
                    .checkEmptyTable();

    }
}