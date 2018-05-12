/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze.model.entities;

import Maze.model.customLevel;
import Maze.view.GFX. Screen;

/**
 *
 * This class has the blueprint for all entities in the game; Pickups, mobs, players, etc
 */
public abstract class Entity {
    public int x,y;
    protected customLevel level;
    
    public Entity(customLevel level)
    {
        init(level);
    }
    
    public final void init(customLevel level)
    {
        this.level=level;
    }
    
    public abstract void tick();
    public abstract void render(Screen screen);
    
}
