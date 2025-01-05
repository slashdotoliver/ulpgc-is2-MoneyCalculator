package es.ulpgc.moneycalc.architecture.control;

import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.model.ExchangeRate;
import es.ulpgc.moneycalc.architecture.model.Money;
import es.ulpgc.moneycalc.architecture.persistence.ExchangeRateLoader;
import es.ulpgc.moneycalc.architecture.view.CurrencyDialog;
import es.ulpgc.moneycalc.architecture.view.ExchangeRateDisplay;
import es.ulpgc.moneycalc.architecture.view.MoneyDialog;
import es.ulpgc.moneycalc.architecture.view.MoneyDisplay;

import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ExchangeCommand implements Command {

    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final MoneyDisplay moneyDisplay;
    private final ExchangeRateDisplay exchangeRateDisplay;
    private final ExchangeRateLoader exchangeRateLoader;

    public ExchangeCommand(
            MoneyDialog moneyDialog,
            CurrencyDialog currencyDialog,
            MoneyDisplay moneyDisplay,
            ExchangeRateDisplay exchangeRateDisplay,
            ExchangeRateLoader exchangeRateLoader
    ) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
        this.exchangeRateDisplay = exchangeRateDisplay;
        this.exchangeRateLoader = exchangeRateLoader;
    }

    @Override
    public void execute() {
        if (moneyDialog.tryGet().isEmpty()) return;
        Money inputMoney = moneyDialog.tryGet().get();
        Currency targetCurrency = currencyDialog.get();

        ExchangeRate rate = exchangeRateLoader.load(inputMoney.currency(), targetCurrency, LocalDate.now());
        Money result = convertWith(rate, inputMoney, targetCurrency);

        exchangeRateDisplay.show(rate);
        moneyDisplay.show(result);
    }

    private static Money convertWith(ExchangeRate exchangeRate, Money inputMoney, Currency targetCurrency) {
        return new Money(
                inputMoney.amount()
                        .multiply(
                                exchangeRate.rate(),
                                new MathContext(inputMoney.amount().precision() * 2, RoundingMode.DOWN)
                        ),
                targetCurrency
        );
    }
}
