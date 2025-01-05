package es.ulpgc.moneycalc.architecture.persistence;

import es.ulpgc.moneycalc.architecture.model.Currency;

public interface CurrencyLoader {
    boolean hasNext();

    Currency load();
}
