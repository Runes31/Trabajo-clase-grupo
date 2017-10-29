package views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class EstilosBotones {
    static void setCursor(JLabel label){
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    static void setCursor(JButton button){
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    static void setCursor(JList lista){
        lista.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    static void setHoverStyle(JLabel label){
        Border borde = BorderFactory.createLineBorder(Color.WHITE, 3);
        label.setBorder(borde);
    }

    static void removeHoverStyle(JLabel elemento) {
        Border borde = BorderFactory.createLineBorder(Color.BLACK, 3);
        Insets insets = borde.getBorderInsets(elemento);
        EmptyBorder emptyBorder = new EmptyBorder(insets);
        elemento.setBorder(emptyBorder);
    }

    static void setColor(JButton button, Color color){
        button.setBackground(color);
        button.setForeground(Color.WHITE);
    }
}
