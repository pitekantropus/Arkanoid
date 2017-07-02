/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Amadeusz
 */
public abstract class BaseBlock extends Rectangle2D.Double {
    public static final int STD_WIDTH = 40, STD_HEIGHT = 25, PADDING = 4;
    protected int state;
    protected Color color;
    
    public BaseBlock(int row, int col) {
        this.width = STD_WIDTH-PADDING;
        this.height = STD_HEIGHT-PADDING;
        this.x = GameField.PADDING_H + (col-1)*(STD_WIDTH);
        this.y = GameField.PADDING_V + (row-1)*(STD_HEIGHT);
    }
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(this);
    }
    
    public int getPosX() {
        return (int)(x + STD_WIDTH/2);
    }
    
    public int getPosY() {
        return (int)(y + STD_HEIGHT/2);
    }
    
    public int getState() {
        return state;
    }
    
    public void moveDown() {
        this.y += STD_HEIGHT;
    }
    
    public abstract int changeState();
}
