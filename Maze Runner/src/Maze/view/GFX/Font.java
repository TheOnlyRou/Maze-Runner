/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view.GFX;

/**
 *
 * This class is for storing the images of the components of the font we use for typing onto the canvas
 */
public class Font {
    private static String chars = "abcdefghijklmnopqrstuvwxyz      " + "ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + "1234567890!@#$%^&*()+-=/.,<>    ";
    public static void render(String msg, Screen screen, int x, int y, int colour,int scale)
    {
        for(int i=0;i<msg.length();i++)
        {
            int charIndex = chars.indexOf(msg.charAt(i));
            if(charIndex>=0)
            {
                screen.render(x+(i*8), y, charIndex +29*32, colour,0x00,scale);
            }
        }
    }
}
