/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.view.GFX;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * This class manages spritesheets we make. Spritesheets are like images, but divided into sections of smaller images.
 * They are commonly used in 2D games to reduce memory usage and save space.
 * Our Sprite sheets contain the player models, the textures of the walls
 * and the font used for typing onto the canvas
 */
public class SpriteSheet {
    public String path;
    public int width ;
    public int height ;
    
    public int[]pixels;
    
    public SpriteSheet(String path)
    {
        BufferedImage image=null;
        try{
        image=ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.path=path;
        this.width=image.getWidth();
        this.height=image.getHeight();
        
        pixels=image.getRGB(0, 0, width, height, null, 0, width);
        for(int i=0; i<pixels.length;i++)
        {
            pixels[i]=(pixels[i] & 0xff)/64;
        }
    }
    
}
