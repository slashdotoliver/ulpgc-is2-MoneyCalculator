package es.ulpgc.moneycalc.apps.mock.view.displays;

import es.ulpgc.moneycalc.architecture.model.ExchangeRate;
import es.ulpgc.moneycalc.architecture.view.ExchangeRateDisplay;

public class MockExchangeRateDisplay implements ExchangeRateDisplay {
    @Override
    public void show(ExchangeRate rate) {
        System.out.println(rate.date().toString());
    }
}
