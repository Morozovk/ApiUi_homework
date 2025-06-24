package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePages {
    private SelenideElement
            userName = $("#userName-value"),
            buttonDeleteBook = $("#delete-record-undefined"),
            buttonCloseModal = $("#closeSmallModal-ok"),
            emptyTable = $(".rt-noData");

    @Step("Открытие страницы")
    public ProfilePages openPage(){
        open("/profile");
        userName.shouldHave(visible);
        return this;
    }

    @Step("Тап по кнопке удалении книги")
    public ProfilePages clickOnButtonDeleteBook() {
        buttonDeleteBook.click();
        return this;
    }

    @Step("Подтверждаем,что удаляем книгу и обновляем страницу")
    public ProfilePages clickOnButtonCloseModal(){
        buttonCloseModal.click();
        sleep(3000);
        Selenide.refresh();
        return this;
    }

    @Step("Проверяем,что таблица пустая")
    public ProfilePages  checkEmptyTable(){
        emptyTable.shouldHave(text("No rows found"));
        return this;
    }

}
