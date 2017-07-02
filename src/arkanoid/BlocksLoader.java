/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author Amadeusz
 */
public class BlocksLoader {
    private InputStream fileBonuses;
    private File fileLevel;
    private BufferedReader bufferedReader;
    private ArrayList<String[]> bonuses;
    private ArrayList<Integer[]> tableOfBonuses;
    private ArrayList<Integer> allBonuses;
    
    private int sumOfBonuses, percentOfBonuses;
    
    public BlocksLoader(String level) {      
        fileLevel = new File("src/files/levels/"+level);
        fileBonuses = getClass().getResourceAsStream("/files/bonuses/bonuses.txt");
        
        
        sumOfBonuses = 0;
        percentOfBonuses = 20;
        
        bonuses = new ArrayList();
        tableOfBonuses = new ArrayList();
        allBonuses = new ArrayList();
        
        loadBonuses();
        generateInts();
    }
    
    public BlocksLoader(String level, int whatever) {
        fileLevel = new File("src/files/levels/"+level);
    }
    
    public ArrayList<BaseBlock> loadBlocks(Boolean isCreate) {
        ArrayList<BaseBlock> blocks = new ArrayList();
        ArrayList<String> lines = new ArrayList();
        ArrayList<String> coordinates = new ArrayList();
        String[] tempCrd;
        String[] crd;
        String line;
        int row, col, state;
        
        try {
            Reader fileReader = new FileReader(fileLevel);
            bufferedReader = new BufferedReader(fileReader);
            
            for(int i=0; i<3; i++) {
                bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            
            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }  
        } catch(IOException ex) {
            System.err.println("Error :"+ex);
        }
        
        for(int i=0; i<lines.size(); i++) {
            tempCrd = lines.get(i).split("\t");
            for(int j=0; j<tempCrd.length; j++) {
                coordinates.add(tempCrd[j]);
            }
        }
        
        for(String element : coordinates) {
            crd = element.split(",");
            row = Integer.parseInt(crd[0]);
            col = Integer.parseInt(crd[1]);
            if(isCreate)
                blocks.add(new BlockEmpty(row,col));
            else {
                state = Integer.parseInt(crd[2]);
                blocks.add(new Block(row,col,drawBonus(),state));
            }
        }
        
        return blocks;
    }
    
    public void loadBonuses() {
        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(fileBonuses));
            
            for(int i=0; i<2; i++) {
                bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
            
            while(line != null) {
                bonuses.add(line.split(";"));
                line = bufferedReader.readLine();
            }  
        } catch(IOException ex) {
            System.err.println("Error :"+ex);
        }
    }
    
    public void generateInts() {
        Integer[] temp;
        for(int i=0; i<bonuses.size(); i++) {
            temp = new Integer[2];
            temp[0] = Integer.parseInt(bonuses.get(i)[0]);
            temp[1] = Integer.parseInt(bonuses.get(i)[1]);
            tableOfBonuses.add(temp);
            sumOfBonuses += temp[1];
        }
        for(int i=0; i<sumOfBonuses*100/percentOfBonuses-sumOfBonuses; i++) {
            allBonuses.add(0);
        }
        for(int i=0; i<tableOfBonuses.size(); i++) {
            for(int j=0; j<tableOfBonuses.get(i)[1]; j++) {
                allBonuses.add(tableOfBonuses.get(i)[0]);
            }
        }
        sumOfBonuses = allBonuses.size();
    }
    
    public int drawBonus() {
        int index = (int)(Math.random()*sumOfBonuses);
        return allBonuses.get(index);
    }
    
    public ArrayList<String[]> getBonuses() {
        return bonuses;
    }
    
    
}
