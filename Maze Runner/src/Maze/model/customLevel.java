/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model;

import Maze.model.entities.Entity;
import Maze.view.GFX.Colours;
import Maze.view.GFX.Screen;
import Maze.view.GFX.SpriteSheet;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class customLevel {
    private byte[] tiles;
    public int height;
    public int width;
    public List<Entity> entities = new ArrayList<Entity>();
    private String levelPath;
    private BufferedImage image;
     
    
    public customLevel(String levelPath)
    {
        if(levelPath!=null)
        {
            this.levelPath=levelPath;
            this.loadLevelfromFile();
        }
        else{
            this.width = 64;
            this.height = 64;
            this.levelPath=levelPath;
            tiles= new byte [width*height];
            this.generateLevel();
        }
    }
    
    
    public void tick()
    {
           for(Entity e: entities)
        {
            e.tick();
        }       
    }
    
    public void generateLevel()
    {
        for(int y=0; y<height;y++)
        {
            for(int x =0; x<width; x++)
            {
                if(x*y%10<7)
                    tiles[x+y*width]=Tile.SAND.getID();
                else
                    tiles[x+y*width]=Tile.STONE.getID();
            }
        }
    }
    
    public void renderTiles(Screen screen, int xOffset, int yOffset)
    {
        if(xOffset<0)
            xOffset=0;
        if(xOffset>((width<<3)-screen.width))
        {
            xOffset = ((width<<3)- screen.width);
        }        
        if(yOffset<0)
            yOffset=0;
        if(yOffset>((height<<3)-screen.height))
        {
            yOffset = ((height<<3)- screen.height);
        }

        screen.setOffset(xOffset,yOffset);
        
        
        for(int y=0; y<height;y++)
        {
            for(int x =0; x<width; x++)
            {
                getTile(x,y).render(screen,this,x<<3,y<<3);
            }
        }
        
    }
    
    public Tile getTile(int x, int y)
    {
        if(0>x || x>=width || 0>y || y>=height)
        {
            return Tile.VOID;
        }
        return Tile.tiles[tiles[x+y*width]];
    }
    
    public void renderEntities(Screen screen)
    {
        for(Entity e: entities)
        {
            e.render(screen);
        }        
    }
    
    public void addEntity(Entity entity)
    {
        this.entities.add(entity);
    }
    
    private void loadLevelfromFile()
    {
        try{
            this.image=ImageIO.read(customLevel.class.getResource(this.levelPath));
            this.width = image.getWidth();
            this.height = image.getHeight();
            tiles = new byte[width*height];
            this.loadTiles();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void loadTiles()
    {
        int[] tileColours = this.image.getRGB(0,0,width,height,null,0,width);
        for(int y=0; y<height;y++)
        {
            for(int x=0;x<width;x++)
            {
                tileCheck: for(Tile t : Tile.tiles)
                {
                    if(t.getLevelColour() == tileColours[x+y*width])
                    {
                        this.tiles[x+y*width]=t.getID();
                        break tileCheck;
                    }
                }
            }
        }
    }
    
    private void saveLevel()
    {
            try{
            ImageIO.write(image,"png",new File(customLevel.class.getResource(this.levelPath).getFile()));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }  
    }
    
    public void alterTile(int x, int y, Tile newTile)
    {
        this.tiles[x+y*width]= newTile.getID();
        image.setRGB(x, y, newTile.getLevelColour());
    }
}

