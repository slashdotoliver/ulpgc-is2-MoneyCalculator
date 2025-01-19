package es.ulpgc.moneycalc.apps.swing;

import es.ulpgc.moneycalc.apps.mock.MockMain;
import es.ulpgc.moneycalc.apps.mock.persistence.MockCurrencyLoader;
import es.ulpgc.moneycalc.apps.mock.persistence.MockExchangeRateLoader;
import es.ulpgc.moneycalc.apps.swing.view.frames.SwingMainFrame;
import es.ulpgc.moneycalc.architecture.control.Command;
import es.ulpgc.moneycalc.architecture.control.CommandName;
import es.ulpgc.moneycalc.architecture.control.ExchangeCommand;

import java.util.HashMap;
import java.util.Map;

public class SwingMain {

    private static final Map<CommandName, Command> COMMANDS = new HashMap<>();

    public static void main(String[] args) {
        registerExchangeCommand(createMainFrame());
        createMainFrame().setVisible(true);
    }

    private static SwingMainFrame createMainFrame() {
        return new SwingMainFrame(
                COMMANDS,
                MockMain.currenciesFrom(new MockCurrencyLoader())
        );
    }

    private static void registerExchangeCommand(SwingMainFrame mainFrame) {
        COMMANDS.put(CommandName.Exchange, new ExchangeCommand(
                mainFrame.getMoneyDialog(),
                mainFrame.getCurrencyDialog(),
                mainFrame.getMoneyDisplay(),
                mainFrame.getExchangeRateDisplay(),
                new MockExchangeRateLoader()
        ));
    }
}
