package es.ulpgc.moneycalc.apps.swing.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JPanelBuilder {

    public interface LayoutConfigurator {
        void configure(JPanel panel);
    }

    private final LayoutConfigurator layoutConfigurator;
    private final List<Component> components = new ArrayList<>();

    public JPanelBuilder(LayoutConfigurator layoutConfigurator) {
        this.layoutConfigurator = layoutConfigurator;
    }

    public static JPanelBuilder withBoxLayout(int axis) {
        return new JPanelBuilder(panel -> panel.setLayout(new BoxLayout(panel, axis)));
    }

    public JPanelBuilder add(Component... components) {
        this.components.addAll(Arrays.asList(components));
        return this;
    }

    public JPanel build() {
        JPanel panel = new JPanel();
        layoutConfigurator.configure(panel);
        components.forEach(panel::add);
        return panel;
    }
}
