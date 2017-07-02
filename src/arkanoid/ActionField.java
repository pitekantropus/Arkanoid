/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Amadeusz
 */
public abstract class ActionField extends JPanel {
    protected int width, height;
    protected ArrayList<BaseBlock> blocks;
    protected BlocksLoader loader;
    protected ScreenContainer screenContainer;
    protected Judge judge;
    
    public ActionField(int width, int height) {
        this.width = width;
        this.height = height;
        judge = Judge.getJudge();
        setPreferredSize(new Dimension(width, height));
    }
    
    public void setScreenContainer(ScreenContainer screenContainer) {
        this.screenContainer = screenContainer;
    }
    
    public abstract void saveLevel();
    
    public abstract void blocksDown();
}
