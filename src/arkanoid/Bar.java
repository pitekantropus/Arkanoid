/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Amadeusz
 */
public class Bar extends RoundRectangle2D.Double {
    public static final int STD_SIZE = 100, MAX_SIZE = 250, MIN_SIZE = 25,
            INCR_SIZE = 25, HEIGHT = 16, RADIUS = 5, ABOVE = 40;
    public static final Color STD_COLOR = Color.white;
    
    private Color color;
    private double posX, posY, fieldWidth;

    public Bar(int fieldWidth, int fieldHeight) {
        this.posX = fieldWidth/2;
        this.x = posX - STD_SIZE/2;
        this.posY = fieldHeight - ABOVE;
        this.y = posY - HEIGHT/2;
        this.height = HEIGHT;
        this.width = STD_SIZE;
        this.arcwidth = RADIUS;
        this.archeight = RADIUS;
        this.color = STD_COLOR;
        this.fieldWidth = fieldWidth;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(this);
    }
    
    public void setPos(double posX) {
        if((posX <= fieldWidth - width/2) && (posX >= 0 + width/2)) {
            this.posX = posX;
            convertPos();
        }
    }
    
    public double getPos() {
        return posX;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void growBar() {
        if(this.width < MAX_SIZE) {
            this.width+= INCR_SIZE;
        }
    }
    
    public void shrinkBar() {
        if(this.width > MIN_SIZE) {
            this.width-= INCR_SIZE;
        }
    }
    
    public void convertPos() {
        this.x = posX - width/2;
    }
    
    public void resetBar() {
        this.width = STD_SIZE;
        this.color = STD_COLOR;
    }
}
