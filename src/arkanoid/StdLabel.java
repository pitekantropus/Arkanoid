/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Amadeusz
 */
public class StdLabel extends JLabel {
    private Font font;
    public StdLabel(String text, int width, String alignment) {
        super(text.toUpperCase());
        font = new Font("Roboto Regular",Font.BOLD,11);
        setFont(font);
        setForeground(Color.black);
        setPreferredSize(new Dimension(width,20));
        setBorder(new EmptyBorder(0, 3, 0, 3));
        if(alignment.equals("right"))
            setHorizontalAlignment(RIGHT);
        else
            setHorizontalAlignment(LEFT);
    }
}
