package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePages {
    private SelenideElement
            userName = $("#userName-value"),
            buttonDeleteBook = $("#delete-record-undefined"),
            buttonCloseModal = $("#closeSmallModal-ok"),
            emptyTable = $(".rt-noData");

    public ProfilePages openPage(){
        open("/profile");
        userName.shouldHave(visible);
        return this;
    }

    public ProfilePages clickOnButtonDeleteBook() {
        buttonDeleteBook.click();
        return this;
    }

    public ProfilePages clickOnButtonCloseModal(){
        buttonCloseModal.click();
        sleep(3000);
        Selenide.refresh();
        return this;
    }

    public ProfilePages  checkEmptyTable(){
        emptyTable.shouldHave(text("No rows found"));
        return this;
    }

}
