/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view.GFX;


/* Since the spritesheet color scheme used is in grayscale, the actual painting is done at runtime
   The grayscale colors are defined in 4 shades of black and white (black, dark gray, light gray, white)
   What this class does is, it produces RGB colours (with a limited set of colors (36)) that will be later used
   to overlay the grayscale colours with colours we want
    
   Note: Produces Invisible colour on -1 entry;
*/

public class Colours {
    public static int get(int colour1,int colour2, int colour3, int colour4)
    {
        return ((get(colour4) << 24)  + (get(colour3) << 16) + (get(colour2)<<8) + get(colour1));
    }
    
    /* produces a colour that has 6 shades of black, gray, lighter gray, white */
    public static int get(int colour)
    {
        if(colour<0)
        {
            return 255;
        }
        int r = colour/100%10;
        int g = colour/10%10;
        int b = colour%10;
        return r*36 + g*6 + b;
    }
}
