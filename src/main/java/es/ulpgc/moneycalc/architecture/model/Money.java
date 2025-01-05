package es.ulpgc.moneycalc.architecture.model;

import java.math.BigDecimal;

public record Money(BigDecimal amount, Currency currency) { }
