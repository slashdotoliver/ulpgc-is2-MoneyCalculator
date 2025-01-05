package es.ulpgc.moneycalc.apps.mock;

import es.ulpgc.moneycalc.apps.mock.persistence.MockCurrencyLoader;
import es.ulpgc.moneycalc.apps.mock.persistence.MockExchangeRateLoader;
import es.ulpgc.moneycalc.apps.mock.view.MockCurrencyDialog;
import es.ulpgc.moneycalc.apps.mock.view.MockExchangeRateDisplay;
import es.ulpgc.moneycalc.apps.mock.view.MockMoneyDialog;
import es.ulpgc.moneycalc.apps.mock.view.MockMoneyDisplay;
import es.ulpgc.moneycalc.architecture.control.Command;
import es.ulpgc.moneycalc.architecture.control.ExchangeCommand;
import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.persistence.CurrencyLoader;
import es.ulpgc.moneycalc.architecture.view.CurrencyDialog;
import es.ulpgc.moneycalc.architecture.view.ExchangeRateDisplay;
import es.ulpgc.moneycalc.architecture.view.MoneyDialog;
import es.ulpgc.moneycalc.architecture.view.MoneyDisplay;

import java.util.ArrayList;
import java.util.List;

public class MockMain {
    public static void main(String[] args) {
        List<Currency> currencies = currenciesFrom(new MockCurrencyLoader());

        MoneyDialog moneyDialog = new MockMoneyDialog(currencies);
        CurrencyDialog currencyDialog = new MockCurrencyDialog(currencies);

        ExchangeRateDisplay exchangeRateDisplay = new MockExchangeRateDisplay();
        MoneyDisplay moneyDisplay = new MockMoneyDisplay();

        Command exchangeCommand = new ExchangeCommand(
                moneyDialog,
                currencyDialog,
                moneyDisplay,
                exchangeRateDisplay,
                new MockExchangeRateLoader()
        );

        exchangeCommand.execute();
    }

    public static List<Currency> currenciesFrom(CurrencyLoader loader) {
        ArrayList<Currency> currencies = new ArrayList<>();
        while (loader.hasNext())
            currencies.add(loader.load());
        return currencies;
    }
}
