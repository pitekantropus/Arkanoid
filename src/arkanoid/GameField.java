/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Amadeusz
 */
public class GameField extends ActionField {
    public static final int DELAY = 10;
    public static final int PADDING_H = 50;
    public static final int PADDING_V = 50;
    
    private boolean isRunning;
    private String level;
    
    private Ball kulka;
    private Bar bar;
    private ArrayList<Bonus> bonuses;
    private MouseObserver mouseObserver;
    private ContactObserver contactObserver;
    
    public GameField(int width, int height, String level) {
        super(width, height);
        this.level = level;
        setDoubleBuffered(true);
        
        init();
    }
    
    public void init() {
        initVariables();
        initObjects();
        initBlocks();
        initObservers();
    }
    
    public void initVariables() {
        isRunning = false;
    }
    
    public void initObjects() {
        bar = new Bar(width, height);
        kulka = new Ball();
        bonuses = new ArrayList();
    }
    
    public void initBlocks() {
        loader = new BlocksLoader(level);
        blocks = loader.loadBlocks(false);
    }
    
    public void initObservers() {
        mouseObserver = new MouseObserver(this, bar);
        addMouseListener(mouseObserver);
        addMouseMotionListener(mouseObserver);
        
        contactObserver = new ContactObserver(bar, kulka, blocks, this, bonuses, loader);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        if(!isRunning) {
            paintBackground(g2d);
            paintObjects(g2d);
        }
        
        if(isRunning) {
            long currentTime = System.currentTimeMillis();
            update(g2d);
            repaint();
            long delta = System.currentTimeMillis() - currentTime;
            try {
                Thread.sleep(DELAY - delta);
            }
            catch(Exception ex) {
                //System.err.println(ex);
            }
        }
    }
    
    public void update(Graphics2D g2d) {
        paintBackground(g2d);
        paintObjects(g2d);
        contactObserver.checkContact();
        kulka.move();
        for(int i=0; i<bonuses.size(); i++) {
            bonuses.get(i).fall();
        }
    }
    
    public void paintBackground(Graphics2D g2d) {
        Color gr1 = new Color(102, 136, 233);
        Color gr2 = new Color(126, 68, 171);
        
        GradientPaint gradientBackground = new GradientPaint(0, 0, gr1, width, height, gr2);
        g2d.setPaint(gradientBackground);
        g2d.fillRect(0, 0, width, height);
    }
    
    public void paintObjects(Graphics2D g2d) {
        kulka.paint(g2d);
        bar.paint(g2d);
        for(int i=0; i<blocks.size(); i++) {
            blocks.get(i).paint(g2d);
        }
        for(int i=0; i<bonuses.size(); i++) {
            bonuses.get(i).paint(g2d);
        }
    }
    
    public boolean getIsRunning() {
        return isRunning;
    }
    
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    
    public void ballLost() {
        setIsRunning(false);
        bonuses.clear();
        kulka.resetBall();
        bar.resetBar();
    }
    
    public void bonusCaught(Bonus bonus) {
        bonus.action(kulka, bar);
        bonuses.remove(bonus);
    }
    @Override
    public void saveLevel() {
        
    }
    
    @Override
    public void blocksDown() {
        for(BaseBlock block : blocks) {
            block.moveDown();
        }
    }
}
