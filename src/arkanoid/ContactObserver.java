/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Amadeusz
 */
public class ContactObserver {
    private Bar bar;
    private Ball ball;
    private GameField gameField;
    private ArrayList<BaseBlock> blocks;    //loaded blocks
    private ArrayList<Bonus> bonuses;       //loaded bonuses
    private BlocksLoader loader;
    private Judge judge;
    
    public ContactObserver(Bar bar, Ball kulka, ArrayList<BaseBlock> blocks,
            GameField gameField, ArrayList<Bonus> bonuses, BlocksLoader loader) {
        this.bar = bar;
        this.ball = kulka;
        this.blocks = blocks;
        this.gameField = gameField;
        this.bonuses = bonuses;
        this.loader = loader;
        this.judge = Judge.getJudge();
    }
    
    public void checkContact() {
        Area barArea = new Area(bar);
        Area kulkaArea = new Area(ball);
        
        checkBoundsContact();
        checkBarContact(kulkaArea, barArea);
        checkBlocksContact();
        checkBonusContact();
    }
    
    public void checkBoundsContact() {      //contact with gamefield bounds
        int lifesLeft;
        if(ball.getMaxX() >= gameField.getWidth()) {
            ball.getVector().bounce('r');
        }
        if(ball.getMinX() <= 0) {
            ball.getVector().bounce('l');
        }
        if(ball.getMinY() <= 0) {
            ball.getVector().bounce('v');
        }
        if(ball.getMaxY() >= gameField.getHeight()) {
            lifesLeft = judge.changeLifes(-1);
            if(lifesLeft == 0)
                MainFrame.getMainFrame().gameEnd(false);
            gameField.ballLost();
        }
    }
    
    public void checkBarContact(Area kulkaArea, Area barArea) {
        int barPos;
        kulkaArea.intersect(barArea);
        if(!kulkaArea.isEmpty()) {
            barPos = (int) (ball.getPosX() - bar.getPos());
            ball.getVector().bounceBar(barPos);    //ball bounces on bar
        }
    }
    
    public void checkBonusContact() {
        Bonus bonus;
        for(int i=0; i<bonuses.size(); i++) {
            bonus = bonuses.get(i);
            if(bonus.intersects(bar.getBounds2D())) {
                judge.showBonus(bonus);         //shows bonus info
                gameField.bonusCaught(bonus);   //bonus action
            }
            
            if(bonus.getMaxY() >= MainFrame.HEIGHT) {
                bonuses.remove(bonus);          //bonus death
            }
        }
    }
    
    public void checkBlocksContact() {      //ball contact with blocks
        Block block;
        Rectangle2D kulkaBounds, intersection;
        
        for(int i=0; i<blocks.size(); i++) {
            block = (Block)blocks.get(i);
            kulkaBounds = ball.getBounds2D();
            if(kulkaBounds.intersects(block)) {
                judge.increasePoints(block);
                intersection = kulkaBounds.createIntersection(block);
                if(intersection.getWidth()>intersection.getHeight()) {
                    ball.getVector().bounce('v');
                }
                else {
                    ball.getVector().bounce('h');
                }
                if(block.getState()==1) {
                    if(block.getBonus() != 0)
                        bonuses.add(new Bonus(block, loader));
                    blocks.remove(block);
                }
                else
                    block.changeState();
                
                if(blocks.isEmpty()) {
                    MainFrame.getMainFrame().gameEnd(true);
                    return;
                }
                break;
            }
        }
    }
}
