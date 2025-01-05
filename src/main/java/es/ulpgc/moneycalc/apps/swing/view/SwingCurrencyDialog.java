package es.ulpgc.moneycalc.apps.swing.view;

import es.ulpgc.moneycalc.apps.swing.view.listeners.OnComponentUpdateListener;
import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.view.CurrencyDialog;

import javax.swing.*;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {

    private final JComboBox<Currency> selector;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.add(selector = createSelector(currencies));
    }

    private JComboBox<Currency> createSelector(List<Currency> currencies) {
        JComboBox<Currency> comboBox = new JComboBox<>();
        currencies.forEach(comboBox::addItem);
        comboBox.setSelectedIndex(0);
        return comboBox;
    }

    @Override
    public Currency get() {
        return selector.getItemAt(selector.getSelectedIndex());
    }

    public void addOnUpdateListener(OnComponentUpdateListener listener) {
        selector.addActionListener(_ -> listener.changePerformed());
    }
}
