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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Amadeusz
 */
public class LevelsScreen extends JPanel {
    private ArrayList<String> levelsList;
    private int width, height;
    private int butWidth, butHeight, butPadding;
    private MenuButton buttonHome;
    private JPanel buttonsPanel, topPanel;
    private JLabel labelTitle;
    
    public LevelsScreen(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setLayout(null);
        setBackground(Color.white);
        
        init();
    }
    
    public void init() {
        readLevels();
        initParameters();
        initPanels();
        initButtons();
        initTitle();
    }
    
    public void initParameters() {
        butPadding = 10;
        butWidth = width*2/9 - butPadding;
        butHeight = 40;
    }
    
    public void initPanels() {
        topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 5));
        topPanel.setSize(width, 35);
        topPanel.setLocation(0, 0);
        topPanel.setBackground(Color.white);
        
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        buttonsPanel.setSize(width*2/3, height*2/3);
        buttonsPanel.setLocation(width/6, height/4);
        buttonsPanel.setBackground(Color.white);
        
        add(topPanel);
        add(buttonsPanel);
    }
    
    public void readLevels() {
        levelsList = new ArrayList();
        File location = new File("src/files/levels/");
        for(File f : location.listFiles()) {
            if(f.isFile()) {
                levelsList.add(f.getName());
            }
        }
    }
    
    public void initTitle() {
        labelTitle = new JLabel("LEVELS", JLabel.CENTER);
        Font font = new Font("Roboto Thin", Font.PLAIN, 71);
        labelTitle.setFont(font);
        
        labelTitle.setLocation(0, 50);
        labelTitle.setSize(width, 71);
        labelTitle.setForeground(Color.black);
        add(labelTitle);
    }
    
    public void initButtons() {
        buttonHome = new MenuButton("HOME", "homeViolet.png", new Dimension(25, 25));
        topPanel.add(buttonHome);
        
        int i=0, x, y;
        for(String temp : levelsList) {
            x = butPadding/2 + (i%3)*(butWidth + butPadding);
            y = (i/3)*(butHeight + butPadding);
            addLevelButton(temp, x, y);
            i++;
        }
    }
    
    public void addLevelButton(String name, int x, int y) {
        JButton button = new JButton(name.split("\\.")[0]);
        button.setBackground(ColorPalette.colorsBlocks[(int)(Math.random()*7)]);
        button.setOpaque(true);
        button.setSize(new Dimension(butWidth,butHeight));
        button.setLocation(x, y);
        button.setBorderPainted(false);
        button.setFont(new Font("Roboto Regular", Font.PLAIN, 14));
        button.setForeground(Color.white);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainFrame.getMainFrame().newGame(name);
            }
        
        });
        
        buttonsPanel.add(button);
    }
}
