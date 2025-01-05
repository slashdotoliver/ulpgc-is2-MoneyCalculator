package es.ulpgc.moneycalc.apps.mock.persistence;

import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.persistence.CurrencyLoader;

import java.util.Iterator;
import java.util.List;

public class MockCurrencyLoader implements CurrencyLoader {

    private static final List<Currency> currencies = List.of(
            new Currency("USD", "Dólar americano", "$"),
            new Currency("EUR", "Euro", "€"),
            new Currency("JPY", "Yen japonés", "¥"),
            new Currency("GBP", "Libra esterlina", "£"),
            new Currency("AUD", "Dólar australiano", "A$"),
            new Currency("CAD", "Dólar canadiense", "C$"),
            new Currency("CHF", "Franco suizo", "Fr."),
            new Currency("CNY", "Yuan chino", "¥"),
            new Currency("SEK", "Corona sueca", "kr"),
            new Currency("NZD", "Dólar neozelandés", "NZ$"),
            new Currency("MXN", "Peso mexicano", "$"),
            new Currency("INR", "Rupia india", "₹"),
            new Currency("BRL", "Real brasileño", "R$"),
            new Currency("RUB", "Rublo ruso", "₽"),
            new Currency("KRW", "Won surcoreano", "₩"),
            new Currency("SGD", "Dólar de Singapur", "S$"),
            new Currency("HKD", "Dólar de Hong Kong", "HK$"),
            new Currency("NOK", "Corona noruega", "kr"),
            new Currency("DKK", "Corona danesa", "kr")
    );

    private final Iterator<Currency> iterator = currencies.iterator();

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Currency load() {
        return iterator.next();
    }
}
