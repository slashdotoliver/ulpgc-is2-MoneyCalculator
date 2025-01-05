package es.ulpgc.moneycalc.apps.swing;

import es.ulpgc.moneycalc.apps.mock.MockMain;
import es.ulpgc.moneycalc.apps.mock.persistence.MockCurrencyLoader;
import es.ulpgc.moneycalc.apps.mock.persistence.MockExchangeRateLoader;
import es.ulpgc.moneycalc.apps.swing.view.SwingMainFrame;
import es.ulpgc.moneycalc.architecture.control.Command;
import es.ulpgc.moneycalc.architecture.control.CommandName;
import es.ulpgc.moneycalc.architecture.control.ExchangeCommand;
import es.ulpgc.moneycalc.architecture.model.Currency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingMain {
    public static void main(String[] args) {
        List<Currency> currencies = MockMain.currenciesFrom(new MockCurrencyLoader());

        Map<CommandName, Command> commands = new HashMap<>();
        SwingMainFrame mainFrame = new SwingMainFrame(commands, currencies);

        commands.put(CommandName.Exchange, new ExchangeCommand(
                mainFrame.getMoneyDialog(),
                mainFrame.getCurrencyDialog(),
                mainFrame.getMoneyDisplay(),
                mainFrame.getExchangeRateDisplay(),
                new MockExchangeRateLoader()
        ));

        mainFrame.setVisible(true);
    }
}
