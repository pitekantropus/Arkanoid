/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Amadeusz
 */
public class EndScreen extends JPanel {
    private BufferedImage img;
    private String fileName, currentLevel;
    private boolean isWin;
    
    public EndScreen(boolean isWin, String currentLevel) {
        this.isWin = isWin;
        this.currentLevel = currentLevel;
        Dimension screenDim = new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT);
        mouseAdapter();
        setPreferredSize(screenDim);
        if(isWin) {
            fileName = "WIN.png";
        } else {
            fileName = "FAILED.png";
        }
        try {
            img = ImageIO.read(getClass().getResource("/files/images/"+fileName));
            img = Judge.getJudge().scaleImg(img, screenDim);
        } catch(IOException ex) {
            System.err.print(ex);
        }
    }
    
    public void mouseAdapter() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(isWin) {
                    MainFrame.getMainFrame().chooseLevel();
                } else {
                    MainFrame.getMainFrame().newGame(currentLevel);
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(img, 0, 0, null);
    }
}
