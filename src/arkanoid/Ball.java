/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Amadeusz
 */
public class Ball extends Ellipse2D.Double {
    private Color color;
    private Vector vector;
    private int speed;
    public static final int STD_SPEED = 4, MAX_SPEED = 6, MIN_SPEED = 2,
            STD_ANGLE = 90;
    public static final double STD_SIZE = 15, MAX_SIZE = 35, MIN_SIZE = 5,
            INCR_SIZE = 5;
    
    public Ball() {
        resetBall();
        this.color = Color.white;
        this.vector = new Vector(STD_ANGLE);
        
    }
    
    public void move() {
        x += vector.getH()*speed;
        y += vector.getV()*speed;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fill(this);
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public double getPosX() {
        return x + width/2;
    }
    
    public double getPosY() {
        return y + height/2;
    }
    
    public void setPos(double x, double y) {
        this.x = x - width/2;
        this.y = y - height/2;
    }
    
    public Vector getVector() {
        return vector;
    }
    
    public void growBall() {
        if(this.width < MAX_SIZE) {
            this.width+= INCR_SIZE;
            this.height+= INCR_SIZE;
        }
    }
    
    public void shrinkBall() {
        if(this.width > MIN_SIZE) {
            this.width-= INCR_SIZE;
            this.height-= INCR_SIZE;
        }
    }
    
    public void speedUp() {
        if(this.speed < MAX_SPEED)
            speed++;
    }
    
    public void slowDown() {
        if(this.speed > MIN_SPEED)
            speed--;
    }
    
    public void resetBall() {
        speed = STD_SPEED;
        height = STD_SIZE;
        width = STD_SIZE;
        this.vector = new Vector(STD_ANGLE);
        setPos(MainFrame.WIDTH/2, MainFrame.FIELD_HEIGHT - Bar.ABOVE - STD_SIZE);
    }
}
