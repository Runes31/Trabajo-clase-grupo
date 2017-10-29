package views;

import javax.swing.*;
import java.awt.*;

public class JLabelWhite extends JLabel {
    JLabelWhite(String text){
        setForeground(Color.WHITE);
        setText(text);
    }

    JLabelWhite(){
        setForeground(Color.WHITE);
    }
}
