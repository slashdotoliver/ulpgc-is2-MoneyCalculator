package es.ulpgc.moneycalc.apps.swing.utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public record JPanelBuilder(List<Component> components, int axis) {
    public static JPanelBuilder withBoxLayout(int axis) {
        return new JPanelBuilder(new ArrayList<>(), axis);
    }

    public JPanelBuilder add(Component component) {
        components.add(component);
        return this;
    }

    public JPanelBuilder add(List<Component> components) {
        this.components.addAll(components);
        return this;
    }

    public JPanel build() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, axis));
        components.forEach(panel::add);
        return panel;
    }
}
