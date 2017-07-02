/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Amadeusz
 */
public class Bonus extends Rectangle2D.Double {
    public static final int WIDTH = 20, HEIGHT = 20;
    public static final double GRAVITY = 1;
    
    private double speed, time;
    private int type;
    private BufferedImage img;
    private String[] bonusData;
    
    public Bonus(Block block, BlocksLoader loader) {
        float scale;
        Dimension dim;

        this.type = block.getBonus();
        this.bonusData = loader.getBonuses().get(this.type-1);
        speed = 0;
        time = 0;
        try {
            img = ImageIO.read(getClass().getResource("/files/bonuses/"+bonusData[2]));
            scale = getScale(img.getWidth(), img.getHeight());
            this.width = (int)(scale*img.getWidth());
            this.height = (int)(scale*img.getHeight());
            dim = new Dimension((int)width, (int)height);
            img = Judge.getJudge().scaleImg(img, dim);
        } catch(IOException ex) {
            System.err.print(ex);
        }
        this.x = block.getPosX() - width/2;
        this.y = block.getPosY() - height/2;
    }
    
    public float getScale(int w, int h) {
        if(w<h)
            return (float)WIDTH/(float)w;
        else
            return (float)HEIGHT/(float)h;
    }
    
    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, (int)x, (int)y, null);
    }
    
    public void fall() {
        time+=0.01;
        speed = GRAVITY*(time*time);
        y += speed;
    }
    
    public void action(Ball kulka, Bar bar) {
        switch(this.type) {
            case 1:
                bar.growBar();
                break;
            case 2:
                bar.shrinkBar();
                break;
            case 3:
                kulka.growBall();
                break;
            case 4:
                kulka.shrinkBall();
                break;
            case 5:
                kulka.slowDown();
                break;
            case 6:
                kulka.speedUp();
                break;
            case 7:
                Judge.getJudge().getActionField().blocksDown();
                break;
            default:
                break;
        }
    }
    
    public String getBonusName() {
        return bonusData[3];
    }
}
