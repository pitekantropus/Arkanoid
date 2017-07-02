/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Amadeusz
 */
public class BlockEmpty extends BaseBlock {
    private int row, col;
    
    public BlockEmpty(int row, int col) {
        super(row, col);
        this.row = row;
        this.col = col;
        this.color = new Color(0, 0, 0, 0);
        this.state = 0;
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(this);
        if(state == 0) {
            g2d.setColor(Color.white);
            g2d.draw(this);
        }
    }
    
    @Override
    public int changeState() {
        state = (++state)%3;
        switch(state) {
            case 0:
                this.color = new Color(0, 0, 0, 0);
                break;
            case 1:
                this.color = new Color(29, 233, 182);
                break;
            case 2:
                this.color = ColorPalette.colors[19];
                break;
            default:
                break;
        }
        return state;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
}
