/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Amadeusz
 */
public abstract class JudgePanel extends JPanel {
    protected int width, height;
    protected Color backColor;
    protected MenuButton buttonHome;
    protected JTextField levelName;
    protected JPanel panelLeft, panelRight;
    
    public JudgePanel(int width, int height) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.width = width;
        this.height = height;
        this.backColor = Color.white;
        setPreferredSize(new Dimension(width, height));  
        
        init();
    }
    
    public void init() {
        initParameters();
        initPanels();
        initLabels();
        initButtons();
    }
    
    public abstract void initParameters();
    
    public abstract void initPanels();
    
    public abstract void initLabels();
    
    public abstract void initButtons();
    
    public abstract void increasePoints(int dPoints);
    
    public abstract int changeLifes(int dLifes);
    
    public abstract void showBonus(String bonus);
    
    public JTextField getLevelName() {
        return levelName;
    }
    
    public abstract void addBricks(int dBricks);
    
    public abstract int getBricks();
    
    public void showFps(long fps) { };
}
