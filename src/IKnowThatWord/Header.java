package IKnowThatWord;

import javax.swing.*;
import java.awt.*;
//2022985 - Robert Fernando Gil Trujillo
//2025176 - Esperanza Olivo Calderón
public class Header extends  JLabel {

    public Header(String title, Color colorBackground){
        this.setText(title);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Agency FB", Font.BOLD, 20));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setOpaque(true);
        this.setBackground(colorBackground);
    }
}
