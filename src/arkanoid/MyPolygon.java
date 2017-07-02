/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Polygon;

/**
 *
 * @author Amadeusz
 */
public class MyPolygon extends Polygon {
    public static final int SIDES = 4;
    public static final int MIN_LENGTH = 25;
    public static final int MAX_LENGTH = 40;
    private int[] xCrd, yCrd;
    
    
    public MyPolygon() {
        super();
        generateCoordinates();
        setCoordinates();
    }
    
    public void generateCoordinates() {
        xCrd = new int[SIDES];
        yCrd = new int[SIDES];
        
        xCrd[0] = (int)(Math.random()*MainFrame.WIDTH);
        yCrd[0] = (int)(Math.random()*MainFrame.HEIGHT);
        
        xCrd[2] = xCrd[0] + (int)(Math.random()*(MAX_LENGTH-MIN_LENGTH)) + MIN_LENGTH;
        yCrd[2] = yCrd[0] - (int)(Math.random()*(MAX_LENGTH-MIN_LENGTH)) + MIN_LENGTH;
        
        xCrd[1] = xCrd[0] + (int)(Math.random()*(MAX_LENGTH-MIN_LENGTH)); // + MIN_LENGTH;
        yCrd[1] = yCrd[0];    //yCrd[0] - (int)(Math.random()*(MAX_LENGTH-MIN_LENGTH)) + MIN_LENGTH;
        
        xCrd[3] = xCrd[0] + (int)(Math.random()*(MAX_LENGTH-MIN_LENGTH)) + MIN_LENGTH;
        yCrd[3] = yCrd[0] + (int)(Math.random()*(MAX_LENGTH-MIN_LENGTH)) + MIN_LENGTH;

    }
    
    public void setCoordinates() {
        for(int i=0; i<SIDES; i++) {
            addPoint(xCrd[i], yCrd[i]);
        }
    }
}
