/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model.entities;

import Maze.controller.GameWon;
import Maze.model.Tile;
import Maze.model.customLevel;
import Maze.view.GFX.Screen;
import Maze.view.HPObserver;
import Maze.view.ArmorObserver;

/**
 *
 * This class is the blueprint for all Moving entities; player, mobs (later), etc
 * It has the movement functions and collision detection method.
 */
public abstract class MovingEntity extends Entity{

    protected String name;
    protected int speed;
    protected int numSteps=0;
    protected boolean isMoving;
    protected int movingDir=1; // 0 is up, 1 is down, 2 is left, 3 is right
    protected int scale=1;
    
    public MovingEntity(customLevel level, String name,int x, int y, int speed)
    {
        super(level);
        this.name=name;
        this.x=x;
        this.y=y;
        this.speed=speed;
    }
    
    public void move(int xa, int ya)
    {
        if(xa!=0 && ya!=0)
        {
            move(xa,0);
            move(0,ya);
            numSteps--;
        }
        numSteps++;
        if(!hasCollided(xa,ya))
        {
            if(ya<0)
                movingDir=0;
            if(ya>0)
                movingDir=1;
            if(xa<0)
                movingDir=2;
            if(xa>0)
                movingDir=3;            
            x+=xa*speed;
            y+=ya*speed;
        }
    }
    
    public abstract boolean hasCollided(int xa, int ya);
    
    protected boolean isSolidTile(int xa, int ya, int x, int y)
    {
        if(level==null)
            return false;
        
        Tile lastTile= level.getTile((this.x+x)>>3,(this.y+y)>>3);
        Tile newTile= level.getTile((this.x+x +xa)>>3,(this.y+y+ya)>>3);
        
       if(!lastTile.equals(newTile) && newTile.doesDamage())
       {
            HPObserver hp = new HPObserver(newTile.getDamage(),false);
       }
       
       if(!lastTile.equals(newTile) && newTile.isIsPickup())
       {  
            HPObserver hp;
            ArmorObserver armor;
            if(newTile==Tile.HP)
            {
                hp = new HPObserver(newTile.getHeal(),true);
            }
            else if(newTile==Tile.ARMOR)
            {
                armor = new ArmorObserver(newTile.getHeal());
            }        
       }
       
       if(!lastTile.equals(newTile) && newTile.isSolid())
       {
           return true;    
       }

       if(lastTile.isHasWon())
       {
           GameWon winner = new GameWon();
           winner.execute();
       }
        
        return false;
    }

    public String getName() {
        return name;
    }    
    
    @Override
    public abstract void tick();
    @Override
    public abstract void render(Screen screen);
    
}
