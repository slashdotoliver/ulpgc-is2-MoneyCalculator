package es.ulpgc.moneycalc.architecture.model;

public record Currency(String code, String name, String symbol) {
    @Override
    public String toString() {
        return code + " (" + symbol + ") - " + name;
    }
}
