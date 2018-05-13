/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model.entities;

import Maze.controller.InputHandler;
import Maze.model.Tile;
import Maze.model.customLevel;
import Maze.view.GFX.Colours;
import Maze.view.GFX.Screen;
import Maze.view.MainWindow;

/**
 *
 * This class creates a Moving Entity that is the player, that has the ability to move and shoot. It has its own collision box.
 * This is the unit the user will operate with on the screen
 */
public class Player extends MovingEntity {

    private InputHandler input;
    private int colour = Colours.get(-1, 111, 511,543);
    private boolean isShooting;
    
    public Player(customLevel level, int x, int y, InputHandler input)
    {
        super(level,"Player",x,y,1);
        this.input=input;
        this.scale=1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
     
    
    //This method creates the collision box for the player and decides whether or not it has collided with another object's collision box
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
    public void move(int xa, int ya){
        super.move(xa, ya);

        Tile lastTile= level.getTile((this.x+x)>>3,(this.y+y)>>3);
        Tile newTile= level.getTile((this.x+x +xa)>>3,(this.y+y+ya)>>3);
        
        if(!lastTile.equals(newTile) && newTile.doesDamage())
        {
            
        }
    }
    
    

    @Override
    public void tick() {
        int xa=0;
        int ya=0;
        
        if(input.up.isPressed())
        {
            ya--;
        }
        if(input.down.isPressed())
        {
            ya++;
        }
        if(input.right.isPressed())
        {
            xa++;
        }
        if(input.left.isPressed())
        {
            xa--;
        }      
        if(input.space.isPressed())
        {
            this.isShooting = true;
        }
        else{
            this.isShooting=false;
        }
        if(xa!=0 || ya!=0)
        {
            move(xa,ya);
            isMoving=true;
        }
        else{
            isMoving=false;
        }
        shoot();
    }

    @Override
    public void render(Screen screen) {
        int xTile=0;
        int yTile=27;
        int walkingSpeed=5;
        int flipTop = (numSteps >> walkingSpeed) & 1 ;
        int flipBot = (numSteps >> walkingSpeed) & 1 ;; 
        
        if(movingDir==1)
            xTile+=2;
        else if(movingDir>1)
        {
            xTile+= 4 + ((numSteps >> walkingSpeed)& 1)*2;
            flipTop = (movingDir-1)%2;
        }        
        
        int modifier = 8*scale;
        int xOffset = x- modifier/2;
        int yOffset = y- modifier/2 -4;
        

        
        screen.render(xOffset + (modifier*flipTop),yOffset, xTile + yTile*32, colour,flipTop,scale);
        
        screen.render(xOffset + modifier - (modifier*flipTop) , yOffset, (xTile + 1) + yTile*32, colour,flipTop,scale);
        
        screen.render(xOffset + (modifier*flipBot), yOffset + modifier, xTile + (yTile+1)*32, colour,flipBot,scale);
        
        screen.render(xOffset + modifier - (modifier*flipBot) ,yOffset + modifier, (xTile+1) + (yTile+1)*32, colour,flipBot,scale);
    }
    
    public void shoot()
    {
        if(isShooting)
        {
            System.out.println("PEW");
            Bullet b = new Bullet(this.level,this.x,this.y);
        }
        
        /*if(hasCollided(xa,ya))
        {
            System.out.println(bullets);
            Tile lastTile = level.getTile((this.x+x)>>3,(this.y+y)>>3);
            Tile newTile = level.getTile((this.x+x)>>3,(this.y+y)>>3);

            switch(movingDir)
            {
                case 0:
                    newTile= level.getTile((this.x+x)>>3,(this.y+y-1)>>3);
                    break;
                case 1:
                    newTile= level.getTile((this.x+x)>>3,(this.y+y+1)>>3);
                    break;
                case 2:
                    newTile= level.getTile((this.x+x-1)>>3,(this.y+y)>>3);
                    break;                    
                case 3:
                    newTile= level.getTile((this.x+x+1)>>3,(this.y+y)>>3);
                    break;                    
            }
            if(!lastTile.equals(newTile) && newTile.isDestructible())
            {
                tileCheck: for(Tile t : Tile.tiles)
                {
                    if(newTile.equals(t))
                    {
                        level.alterTile(xa, ya, newTile);
                    }
                }
            }
            bullets--;
        }
        else
        {
             //produce sound tick tick and do nothing
        }*/
    }
    
}
