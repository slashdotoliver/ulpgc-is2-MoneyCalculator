package es.ulpgc.moneycalc.apps.swing.view.displays;

import es.ulpgc.moneycalc.architecture.model.Money;
import es.ulpgc.moneycalc.architecture.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDisplay extends JTextField implements MoneyDisplay {

    private static final Dimension MINIMUM_SIZE = new Dimension(250, 30);

    public SwingMoneyDisplay() {
        setEditable(false);
        setText("0");
        setMinimumSize(MINIMUM_SIZE);
        setPreferredSize(MINIMUM_SIZE);
    }

    @Override
    public void show(Money money) {
        setText(money.amount().toString());
    }
}
