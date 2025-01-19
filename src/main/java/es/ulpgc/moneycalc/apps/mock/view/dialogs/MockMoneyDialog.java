package es.ulpgc.moneycalc.apps.mock.view.dialogs;

import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.model.Money;
import es.ulpgc.moneycalc.architecture.view.MoneyDialog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class MockMoneyDialog implements MoneyDialog {

    private final List<Currency> currencies;

    public MockMoneyDialog(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Optional<Money> tryGet() {
        return Optional.of(new Money(BigDecimal.valueOf(100d), currencies.getFirst()));
    }
}
