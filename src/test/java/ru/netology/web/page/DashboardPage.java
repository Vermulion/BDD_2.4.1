package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
  protected SelenideElement heading = $x(".//h2[@data-test-id='dashboard']");
  protected SelenideElement firstCardBalance = $x(".//div[@data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
  protected SelenideElement secondCardBalance = $x(".//div[@data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
  protected ElementsCollection topUpAccButton = $$x(".//button[@data-test-id=\'action-deposit\']");
  protected SelenideElement refreshButton = $x(".//button[@data-test-id='action-reload']");

  private final String topText = "баланс: ";
  private final String tailText = " р.";
  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public MoneyTransferPage firstCard() {
  topUpAccButton.first().click();
  return new MoneyTransferPage();
  };

  public MoneyTransferPage secondCard() {
    topUpAccButton.last().click();
    return new MoneyTransferPage();
  };


  public int getBalance(String id) {
    return extractBalance($("[data-test-id='" + id + "']").getText());
  }

  public int extractBalance(String text) {
    val top =  text.indexOf(topText);
    val tail = text.indexOf(tailText);
    val balanceInt = text.substring(top + topText.length(), tail);
    return Integer.parseInt(balanceInt);
  }
}
