package es.ulpgc.moneycalc.apps.swing.view;

import es.ulpgc.moneycalc.apps.swing.view.listeners.OnComponentUpdateListener;
import es.ulpgc.moneycalc.architecture.control.Command;
import es.ulpgc.moneycalc.architecture.control.CommandName;
import es.ulpgc.moneycalc.architecture.model.Currency;
import es.ulpgc.moneycalc.architecture.view.CurrencyDialog;
import es.ulpgc.moneycalc.architecture.view.ExchangeRateDisplay;
import es.ulpgc.moneycalc.architecture.view.MoneyDialog;
import es.ulpgc.moneycalc.architecture.view.MoneyDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SwingMainFrame extends JFrame {

    private static final Dimension SIZE = new Dimension(700, 200);
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final SwingMoneyDisplay moneyDisplay;
    private final SwingExchangeRateDisplay exchangeRateDisplay;

    public SwingMainFrame(Map<CommandName, Command> commands, List<Currency> currencies) throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Money Calculator");
        setResizable(false);
        setMinimumSize(SIZE);
        setSize(SIZE);
        setLocationRelativeTo(null);

        moneyDialog = new SwingMoneyDialog(currencies);
        currencyDialog = new SwingCurrencyDialog(currencies);
        moneyDisplay = new SwingMoneyDisplay();
        exchangeRateDisplay = new SwingExchangeRateDisplay();

        setLayout(new BorderLayout());
        add(createLeftPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createRightPanel(), BorderLayout.EAST);

        addOnUpdateListener(() -> commands.get(CommandName.Exchange).execute());
    }

    private void addOnUpdateListener(OnComponentUpdateListener listener) {
        moneyDialog.addOnUpdateListener(listener);
        currencyDialog.addOnUpdateListener(listener);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(moneyDialog);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(exchangeRateDisplay);
        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(moneyDisplay);
        panel.add(currencyDialog);
        return panel;
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }

    public ExchangeRateDisplay getExchangeRateDisplay() {
        return exchangeRateDisplay;
    }
}
