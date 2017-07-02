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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Amadeusz
 */
public class TopPanelCreate extends JudgePanel {
    private int bricks;
    private MenuButton buttonSave;
    private JLabel levelLabel, bricksLabel, bricksCount;
    private JPanel panelMiddle;
    
    public TopPanelCreate(int width, int height) {
        super(width, height);
    }
    
    @Override
    public void initParameters() {
        bricks = 0;
    }
    
    @Override
    public void initPanels() {
        panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT,0,8));
        panelLeft.setPreferredSize(new Dimension(400, height));
        panelLeft.setBackground(backColor);
        panelMiddle = new JPanel(new FlowLayout(FlowLayout.CENTER,0,8));
        panelMiddle.setPreferredSize(new Dimension(100, height));
        panelMiddle.setBackground(backColor);
        panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,5));
        panelRight.setPreferredSize(new Dimension(400, height));
        panelRight.setBackground(backColor);
        
        add(panelLeft);
        add(panelMiddle);
        add(panelRight);
    }
    
    @Override
    public void initLabels() {
        levelLabel = new StdLabel("level name", 120, "right");
        levelName = new JTextField(10);
        levelName.setBorder(null);
        levelName.setBackground(new Color(224, 224, 224));
        levelName.setBorder(new EmptyBorder(3,3,3,3));
        levelName.setFont(new Font("Roboto Regular",Font.BOLD,12));
        bricksLabel = new StdLabel("active bricks", 100, "right");
        bricksCount = new StdLabel(""+bricks, 25, "left");
        
        panelLeft.add(levelLabel);
        panelLeft.add(levelName);
        panelLeft.add(bricksLabel);
        panelLeft.add(bricksCount);
    }
    
    @Override
    public void initButtons() {
        buttonSave = new MenuButton("SAVE", "buttonSave.png", 11);
        buttonHome = new MenuButton("HOME", "homeViolet.png", new Dimension(25, 25));
        
        panelMiddle.add(buttonSave);
        panelRight.add(buttonHome);
    }
    
    @Override
    public void increasePoints(int dPoints) { }
    
    @Override
    public int changeLifes(int dLifes) {
        return dLifes;
    }
    
    @Override
    public void showBonus(String bonus) { }
    
    @Override
    public void addBricks(int dBricks) {
        bricks += dBricks;
        bricksCount.setText(""+bricks);
    }
    
    @Override
    public int getBricks() {
        return bricks;
    }
}
