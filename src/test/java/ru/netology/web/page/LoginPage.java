package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
  protected SelenideElement loginField = $x(".//span[@data-test-id='login']//input");
  protected SelenideElement passwordField = $x(".//span[@data-test-id='password']//input");
  protected SelenideElement loginButton = $x(".//button[@data-test-id='action-login']");

  public VerificationPage validLogin(DataHelper.AuthInfo info) {
    loginField.setValue(info.getLogin());
    passwordField.setValue(info.getPassword());
    loginButton.click();
    return new VerificationPage();
  }
}
