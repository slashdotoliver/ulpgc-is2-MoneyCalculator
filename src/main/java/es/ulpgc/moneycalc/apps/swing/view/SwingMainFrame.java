package es.ulpgc.moneycalc.apps.swing.view;

import es.ulpgc.moneycalc.apps.swing.utils.JPanelBuilder;
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
        configureFrame();
        moneyDialog = new SwingMoneyDialog(currencies);
        currencyDialog = new SwingCurrencyDialog(currencies);
        moneyDisplay = new SwingMoneyDisplay();
        exchangeRateDisplay = new SwingExchangeRateDisplay();
        addPanels();
        add(() -> commands.get(CommandName.Exchange).execute());
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Money Calculator");
        setResizable(false);
        setMinimumSize(SIZE);
        setSize(SIZE);
        setLocationRelativeTo(null);
    }

    private void addPanels() {
        setLayout(new BorderLayout());
        add(createVerticalPanelWith(List.of(moneyDialog)), BorderLayout.WEST);
        add(createVerticalPanelWith(List.of(exchangeRateDisplay)), BorderLayout.CENTER);
        add(createVerticalPanelWith(List.of(moneyDisplay, currencyDialog)), BorderLayout.EAST);
    }

    private void add(OnComponentUpdateListener listener) {
        moneyDialog.add(listener);
        currencyDialog.add(listener);
    }

    private static JPanel createVerticalPanelWith(List<Component> components) {
        JPanelBuilder builder = JPanelBuilder.withBoxLayout(BoxLayout.Y_AXIS);
        components.forEach(builder::add);
        return builder.build();
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
