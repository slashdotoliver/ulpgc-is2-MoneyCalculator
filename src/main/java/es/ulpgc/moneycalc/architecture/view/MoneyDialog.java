package es.ulpgc.moneycalc.architecture.view;

import es.ulpgc.moneycalc.architecture.model.Money;

import java.util.Optional;

public interface MoneyDialog {
    Optional<Money> tryGet();
}
