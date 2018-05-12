/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view.GFX;

/**
 *
 *  This class's main duty is managing the maths behind the movement of the player, the canvas.
 */
public class Screen {
    
    public static final int MAP_WIDTH=64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;
    
    public static final byte BIT_MIRROR_X=0x01;
    public static final byte BIT_MIRROR_Y=0x02;
    
    
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

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void render(int xPosition, int yPosition, int tile, int colour)
    {
        render(xPosition, yPosition, tile, colour, 0,1);
    }
   
    /* Method to render pixels on the Screen and make sure nothing goes off boundaries, also allowing scrolling */ 
    //Rendering function that goes along with current colour scheme. old one no longer works
    public void render(int xPosition, int yPosition, int tile, int colour, int mirrorDir, int scale)
    {
        xPosition -= xOffset;
        yPosition -= yOffset;
        
        boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
        boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;
        
        int scaleMap = scale-1;
        int xTile = tile%32;
        int yTile = tile/32;
        
        int tileOffset = (xTile<<3) + (yTile << 3)* sheet.width;
        for(int y=0;y<8;y++)
        {
            int ySheet=y;
            if(mirrorY)
                ySheet=7-y;
                int yPixel = y + yPosition + (y*scaleMap) - ((scaleMap <<3)/2);

            for(int x=0;x<8;x++)
            {
                int xSheet=x;
                if(mirrorX)
                    xSheet=7-x;
                int xPixel = x + xPosition + (x*scaleMap) - ((scaleMap <<3)/2);
                int col = (colour >> sheet.pixels[xSheet + ySheet*sheet.width + tileOffset] * 8) & 255;
                if(col<255)
                {
                    for(int yScale=0; yScale<scale; yScale++)
                    {
                        if(yPixel+yScale < 0 || yPixel+yScale >=height)
                            continue;                       
                    
                    for(int xScale =0; xScale<scale;xScale++)
                    {
                        if(xPixel+xScale < 0 || xPixel+xScale >=width)
                            continue;        
                        pixels[(xPixel + xScale) + (yPixel+ yScale)*width]=col;
                    }                     

                    }
                }
            }
        }
    }
    
}
