package es.ulpgc.moneycalc.apps.mock;

import es.ulpgc.moneycalc.apps.mock.persistence.MockCurrencyLoader;
import es.ulpgc.moneycalc.apps.mock.persistence.MockExchangeRateLoader;
import es.ulpgc.moneycalc.apps.mock.view.dialogs.MockCurrencyDialog;
import es.ulpgc.moneycalc.apps.mock.view.displays.MockExchangeRateDisplay;
import es.ulpgc.moneycalc.apps.mock.view.dialogs.MockMoneyDialog;
import es.ulpgc.moneycalc.apps.mock.view.displays.MockMoneyDisplay;
import es.ulpgc.moneycalc.architecture.control.ExchangeCommand;
import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.persistence.CurrencyLoader;

import java.util.ArrayList;
import java.util.List;

public class MockMain {
    public static void main(String[] args) {
        createExchangeCommand(currenciesFrom(new MockCurrencyLoader())).execute();
    }

    private static ExchangeCommand createExchangeCommand(List<Currency> currencies) {
        return new ExchangeCommand(
                new MockMoneyDialog(currencies),
                new MockCurrencyDialog(currencies),
                new MockMoneyDisplay(),
                new MockExchangeRateDisplay(),
                new MockExchangeRateLoader()
        );
    }

    public static List<Currency> currenciesFrom(CurrencyLoader loader) {
        ArrayList<Currency> currencies = new ArrayList<>();
        while (loader.hasNext())
            currencies.add(loader.load());
        return currencies;
    }
}
