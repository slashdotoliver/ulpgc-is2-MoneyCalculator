package es.ulpgc.moneycalc.apps.mock.persistence;

import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.model.ExchangeRate;
import es.ulpgc.moneycalc.architecture.persistence.ExchangeRateLoader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class MockExchangeRateLoader implements ExchangeRateLoader {
    @Override
    public ExchangeRate load(Currency from, Currency to, LocalDate date) {
        return new ExchangeRate(
                from,
                to,
                from.equals(to) ? one() : pseudorandomRate((long) from.hashCode() * to.hashCode()),
                LocalDate.now()
        );
    }

    private static BigDecimal one() {
        return BigDecimal.valueOf(1d);
    }

    private static BigDecimal pseudorandomRate(long seed) {
        return BigDecimal.valueOf(new Random(seed).nextDouble(0.2d, 5d));
    }
}
