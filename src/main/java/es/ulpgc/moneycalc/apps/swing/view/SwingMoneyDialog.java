package es.ulpgc.moneycalc.apps.swing.view;

import es.ulpgc.moneycalc.apps.swing.view.listeners.OnComponentUpdateListener;
import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.model.Money;
import es.ulpgc.moneycalc.architecture.view.MoneyDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {

    private static final Dimension MINIMUM_SIZE = new Dimension(250, 30);

    private final SwingCurrencyDialog currencyDialog;
    private final JTextField amountTextField;

    public SwingMoneyDialog(List<Currency> currencies) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(amountTextField = createAmountInput());
        add(currencyDialog = new SwingCurrencyDialog(currencies));
    }

    private JTextField createAmountInput() {
        JTextField textField = new JTextField();
        textField.setColumns(5);
        textField.setToolTipText("Input Amount");
        textField.setText("0");
        textField.setMinimumSize(MINIMUM_SIZE);
        textField.setPreferredSize(MINIMUM_SIZE);
        return textField;
    }

    @Override
    public Optional<Money> tryGet() {
        try {
            return Optional.of(new Money(amountFrom(amountTextField.getText()), currencyDialog.get()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static BigDecimal amountFrom(String text) throws NumberFormatException {
        return new BigDecimal(text);
    }

    public void add(OnComponentUpdateListener listener) {
        amountTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                listener.changePerformed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                listener.changePerformed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                listener.changePerformed();
            }
        });
        currencyDialog.add(listener);
    }
}
