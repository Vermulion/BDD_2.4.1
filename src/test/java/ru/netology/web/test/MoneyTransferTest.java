package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.MoneyTransferPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

  @BeforeEach
  void setUp() {
    open("http://localhost:9999");

  }

  @AfterEach
  @DisplayName("Return the balance to its original position")
  void returnAccBalanceToOriginalState() {
    DashboardPage dashboardPage = new DashboardPage();
    int balance = 10000;
    int cardBalance1 = dashboardPage.getBalance(DataHelper.validCard1().getDataTestId());
    int cardBalance2 = dashboardPage.getBalance(DataHelper.validCard2().getDataTestId());
    int remains;
    if (cardBalance1 > balance) {
      remains = cardBalance1 - balance;
      dashboardPage.secondCard();
      var transfer = new MoneyTransferPage();
      var idCard = DataHelper.validCard1();
      transfer.transfer(remains, idCard.getCardNumber());
    }
    if (cardBalance2 > balance) {
      remains = cardBalance2 - balance;
      dashboardPage.firstCard();
      var transfer = new MoneyTransferPage();
      var idCard = DataHelper.validCard2();
      transfer.transfer(remains, idCard.getCardNumber());
    }

  }


  @Test
  void shouldTransferMinimumMoneyFrom1to2Card() {
    int transferSum = 1;
    var LoginPage = new LoginPage();
    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = LoginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    verificationPage.validVerify(verificationCode);
    var dashBoardPage = new DashboardPage();
    int expBalance1 = dashBoardPage.getBalance(DataHelper.validCard1().getDataTestId()) - transferSum;
    int expBalance2 = dashBoardPage.getBalance(DataHelper.validCard2().getDataTestId()) + transferSum;
    var moneyTransferPage = dashBoardPage.secondCard();
    var idCard = DataHelper.validCard1();
    moneyTransferPage.transfer(transferSum, idCard.getCardNumber());
    assertEquals(expBalance1, dashBoardPage.getBalance(DataHelper.validCard1().getDataTestId()));
    assertEquals(expBalance2, dashBoardPage.getBalance(DataHelper.validCard2().getDataTestId()));

  }

//  @Test
//  void shouldTransferMoneyBetweenOwnCardsV3() {
//    var loginPage = open("http://localhost:9999", LoginPageV3.class);
//    var authInfo = DataHelper.getAuthInfo();
//    var verificationPage = loginPage.validLogin(authInfo);
//    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
//    verificationPage.validVerify(verificationCode);
//  }
}

