/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;

/**
 *
 * @author Amadeusz
 */
public class MainFrame extends JFrame {
    private static MainFrame mainFrame = null;
    private GameField gameField;
    private JudgePanel judgePanel;
    private MenuPanel menuPanel;
    private CreateLevel createLevel;
    private Judge judge;
    private LevelsScreen levelsScreen;
    private ScreenContainer screenContainer;
    public static final int WIDTH = 900, PANEL_HEIGHT = 35;
    public static final int HEIGHT = 600, 
            FIELD_HEIGHT = HEIGHT - PANEL_HEIGHT;
    
    private MainFrame() {
        super("Arka Noida");
        init();
    }
    
    public void init() {
        initMenuPanel();
        initFrame();
        initFonts();
        initJudge();
    }
        
    public static MainFrame getMainFrame() {
        if(mainFrame == null) 
            mainFrame = new MainFrame();
        return mainFrame;
    }
    
    public void initMenuPanel() {
        menuPanel = new MenuPanel(WIDTH, HEIGHT);
        getContentPane().add(menuPanel);
    }
    
    public void initFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        setLocation((int)(screenWidth - WIDTH)/2, (int)(screenHeight - HEIGHT)/2-50);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void initFonts() {
        URI uri;
        String fontSrc = "/files/fonts/";
        /*try {
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            uri = getClass().getResource(fontSrc + "Roboto-Thin.ttf").toURI();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(uri)));
            uri = getClass().getResource(fontSrc + "Roboto-Regular.ttf").toURI();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(uri)));
        } catch (URISyntaxException|IOException|FontFormatException e) {
            System.out.print("Nie wczytało czcionki!");
        }*/
        
        try {
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/files/fonts/Roboto-Thin.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/files/fonts/Roboto-Regular.ttf")));
        } catch (IOException|FontFormatException e) {
            System.out.print("Nie wczytało czcionki!");
        }
    }
    
    public void initJudge() {
        judge = Judge.getJudge();
    }
    
    public void menuAction(String action) {
        switch(action) {
            case "PLAY":
                chooseLevel();
                break;
            case "EXIT":
                exit();
                break;
            case "CREATE":
                create();
                break;
            case "HOME":
                backToMenu();
                break;
            case "SAVE":
                saveLevel();
                break;
            default:
                break;
        }
    }
    
    public void chooseLevel() {
        getContentPane().removeAll();
        
        levelsScreen = new LevelsScreen(WIDTH, HEIGHT);
        getContentPane().add(levelsScreen);
        pack();
    }
    
    public void newGame(String level) {
        getContentPane().removeAll();
        
        judge.setLevel(level);
        judgePanel = new TopPanelGame(WIDTH, PANEL_HEIGHT);
        gameField = new GameField(WIDTH, FIELD_HEIGHT, level);
        screenContainer = new ScreenContainer(judgePanel, gameField);
        judge.setScreenContainer(screenContainer);
        getContentPane().add(screenContainer);
        pack();
    }
    
    public void create() {
        getContentPane().removeAll();
        
        judgePanel = new TopPanelCreate(WIDTH, PANEL_HEIGHT);
        createLevel = new CreateLevel(WIDTH, HEIGHT);
        screenContainer = new ScreenContainer(judgePanel, createLevel);
        judge.setScreenContainer(screenContainer);
        getContentPane().add(screenContainer);
        pack();
    }
    
    public void backToMenu() {
        getContentPane().removeAll();
        pack();
        getContentPane().add(menuPanel);
        pack();
    }
    
    public void saveLevel() {
        judge.getActionField().saveLevel();
        backToMenu();
    }
    
    public void exit() {
        System.exit(0);
    }
    
    public void gameEnd(boolean isWin) {
        getContentPane().removeAll();
        getContentPane().add(new EndScreen(isWin, judge.getLevel()));
        pack();
    }
}
