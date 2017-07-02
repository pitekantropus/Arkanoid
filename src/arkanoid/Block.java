/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Graphics2D;

/**
 *
 * @author Amadeusz
 */
public class Block extends BaseBlock {
    private int bonus, points;
    
    public Block(int row, int col, int bonus, int state) {
        super(row, col);
        if(state==1)
            this.color = ColorPalette.BLOCK_COLORS[(int)(Math.random()*ColorPalette.BLOCK_COLORS.length)];
        else
            this.color = ColorPalette.colors[19];
        this.bonus = bonus;
        this.points = 20;
        this.state = state;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(this);
    }
    
    public int getBonus() {
        return bonus;
    }
    
    public int getPoints() {
        return points;
    }
    
    @Override
    public int changeState() {
        state--;
        this.color = ColorPalette.BLOCK_COLORS[(int)(Math.random()*ColorPalette.BLOCK_COLORS.length)];
        return state;
    }
}
