package es.ulpgc.moneycalc.apps.swing.view.displays;

import es.ulpgc.moneycalc.apps.swing.utils.JPanelBuilder;
import es.ulpgc.moneycalc.architecture.model.ExchangeRate;
import es.ulpgc.moneycalc.architecture.view.ExchangeRateDisplay;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SwingExchangeRateDisplay extends JPanel implements ExchangeRateDisplay {

    private final JLabel humanDateLabel = new JLabel();
    private final JLabel isoDateLabel = new JLabel();

    public SwingExchangeRateDisplay() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(createPanel());
    }

    private JPanel createPanel() {
        return JPanelBuilder.withBoxLayout(BoxLayout.X_AXIS)
                .add(Box.createHorizontalGlue())
                .add(JPanelBuilder.withBoxLayout(BoxLayout.Y_AXIS)
                        .add(humanDateLabel)
                        .add(isoDateLabel)
                        .build())
                .add(Box.createHorizontalGlue())
                .build();
    }

    @Override
    public void show(ExchangeRate rate) {
        humanDateLabel.setText(rate.date().format(
                DateTimeFormatter.ofPattern("eeee d 'de' MMMM", Locale.forLanguageTag("es")))
        );
        isoDateLabel.setText(rate.date().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }
}
