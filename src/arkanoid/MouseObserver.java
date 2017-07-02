/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Amadeusz
 */
public class MouseObserver implements MouseListener, MouseMotionListener {

    private GameField gameField;
    private Bar bar;
    
    public MouseObserver(GameField gameField, Bar bar) {
        this.gameField = gameField;
        this.bar = bar;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

            if(!gameField.getIsRunning()) {
                gameField.setIsRunning(true);
                gameField.repaint();
            }

        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
  
    }

    @Override
    public void mouseExited(MouseEvent me) {
  
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {

            bar.setPos(me.getX());
            gameField.repaint();

    }
    
}
