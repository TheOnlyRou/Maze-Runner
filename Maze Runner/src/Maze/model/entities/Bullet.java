/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model.entities;

import Maze.model.customLevel;
import Maze.view.GFX.Colours;
import Maze.view.GFX.Screen;

/**
 *
 * @author user
 */
public class Bullet extends MovingEntity {

    private int x, y;
   
    public Bullet(customLevel level, int x, int y) {
        super(level, "Bullet", x, y, 2);
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        int xMin=0; 
        int xMax=7;
        int yMin=3;
        int yMax=7;
        
        //creating four lines that define the collision box
        for(int x = xMin ; x<xMax ;x++)
        {
            if(isSolidTile(xa,ya,x,yMin))
                return true;
        }
        for(int x = xMin ; x<xMax ;x++)
        {
            if(isSolidTile(xa,ya,x,yMax))
                return true;
        }
        for(int y = yMin ; y<yMax ;y++)
        {
            if(isSolidTile(xa,ya,xMin,y))
                return true;
        }
        for(int y = yMin ; y<yMax ;y++)
        {
            if(isSolidTile(xa,ya,xMax,y ))
                return true;
        }        
        
        return false;
    }

    @Override
    public void tick() {
        switch(movingDir)
            {
                case 0:
                    y-=5;
                    //newTile= level.getTile((this.x+x)>>3,(this.y+y-1)>>3);
                    break;
                case 1:
                    y+=5;
                    //newTile= level.getTile((this.x+x)>>3,(this.y+y+1)>>3);
                    break;
                case 2:
                    x-=5;
                    //newTile= level.getTile((this.x+x-1)>>3,(this.y+y)>>3);
                    break;                    
                case 3:
                    x+=5;
                    //newTile= level.getTile((this.x+x+1)>>3,(this.y+y)>>3);
                    break;                    
            }
    }

    @Override
    public void render(Screen screen) {
        int xTile=0;
        int yTile=1;
        int travelSpeed=5;
               
        int modifier = 8*scale;
        int xOffset = x- modifier/2;
        int yOffset = y- modifier/2 -4;
        
        screen.render(xOffset,yOffset, xTile + yTile*32, Colours.get(-1, -1, 420,-1),00,scale);
    }
    
}
