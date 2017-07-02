/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Amadeusz
 */
public class MenuPanel extends JPanel {
    JComponent startButton, createButton, optionsButton, exitButton;
    JPanel buttonPanel;
    JLabel labelTitle;
    int width, height, menuW, menuH, polygons;
    
    public MenuPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        
        this.width = width;
        this.height = height;
        menuW = 380;
        menuH = 240;
        polygons = 25;
        
        buttonPanel();
        title();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        paintBackground(g2d);
    }
    
    public void paintBackground(Graphics2D g2d) {
        Color gr1 = new Color(99, 100, 237);
        Color gr2 = new Color(103, 204, 226);
        
        GradientPaint gradientBackground = new GradientPaint(0, height, gr1, width, 0, gr2);
        g2d.setPaint(gradientBackground);
        g2d.fillRect(0, 0, width, height);
    }
    
    public void title() {
        labelTitle = new JLabel("ARKA NOIDA", JLabel.CENTER);
        Font font = new Font("Roboto Thin", Font.PLAIN, 59);
        labelTitle.setFont(font);
        int yPos = (height-menuH)/2 - 59/2;
        
        labelTitle.setLocation(0, yPos);
        labelTitle.setSize(width, 59);
        labelTitle.setForeground(Color.white);
        add(labelTitle);
    }
    
    public void buttonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(1, 0, 20));
        buttonPanel.setBorder(new EmptyBorder(25,50,0,50));
        buttonPanel.setSize(new Dimension(menuW, menuH));
        buttonPanel.setBackground(Color.WHITE);
        
        startButton = new MenuButton("PLAY", "buttonPlay.png", new Dimension(148, 35), 17);
        createButton = new MenuButton("CREATE", "buttonOptions.png", new Dimension(148, 35), 17);
        exitButton = new MenuButton("EXIT", "buttonExit.png", new Dimension(148, 35), 17);
        
        buttonPanel.add(startButton);
        buttonPanel.add(createButton);
        //buttonPanel.add(optionsButton);
        buttonPanel.add(exitButton);
        buttonPanel.setLocation((width-menuW)/2, (height-menuH)/2 + 59/2);

        add(buttonPanel);
    }
    
    public void paintPolygons(Graphics2D g2d) {
        g2d.setColor(Color.white);
        for(int i=0; i<polygons; i++) {
            g2d.fillPolygon(new MyPolygon());
        }
    }
}
