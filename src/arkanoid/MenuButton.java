/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 *
 * @author Amadeusz
 */
public class MenuButton extends JComponent {
    private String text;
    private BufferedImage img;
    private int fontSize;

    public MenuButton(String text, String imgSrc, int fontSize) {
        initButton(text);
        this.fontSize = fontSize;
        
        try {
            img = ImageIO.read(getClass().getResource("/files/images/"+imgSrc));
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public MenuButton(String text, String imgSrc, Dimension dim) {
        initButton(text);
        
        try {
            img = ImageIO.read(getClass().getResource("/files/images/"+imgSrc));
            img = Judge.getJudge().scaleImg(img, dim);
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public MenuButton(String text, String imgSrc, Dimension dim, int fontSize) {
        initButton(text);
        this.fontSize = fontSize;
        
        try {
            img = ImageIO.read(getClass().getResource("/files/images/"+imgSrc));
            img = Judge.getJudge().scaleImg(img, dim);
            setPreferredSize(dim);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void initButton(String text) {
        this.text = text;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                MainFrame.getMainFrame().menuAction(text);
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(img, 0, 0, null);
        
        g.setColor(Color.white);
        AffineTransform affinetransform = new AffineTransform();     
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);  
        Font font = new Font("Roboto Regular", Font.BOLD, fontSize);
        g.setFont(font);
        
        int xText = (getWidth() - (int)font.getStringBounds(text, frc).getWidth()) / 2;
        int yText = (getHeight() + (int)font.getStringBounds(text, frc).getHeight()) / 2 - fontSize/5;
        
        g.drawString(text, xText, yText);
    }
}
