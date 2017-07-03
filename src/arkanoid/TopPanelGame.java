/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Amadeusz
 */
public class TopPanelGame extends JudgePanel {
    private int points, lifes;
    private JLabel lifesLabel, lifesCount, pointsLabel, pointsCount,
            bonusLabel, bonusText;
    
    public TopPanelGame(int width, int height) {
        super(width, height);
    }
    
    @Override
    public void initParameters() {
        points = 0;
        lifes = 3;
    }
    
    @Override
    public void initPanels() {
        panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT,0,8));
        panelLeft.setPreferredSize(new Dimension(width*3/4, height));
        panelLeft.setBackground(backColor);
        panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,5));
        panelRight.setPreferredSize(new Dimension(width/4, height));
        panelRight.setBackground(backColor);
        
        add(panelLeft);
        add(panelRight);
    }
    
    @Override
    public void initLabels() {
        lifesLabel = new StdLabel("balls", 60, "right");
        lifesCount = new StdLabel(""+lifes, 30, "left");
        pointsLabel = new StdLabel("score", 100, "right");
        pointsCount = new StdLabel(""+points, 50, "left");
        bonusLabel = new StdLabel("bonus", 60, "right");
        bonusText = new StdLabel("", 200, "left");
        
        panelLeft.add(pointsLabel);
        panelLeft.add(pointsCount);
        panelLeft.add(lifesLabel);
        panelLeft.add(lifesCount);
        panelLeft.add(bonusLabel);
        panelLeft.add(bonusText);
    }
    
    @Override
    public void initButtons() {
        buttonHome = new MenuButton("HOME", "homeViolet.png", new Dimension(25, 25));
        panelRight.add(buttonHome);
    }
    
    @Override
    public void increasePoints(int dPoints) {
        points += dPoints;
        pointsCount.setText(""+points);
    }
    
    @Override
    public int changeLifes(int dLifes) {
        lifes += dLifes;
        lifesCount.setText(""+lifes);
        return lifes;
    }
    
    @Override
    public void showBonus(String bonus) {
        bonusText.setText(bonus);
    }
    
    @Override
    public void addBricks(int dBricks) { }
    
    @Override
    public int getBricks() {
        return 1;
    }
}
