/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Amadeusz
 */
public class ScreenContainer extends JPanel {
    private ActionField mainField;
    private JudgePanel topBar;
    
    public ScreenContainer(JudgePanel topBar, ActionField mainField) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.topBar = topBar;
        this.mainField = mainField;
        setPreferredSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        
        init();
    }
    
    public void init() {
        mainField.setScreenContainer(this);
        add(topBar);
        add(mainField);
    }
    
    public JudgePanel getTopBar() {
        return topBar;
    }
    
    public ActionField getMainField() {
        return mainField;
    }
}
