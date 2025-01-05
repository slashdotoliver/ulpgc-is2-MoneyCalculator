package es.ulpgc.moneycalc.apps.mock.view;

import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.view.CurrencyDialog;

import java.util.List;

public class MockCurrencyDialog implements CurrencyDialog {

    private final List<Currency> currencies;
    private Integer currentIndex = 0;

    public MockCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Currency get() {
        return currencies.get(nextIndex());
    }

    private int nextIndex() {
        try {
            return currentIndex;
        } finally {
            currentIndex = (currentIndex + 1) % currencies.size();
        }
    }
}
