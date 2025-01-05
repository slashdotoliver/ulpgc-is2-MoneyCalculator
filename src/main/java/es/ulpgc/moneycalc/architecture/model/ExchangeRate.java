package es.ulpgc.moneycalc.architecture.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExchangeRate(Currency from, Currency to, BigDecimal rate, LocalDate date) { }
