package tests;

import helpers.WithLogin;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePages;
import steps.BookApiSteps;

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
    @Tag("Smoke")
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