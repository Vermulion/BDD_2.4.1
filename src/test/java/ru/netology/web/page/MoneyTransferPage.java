package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.String.valueOf;

public class MoneyTransferPage {
    protected SelenideElement heading = $x(".//h2[@data-test-id='dashboard']");
    protected SelenideElement amount = $x(".//span[@data-test-id='amount']//input");
    protected SelenideElement fromCard = $x(".//span[@data-test-id='from']//input");
    protected SelenideElement toCard = $x(".//span[@data-test-id='to']//input");
    protected SelenideElement cancelButton = $x(".//button[@data-test-id='action-cancel']");
    protected SelenideElement transferButton = $x(".//button[@data-test-id='action-transfer']");
    protected SelenideElement error = $x(".//div[@data-test-id='error-notification']");

    public MoneyTransferPage() {
        heading.shouldBe(visible);
        amount.shouldBe(visible);
        fromCard.shouldBe(visible);
        toCard.shouldBe(visible);
        cancelButton.shouldBe(visible);
        transferButton.shouldBe(visible);
    }

    public void error() {
        error.shouldBe(visible);
    }

    public void transfer(int sum, String cardNumber) {
        amount.val(valueOf(sum));
        fromCard.val(valueOf(cardNumber));
        transferButton.click();
    }
}
