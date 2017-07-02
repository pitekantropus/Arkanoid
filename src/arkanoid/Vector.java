/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

/**
 *
 * @author Amadeusz
 */
public class Vector {
    private double hVal;
    private double vVal;
    public static final double LENGTH = 1;
    
    public Vector(int angle) {
        setAngle(angle);
    }
    
    public void bounce(char vect) {
        switch(vect) {
            case 'h':
                hVal*=(-1);
                break;
            case 'v':
                vVal*=(-1);
                break;
            case 'r':
                hVal = Math.abs(hVal)*(-1);
                break;
            case 'l':
                hVal = Math.abs(hVal);
            default:
                break;
        }
    }
    
    public void bounceBar(int angle) {
        setAngle(angle);
    }
    
    public double getH() {
        return hVal;
    }
    
    public double getV() {
        return vVal;
    }
    
    public void setAngle(int angle) {
        double angleRad = angle*Math.PI/180;
        hVal = LENGTH * Math.sin(angleRad);
        vVal = (-1)*LENGTH * Math.cos(angleRad);
    }
}
