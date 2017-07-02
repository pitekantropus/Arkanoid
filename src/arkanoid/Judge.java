/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author Amadeusz
 */
public class Judge {
    private JudgePanel judgePanel;
    private ActionField actionField;
    private ScreenContainer screenContainer;
    private static Judge judge = null;
    private String currentLevel;
    
    private Judge() {
    }
    
    public static Judge getJudge() {
        if(judge == null)
            judge = new Judge();
        return judge;
    }
    
    public void setScreenContainer(ScreenContainer screenContainer) {
        this.screenContainer = screenContainer;
        this.judgePanel = this.screenContainer.getTopBar();
        this.actionField = this.screenContainer.getMainField();
    }
    
    public void increasePoints(Block block) {
        judgePanel.increasePoints(block.getPoints());
    }
    
    public int changeLifes(int dLifes) {
        int lifesLeft;
        lifesLeft = judgePanel.changeLifes(dLifes);
        return lifesLeft;
    }
    
    public void showBonus(Bonus bonus) {
        judgePanel.showBonus(bonus.getBonusName());
    }
    
    public JudgePanel getJudgePanel() {
        return judgePanel;
    }
    
    public ActionField getActionField() {
        return actionField;
    }
    
    public void setLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    public String getLevel() {
        return currentLevel;
    }
    
    public BufferedImage scaleImg(BufferedImage bf, Dimension dim) {
        BufferedImage before = bf;
        int w = before.getWidth();
        int h = before.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(dim.getWidth()/w, dim.getHeight()/h);
        AffineTransformOp scaleOp = 
           new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        
        return after;
    }
}
