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
        Border borde = BorderFactory.createLineBorder(new Color(40,40,40), 3);
        elemento.setBorder(borde);
    }

    static void botonPrimary(JButton button){
        button.setBackground(new Color(60,60,60));
        button.setForeground(Color.WHITE);
    }

    static void botonSuccess(JButton button){
        button.setBackground(new Color(20, 40, 20));
        button.setForeground(Color.WHITE);
    }

    static void botonDanger(JButton button){
        button.setBackground(new Color(80, 30, 30));
        button.setForeground(Color.WHITE);
    }
}
