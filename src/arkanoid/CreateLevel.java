/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Amadeusz
 */
public class CreateLevel extends ActionField {
    
    private ArrayList<String> lines;
    
    public CreateLevel(int width, int height) {
        super(width, height);
        
        init();
    }
    
    public void init() {
        initBlocks();
        mouseAdapter();
    }
    
    public void initBlocks() {
        loader = new BlocksLoader("empty/emptyLevel.txt",1);
        blocks = loader.loadBlocks(true);
    }

    public void mouseAdapter() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                for(BaseBlock b : blocks) {
                    if(b.contains(e.getPoint())) {
                        BlockEmpty be = (BlockEmpty)b;
                        switch(be.changeState()) {
                            case 0:
                                judge.getJudgePanel().addBricks(-1);
                                break;
                            case 1:
                                judge.getJudgePanel().addBricks(1);
                                break;
                            default:
                                break;
                        }
                        repaint();
                    }
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        Color gr1 = new Color(102, 136, 233);
        Color gr2 = new Color(126, 68, 171);
        
        GradientPaint gradientBackground = new GradientPaint(0, 0, gr1, width, height, gr2);
        g2d.setPaint(gradientBackground);
        g2d.fillRect(0, 0, width, height);
        
        for(int i=0; i<blocks.size(); i++) {
            blocks.get(i).paint(g2d);
        }   
    }
    
    @Override
    public void saveLevel() {
        lines = new ArrayList();
        JTextField tfName = screenContainer.getTopBar().getLevelName();
        int bricksCount = screenContainer.getTopBar().getBricks();
        int i = 0;
        BlockEmpty be;
        String temp = "";
        for(BaseBlock b : blocks) {
            be = (BlockEmpty)b;
            if(be.getState() != 0) {
                i++;
                if(i%10!=0 && i!=bricksCount) {
                    temp += "" + be.getRow() + "," + be.getCol() + "," + 
                            be.getState() + "\t";
                } else {
                    temp += "" + be.getRow() + "," + be.getCol() + "," + 
                            be.getState() + "\r\n";
                    lines.add(temp);
                    temp = "";
                }
            }
        }
        
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream("src/files/levels/" + tfName.getText()+".txt"), "utf-8"));
            writer.write("### " + tfName.getText() + " ###\r\n# (row,col) #\r\n#\r\n");
            for(String s : lines)
                writer.write(s);
        } catch (IOException ex) {
          System.err.print(ex);
        } finally {
           try {writer.close();} catch (Exception ex) {
               System.err.print(ex);
           }
        }
    }
    
    @Override
    public void blocksDown() { }
}
