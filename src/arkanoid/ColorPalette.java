/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Color;

/**
 *
 * @author Amadeusz
 */
public class ColorPalette {
    public static final Color[] colors = {
        new Color(26, 188, 156),    //0 turkus
        new Color(46, 204, 113),    //1 szmaragd
        new Color(52, 152, 219),    //2 peter river
        new Color(155, 89, 182),    //3 ametyst
        new Color(52, 73, 94),      //4 mokry asfalt
        new Color(22, 160, 133),    //5 zielone morze
        new Color(39, 174, 96),     //6 nefryt (zielony)
        new Color(41, 128, 185),    //7 belize hole (niebieski)
        new Color(142, 68, 173),    //8 wisteria (fioletowy)
        new Color(44, 62, 80),      //9 midnight blue
        new Color(241, 196, 15),    //10 słonecznik
        new Color(230, 126, 34),    //11 marchew
        new Color(231, 76, 60),     //12 alizarin (czerwony)
        new Color(236, 240, 241),   //13 chmury
        new Color(149, 165, 166),   //14 beton
        new Color(243, 156, 18),    //15 pomarańcz
        new Color(211, 84, 0),      //16 dynia
        new Color(192, 57, 43),     //17 pomegranate (czerwony)
        new Color(189, 195, 199),   //18 srebro
        new Color(127, 140, 141)    //19 azbest
    };
    
    public static final Color[] colorsBlocks = {
        new Color(46, 204, 113),    //0 szmaragd
        new Color(52, 152, 219),    //1 peter river
        new Color(241, 196, 15),    //4 słonecznik
        new Color(39, 174, 96),     //6 nefryt (zielony)
        new Color(41, 128, 185),    //7 belize hole (niebieski)
        new Color(243, 156, 18),    //15 pomarańcz
        new Color(230, 126, 34),    //11 marchew
        new Color(211, 84, 0),      //16 dynia
    };
    
    public static final Color[] BLOCK_COLORS = {
        new Color(254, 235, 60),    //0 żółty
        new Color(161, 234, 158),   //1 jasnozielony
        new Color(254, 151, 0),     //2 oranż
        new Color(0, 229, 255),     //3 błękit
        new Color(206, 220, 58),    //4 zgniły
        new Color(255, 205, 128),   //5 beż
        new Color(48, 79, 255),     //6 niebieski
        new Color(96, 221, 28),     //7 zielony
    };
}
