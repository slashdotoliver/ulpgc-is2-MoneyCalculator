package es.ulpgc.moneycalc.apps.mock.view;

import es.ulpgc.moneycalc.architecture.model.Money;
import es.ulpgc.moneycalc.architecture.view.MoneyDisplay;

public class MockMoneyDisplay implements MoneyDisplay {
    @Override
    public void show(Money money) {
        System.out.println(money.toString());
    }
}
