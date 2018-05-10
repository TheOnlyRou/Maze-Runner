/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view.GFX;

/**
 *
 * @author user
 */
public class Screen {
    
    public static final int MAP_WIDTH=64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
    
    
    
    
    public int xOffset=0;
    public int yOffset=0;
    public int[] pixels;
    public int width;
    public int height;
    
    public SpriteSheet sheet;
    
    public Screen(int width, int height, SpriteSheet ss){
        this.width=width;
        this.height=height;
        this.sheet=ss;
        /* Setting colors corresponding to which true colors*/
        pixels = new int[width*height];
    }
   
    /* Method to render pixels on the Screen and make sure nothing goes off boundaries, also allowing scrolling */ 
    //Rendering function that goes along with current colour scheme. old one no longer works
    public void render(int xPosition, int yPosition, int tile, int colour )
    {
        xPosition -= xOffset;
        yPosition -= yOffset;
        
        int xTile = tile%32;
        int yTile = tile/32;
        
        int tileOffset = (xTile<<3) + (yTile << 3)* sheet.width;
        for(int y=0;y<8;y++)
        {
            int ySheet=y;
            if(y+yPosition < 0 || y+yPosition >=height)
                continue;
            for(int x=0;x<8;x++)
            {
                if(x+xPosition < 0 || x+xPosition >=width)
                continue;                
                int xSheet=x;
                int col = (colour >> sheet.pixels[xSheet + ySheet*sheet.width + tileOffset] * 8) & 255;
                if(col<255)
                {
                    pixels[(x + xPosition) + (y+ yPosition)*width]=col;
                }
            }
        }
    }
    
}
